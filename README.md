영화 포스터 게시하는 페이지

대표 기능 : 게시판에 이미지 파일 업로드 시, 스팸 단어(상업적)를 검사해서 업로드 가능 유무를 확인할 수 있는 게시판

- 사용한 버전
  - java : "17.0.10" 2024-01-16 LTS
  - python : 3.12.3
  - node : v20.12.2
  - Spring boot : 3.2.6
  - Oracle(11g)
- IDE
  - STS4 : 4.22.1.RELEASE
  - Visual Code : 1.90
  - Oracle VM VirtualBox : 6.1
    - ubuntu : 22.04.4LTS
    - pycharm : 2024.1.3
    - python : 3.10.14
    - dJango : 5.0.4
  - google colab
    - Python 3.10.12
  - 사용한 모듈
    - sklearn : naive_bayes : GaussianNB
    - sklearn : feature_extraction.text : CountVectorizer
    - google-cloud-vision
      - core : 1.31.5
      - google-cloud-vision : 2.11.1



- Table 생성 코드

ALTER SESSION SET NLS_DATE_FORMAT='rr/mm/dd';

CREATE TABLE movie_upboard(

num NUMBER PRIMARY KEY,

title VARCHAR2 (100),

writer VARCHAR2(300),

content VARCHAR2(200),

hit NUMBER(10),

reip VARCHAR2(34),

bdate date,

imgn VARCHAR2(30)

);



CREATE SEQUENCE movie_upboard_seq

INCREMENT BY 1 START WITH 1;



* 개선할 부분 : models.py에 학습 데이터를 딥러닝 모델로 변경해서 모델을 저장해서 불러오기
