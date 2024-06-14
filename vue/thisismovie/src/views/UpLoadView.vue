<template>
  <div class="contact_area wrap">
    <div class="container">
      <div class="row">
        <div class="col-md-4 col-sm-6 col-xs-12">
          <div class="main_contact">
            <h1>FileUpLoad</h1>
            <p>
             팀장님~ 준영님~ 여기에는 나중에 업로드되어 작업하는 것에 대한 내용이 간략하게 설명되어질 예정입니다. 
            </p>
          </div>
        </div>

        <div class="col-md-8 col-sm-6 col-xs-12">
          <div class="main_contact2">
            <div class="information">
              <p>수영이 업로드</p>
              <input class="form-control" @change="handleFileUpload" ref="image" type="file">
              <img v-if="imagePreview" class="form-control" :src="imagePreview" alt="이미지 미리보기" style="width: 100%; height: 100%; max-width: 400px;">
              <br>
              <button type="button" class="btn btn-warning" @click="chkImg">이미지확인</button>
              <br>
              <button type="button" class="btn btn-success" @click="submit" :disabled="!isImgDataValid">등록하기</button>
              <br>
              <p>Upload Posters Here</p>
              <input
                type="file"
                @change="selectFile"
                multiple
                accept="image/*"
                ref="fileRef"
              />
              <div class="images" v-if="files.length > 0">
                <div v-for="file in files" :key="file.name" class="image">
                  <img :src="file.preview" alt="이미지"> {{ file.name }}
                  
                </div>
              </div>
            </div>
            <button class="btn btn-default" type="button">
              Send Now<i class="lnr lnr-chevron-right-circle"></i>
            </button>
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
      imgdata: "",
      imageName: "",
      imagePreview: null,
      selectedFile: null
    };
  },
  computed: {
    backendUrl() {
      return "http://192.168.0.39/thismovie0607";
    },
    isImgDataValid() {
      try {
        const imgDataParsed = JSON.parse(this.imgdata);
        return imgDataParsed["spam"] === 0;
      } catch (e) {
        return false;
      }
    }
  },
  methods: {
    selectFile(event) {
      this.files = [];
      const formData = new FormData();
      const selectFiles = event.target.files;

      for (let i = 0; i < selectFiles.length; i++) {
        const file = selectFiles[i];
        const preview = URL.createObjectURL(file);
        this.files.push({
          name: file.name,
          preview: preview,
        });
        formData.append("file", file);
      }
      axios.post(`${this.backendUrl}/uboard/upAdd`, formData, {
          headers: { "Content-Type": "multipart/form-data" },
        }).then(() => {
          alert('Files uploaded successfully.');
        }).catch(error => {
          alert(error.message);
        });
    },
    handleFileUpload(event) {
      this.selectedFile = event.target.files[0];
      this.imagePreview = URL.createObjectURL(this.selectedFile);
    },
    chkImg() {
      if (!this.selectedFile) {
        alert("이미지를 선택해주세요.");
        return;
      }

      const formData = new FormData();
      formData.append("file1", this.selectedFile);
      formData.append("category", this.imageName);

      axios.post('http://192.168.0.131:9000/thisisspam/insert_img', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then((response) => {
        console.log(response); // JSON 데이터를 콘솔에 출력
        this.imgdata = JSON.stringify(response.data, null, 2); // JSON 데이터를 문자열로 변환하여 저장
      })
      .catch((error) => {
        console.error(error);
        alert('이미지 처리 중 오류가 발생했습니다.');
      });
    },
  },
};
</script>

<style scoped>
.images img {
  width: 100px;
}

.information p {
  font-weight: bold; /* 글씨를 진하게 */
  font-size: 20px; /* 글씨 크기를 크게 */
  color: #333; /* 글씨 색상을 어둡게 */
}
</style>
