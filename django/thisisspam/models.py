import re
import io
from google.cloud import vision
from google.cloud.vision_v1 import types
from django.db import models
import pytesseract
import cv2
import pandas as pd
from sklearn.naive_bayes import GaussianNB
from sklearn.feature_extraction.text import CountVectorizer
import numpy as np
import os
# Create your models here.
#정보와 경고 로그를 제외하고 오류 로그만 표시 - 필요 없는 로그 줄이고 필요한 것만 보기 위함
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = '#################################'
class MyResNetSpamModel:
    def isThisSpam_1(self,imgName):
        imageTarget = cv2.imread(imgName, cv2.IMREAD_GRAYSCALE)
        maxValue = 255
        n_size = 99
        s_mean = 10
        image_binarized = cv2.adaptiveThreshold(imageTarget, maxValue, cv2.ADAPTIVE_THRESH_GAUSSIAN_C, cv2.THRESH_BINARY, n_size, s_mean)
        text = pytesseract.image_to_string(image_binarized, lang='kor+eng')
        cleaned_content = re.sub('[^가-힣ㄱ-ㅎㅏ-ㅣ|a-zA-Z0-9]', ' ', text)
        return cleaned_content

    def isThisSpam(self,imgName):
        img = cv2.imread(imgName)
        imageTarget = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        text = pytesseract.image_to_string(imageTarget, lang='kor+eng')
        cleaned_content = re.sub('[^가-힣ㄱ-ㅎㅏ-ㅣ|a-zA-Z0-9]', ' ', text)
        return cleaned_content

    def detect_texts(self, imgName):
        """이미지에서 라벨을 감지합니다."""
        # Vision API 클라이언트 설정
        client = vision.ImageAnnotatorClient()

        # 이미지 읽기
        with io.open(imgName, 'rb') as image_file:
            content = image_file.read()

        image = types.Image(content=content)

        # 텍스트 감지
        response = client.text_detection(image=image)
        texts = response.text_annotations

        # 감지된 텍스트 추출
        text = [re.sub('[^가-힣ㄱ-ㅎㅏ-ㅣ|a-zA-Z0-9]', ' ', text.description) for text in texts]

        return text

    def predict_spam(self, text):
        data = {
            'index': range(1, 14),
            'email': [
                "I got free two movie ticket from your body friend",
                "free coupon from xx.com",
                "watch free new movie from freemovie.com",
                "Best deal, promo code here",
                "There will be free pizza today 2pm meeting - your boss",
                "Scheduled meeting tomorrow",
                "Can we have lunch today?",
                "I miss you",
                "thanks my friend",
                "It was good to see you today",
                "할인",
                "세일",
                "특가"
            ],
            'status': [
                "nospam",
                "spam",
                "spam",
                "spam",
                "nospam",
                "nospam",
                "nospam",
                "nospam",
                "nospam",
                "nospam",
                "spam",
                "spam",
                "spam"
            ]
        }
        # 데이터를 데이터프레임으로
        df = pd.DataFrame(data)

        # 단어벡터화
        vectorizer = CountVectorizer()

        # X : 'email' 컬럼을 특징 벡터로 변환
        X = vectorizer.fit_transform(df['email']).toarray()
        # print(f"{df['email'][0]}, {X[0]}")

        # Y : 'status' 컬럼을 숫자로 변환 nospam 0 , spam 1
        y = df['status'].map({'nospam': 0, 'spam': 1})

        # 가우시안 나이브 베이즈 객체를 생성
        clf = GaussianNB()
        clf.fit(X, y)
        # msg = {0: '스팸 아님', 1: '스팸'}

        # 예측
        score = np.zeros(2)
        for t in text:
            test_X = vectorizer.transform([t]).toarray()
            predicted_prob = clf.predict_proba(test_X)
            print(f"predicted_prob[0] => {predicted_prob[0]}")
            score += predicted_prob[0]
        print(text)

        print(f"score[0] => {score[0]}")
        print(f"score[1] => {score[1]}")

        # 스팸 여부 결정
        if score[1] > score[0]:
            return 1  # 스팸
        else:
            return 0  # 스팸 아님