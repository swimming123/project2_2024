<template>
    <div class="contact_area wrap">
      <div class="container">
        <div class="row">
          <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="main_contact">
                <h1>Movie Poster Upload</h1>
                <h4>Warning</h4>
                <p>Commercial content will not be uploaded.</p>
                <br>
                <h4>image Preview</h4>
                <img v-if="files.length > 0" v-bind:src="files[0].preview" class="img-thumbnail" style="max-width: 300px; max-height: 500px;"></div>
          </div>
  
          <div class="col-md-8 col-sm-6 col-xs-12">
            <div class="main_contact2">
              <div class="information">
                <form @submit.prevent="submitForm">
                <!-- 
                Number	Title	Writer	Content	hit	reip	bdate	imgn
                -->
                <span class="label label-default">Title</span>
                <input type="text" class="form-control" v-model="title" placeholder="글 제목">
                <br>
                <span class="label label-default">Writer</span>
                <input type="text" class="form-control" v-model="writer" placeholder="작성자">
                <br>
                <span class="label label-default">Content</span>
                <textarea class="form-control" v-model="content" placeholder="내용입력" rows="4"></textarea>
                <br>
                <span class="label label-default">imageName</span>
                <input type="text" class="form-control" v-model="imageName" placeholder="imageName">
                <br>
                <input class="form-control" @change="handleFileChange" multiple accept="image/*" ref="fileRef" type="file">
                <br>
                <span class="label label-default">User IP</span>
                <input type="text" class="form-control" v-model="reip" placeholder="아이피 받아오는 것으로 바꾸기">
                <br>
                <button type="button" class="btn btn-default" @click="chkImg">Check Img<i class="lnr lnr-chevron-right-circle"></i></button>
                <br>
                <button class="btn btn-default" type="submit" :disabled="!isImgDataValid">U P L O A D<i class="lnr lnr-chevron-right-circle"></i></button>
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
        title:null,
        writer:null,
        content:null,
        reip:null,
        files: [],
        imgdata: null,
        selectedFiles: null,
        imageName:null,
        backendUrl: "http://192.168.0.39/thismovie0607",
      };
    },
    computed: {
      isImgDataValid() {
        try {
        if (!this.imgdata) return false;
          const imgDataParsed = JSON.parse(this.imgdata);
          return imgDataParsed["spam"] === 0;
        } catch (e) {
          return false;
        }
      }
    },
    methods: {
        handleFileChange(event) {
            this.selectedFiles = event.target.files || [];
            // 파일 선택 후 미리보기 추가
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
            axios.post('http://192.168.0.36:9000/thisisspam/insert_img', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then((response) => {
                console.log(response); // JSON 데이터를 콘솔에 출력
                this.imgdata = JSON.stringify(response.data, null, 2); // JSON 데이터를 문자열로 변환하여 저장
                const imgDataParsed = JSON.parse(this.imgdata)["spam"];
                console.log("imgdata"+this.imgdata)
                if (imgDataParsed === 1) {
                  alert('상업적인 사진은 업로드되지 않습니다.');
                }
            })
            .catch((error) => {
                console.error(error);
                alert('이미지 처리 중 오류가 발생했습니다.');
            });
        }, 
        submitForm() {
          if (!this.isImgDataValid) {
            alert("유효한 이미지 데이터를 확인해주세요.");
            return;
          }
          const formData = new FormData();
          for (let i = 0; i < this.selectedFiles.length; i++) {
            const file = this.selectedFiles[i];        
            // const preview = URL.createObjectURL(file);        
            // this.files.push({
            //   name: file.name,
            //   preview: preview,
            // });
            for (let i = 0; i < this.selectedFiles.length; i++) {
              formData.append('file', this.selectedFiles[i]);
            }
            // formData.append('file', file);
            formData.append("title", this.title);        // 제목 추가
            formData.append("writer", this.writer);      // 작성자 추가
            formData.append("content", this.content);    // 내용 추가
            formData.append("reip", this.reip);          // 사용자 IP 추가

            axios.post(`${this.backendUrl}/uboard/upAdd`, formData, {
              headers: { "Content-Type": "multipart/form-data" },
            })
          .then(() => {
            this.fetchFiles();
            this.$router.push({ name: 'list'});
            // alert('데이터가 성공적으로 업로드되었습니다.');
          })
          .catch(error => {
            alert(error.message);
            alert('데이터 업로드 중 오류가 발생했습니다.');
          });
        }
    },
    fetchFiles() {
    },
  }

  };
</script>