from django.http import JsonResponse
from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt

from thisisspam.models import MyResNetSpamModel
import os

UPLOAD_DIR = '/home/ict/PycharmProjects/mydjango0408/thisisspam/static/uploadImg/'


def upload_img(request):
    return render(request, "thisisspam/imgForm.html")


@csrf_exempt
def insert_img(request):
    if request.method == 'POST' and 'file1' in request.FILES:
        file = request.FILES['file1']
        file_name = file.name
        file_path = os.path.join(UPLOAD_DIR, file_name)

        # Save the uploaded file to the specified directory
        with open(file_path, 'wb+') as destination:
            for chunk in file.chunks():
                destination.write(chunk)

        # Process the image using MyResNetSpamModel
        myModel = MyResNetSpamModel()
        # text1 = myModel.isThisSpam(file_path)
        text2 = myModel.detect_texts(file_path)
        spam = myModel.predict_spam(text2)

        # Prepare the JSON response
        resJson = {"spam":spam}
        print(f"resJson => {resJson}")
        return JsonResponse(resJson)
    else:
        return JsonResponse({"error": "Invalid request"}, status=400)
