import os
import cv2
import numpy as np
import tensorflow as tf
from tensorflow import keras
from django.http import JsonResponse
from django.shortcuts import render, redirect
from django.views.decorators.csrf import csrf_exempt
from fashion.models import fashionModel

# Create your views here.
UPLOAD_DIR = '/home/syyoon/PycharmProjects/pythonProject/fashion/static/uploadImg/'

def upload_img(request):
    from sys import version
    # print('version : ', version)
    return render(request, "fashion/imgForm.html")
@csrf_exempt
def insert_img(request):
    new_model = keras.models.load_model('/home/syyoon/PycharmProjects/pythonProject/fashion/static/fashion_mnist_model')
    file1 =request.FILES['file1']
    print(file1.name)

    file_name = file1.name
    file_path = os.path.join(UPLOAD_DIR, file_name)
    print("1")
    with open(file_path, 'wb+') as destination:
        for chunk in file1.chunks():
            destination.write(chunk)
    print("2")
    img = cv2.imread(file_path, cv2.IMREAD_GRAYSCALE)
    if img is None:
        return JsonResponse({'error': 'Failed to read the image file.'})

    img[img > 128] = 255
    img = cv2.resize(img, (28, 28))
    # 이진화
    _, img = cv2.threshold(img, 128, 255, cv2.THRESH_BINARY_INV)
    # 예측이 가능한 이미지
    # 예측이 가능한 이미지 img.reshape(1,28,28,1)
    img = img.reshape((1, 28, 28, 1))
    # dtype('unit8') -> float32 변경
    # img.dtype
    img = img.astype(np.float32)
    print("3")
    class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat', 'Sandal', 'Shirt', 'Sneaker', 'Bag','Anckle boot']
    print("4")
    # new_model = tf.keras.layers.TFSMLayer('/home/syyoon/PycharmProjects/pythonProject/fashion/static/fashion_mnist_model', call_endpoint='serving_default')
    # new_model = tf.keras.models.load_model('/home/syyoon/PycharmProjects/pythonProject/fashion/static/fashion_mnist_model')

    print("5")
    score = new_model.predict(img)
    predicted_class = np.argmax(score)
    print('Predicted class : ', class_names[predicted_class])
    res = {'Predicte class' : class_names[predicted_class]}
    return JsonResponse(res)













    # if request.method == 'POST':
    #     img = request.FILES["file1"]
    #     fileupload = fashionModel.FileUpload(
    #         imgfile=img,
    #     )
    #     fileupload.save()
    #     return redirect('fileupload')
    # else:
    #     print('---------------------')
# def insert_img(request):
#     file = request.POST('file1')
#     print('--------',file)
    # file_path = cv2.imread()
    # myModel = fashionModel()
    # img = myModel.loadImage(file_path)
    # res = myModel.guessImage(img)
    # resJson = {"class": res}
    # return JsonResponse(resJson)