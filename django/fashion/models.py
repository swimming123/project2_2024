import cv2
import numpy as np
import tensorflow as tf
from django.db import models

# Create your models here.
class fashionModel:

    def loadImage(self,imgName):
        imgfile = models.ImageField(null=True, upload_to="", blank=True)
        img = cv2.imread(imgName)
        mg = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        img[img > 128] = 255
        img = cv2.resize(img, (28, 28))

        # img = cv2.GaussianBlur(img, (5, 5), 0)
        # img = cv2.adaptiveThreshold(img, 255, cv2.ADAPTIVE_THRESH_GAUSSIAN_C, cv2.THRESH_BINARY_INV, 11, 2)
        # 이진화
        _, img = cv2.threshold(img, 128, 255, cv2.THRESH_BINARY_INV)
        img = img.reshape((1, 28, 28, 1))

        # dtype('unit8') -> float32 변경
        # img.dtype
        img = img.astype(np.float32)
        return img


    def guessImage(self,img):
        new_model = tf.keras.models.load_model('/home/syyoon/PycharmProjects/pythonProject/fashion/static/fashion_mnist_model')
        score = new_model.predict(img)
        predicted_class = np.argmax(score)
        class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat', 'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Anckle boot']
        print('Predicted class : ', class_names[predicted_class])

    def FileUpload(self):
        imgfile = models.ImageField(null=True, upload_to="", blank=True)
        return imgfile