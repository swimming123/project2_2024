<template>
  <div class="contact_area wrap">
    <div class="container">
      <div class="row">
        <div class="col-md-4 col-sm-6 col-xs-12">
          <div class="main_contact">
            <h1>무슨 옷이게?</h1>
            <h4>image Preview</h4>
            <img v-if="files.length > 0" :src="files[0].preview" class="img-thumbnail" style="max-width: 300px; max-height: 500px;">
          </div>
        </div>
        <div class="col-md-8 col-sm-6 col-xs-12">
          <div class="main_contact2">
            <div class="information">
              <form @submit.prevent="submitForm">
                <span class="label label-default">imageName</span>
                <input type="text" class="form-control" v-model="imageName" placeholder="imageName">
                <br>
                <input class="form-control" @change="handleFileChange" multiple accept="image/*" ref="fileRef" type="file">
                <br>
                <input type="text" class="form-control" v-model="imgdata" readonly />
                <button type="button" class="btn btn-default" @click="chkImg">Check Img<i class="lnr lnr-chevron-right-circle"></i></button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      files: [],
      imgdata: null,
      selectedFiles: null,
      imageName: null,
      backendUrl: "http://192.168.0.36:9000", // 백엔드 URL
    };
  },
  methods: {
    handleFileChange(event) {
      this.selectedFiles = event.target.files || [];
      this.files = []; // 기존 파일 초기화
      for (let i = 0; i < this.selectedFiles.length; i++) {
        const file = this.selectedFiles[i];
        const preview = URL.createObjectURL(file);
        this.files.push({
          name: file.name,
          preview: preview,
        });
      }
    },
    chkImg() {
      if (!this.selectedFiles || this.selectedFiles.length === 0) {
        alert("이미지를 선택해주세요.");
        return;
      }
      const formData = new FormData();
      for (let i = 0; i < this.selectedFiles.length; i++) {
        formData.append("file1", this.selectedFiles[i]);
        formData.append("category", this.imageName);
      }
      axios.post(`${this.backendUrl}/fashion/insert_img`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then((response) => {
        console.log(response); // JSON 데이터를 콘솔에 출력
        this.imgdata = JSON.stringify(response.data);
      })
      .catch((error) => {
        console.error('Error:', error);
        alert("네트워크 오류가 발생했습니다. 다시 시도해주세요.");
      });
    }
  }
};
</script>