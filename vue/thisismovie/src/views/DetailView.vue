<template>
  <div class="contact_area wrap">
    <div class="container">
      <div class="row">
        <!-- 왼쪽 섹션: 소개 및 메시지 -->
        <div class="col-md-4 col-sm-6 col-xs-12">
          <div class="main_contact">
            <h1 class="display-1 text-center">{{ msg }}</h1>
            <p>
              Here you can view detailed user information. Please contact support if you need further assistance.
            </p>
            <img v-if="imageSrc" :src="imageSrc" alt="Image" class="img-thumbnail" style="max-width: 400px; max-height: 500px;">
      </div>
        </div>
        <!-- 오른쪽 섹션: 사용자 세부 정보 폼 -->
        <div class="col-md-8 col-sm-6 col-xs-12">
          <div class="main_contact2">
            <form v-if="result.imgn && Object.keys(result).length">
              <!-- 숨겨진 필드: 사용자 번호 -->
              <input type="hidden" name="num" :value="result.num" readonly>
              <div class="mb-3 mt-3">
                <label for="title" class="form-label">Title:</label>
                <input type="text" class="form-control" id="title" name="title" readonly :value="result.title" style="color: blue;" />
              </div>
              <div class="mb-3 mt-3">
                <label for="writer" class="form-label">Writer:</label>
                <input type="text" class="form-control" id="writer" name="writer" readonly :value="result.writer" style="color: green;" />
              </div>
              <div class="mb-3 mt-3">
                <label for="content" class="form-label">Content:</label>
                <textarea class="form-control" id="content" name="content" readonly style="color: orange;" :value="result.content"/>
              </div>
              <div class="mb-3 mt-3">
                <label for="hit" class="form-label">Hit:</label>
                <input type="text" class="form-control" id="hit" name="hit" readonly :value="result.hit" style="color: purple;" />
              </div>
              <div class="mb-3 mt-3">
                <label for="reip" class="form-label">Reip:</label>
                <input type="text" class="form-control" id="reip" name="reip" readonly :value="result.reip" style="color: brown;" />
              </div>
              <div class="mb-3 mt-3">
                <label for="bdate" class="form-label">Date:</label>
                <input type="text" class="form-control" id="bdate" name="bdate" readonly :value="result.bdate" style="color: brown;" />
              </div>
              <div class="mb-3 mt-3">
                <label for="imgn" class="form-label">Image Name:</label>
                <input type="text" class="form-control" id="imgn" name="imgn" readonly :value="result.imgn" style="color: teal;" />
              </div>
              <button class="btn btn-default" type="button" @click="goBack">
                Return<i class="lnr lnr-chevron-right-circle"></i>
              </button>
            </form>
            <div v-else>
              Loading...
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  created(){
    this.fetchData(this.$route.params.num);
  },
  data() {
    return {
      msg: "UpList 상세보기",
      result: {},
      imageSrc: null // 이미지 소스 변수 추가
    }
  },
  methods: {
    fetchData(num) {
      console.log(num);
      axios.get(`http://192.168.0.39/thismovie0607/uboard/upDetail/${num}`)
        .then((resp) => {
          // console.log("resp.data!!!", resp.data);
          this.result = resp.data;
          // 이미지 URL 설정
          if (this.result.imagePath) {
            this.imageSrc = `http://192.168.0.39/thismovie0607/uploads/${this.result.imgn}`;
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    goBack() {
      this.$router.push({name: 'list'});
    }
  }
}
</script>
