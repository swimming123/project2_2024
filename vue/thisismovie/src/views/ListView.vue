<template>
  <!-- 소개 섹션 -->
  <section id="about" class="light-bg wrap">
    <div class="container">
      <div class="row d-flex">
        <!-- 순서 뒤집기 추가 -->
        <div class="col-md-4 col-sm-12 col-xs-12">
          <div class="info-img">
            <img src="img/Shawshank.jpg" alt="Shawshank Redemption Poster" class="custom-img"/>
          </div>
        </div>
        <div class="col-md-8 col-sm-12 col-xs-12 info">
          <h1>List</h1>
          <h1 class="user-list-title text-center">Up List</h1>
          <div class="d-flex justify-content-end">
            <router-link to="/user/save" class="btn" style="background-color: #6c757d; color: white;">Add User</router-link>
          </div>
          <table class="table table-hover mt-3">
            <thead class="table-dark">
              <tr>
                <th>Number</th>
                <th>Title</th>
                <th>Writer</th>
                <th>Content</th>
                <th>hit</th>
                <th>reip</th>
                <th>bdate</th>
                <th>imgn</th>
              </tr>
            </thead>
            <tbody>
              <tr class="cursor-pointer" v-for="row in list" :key="row.num" @click="$event => href(row)">
                <td>{{row.num}}</td>
                <td>{{row.title}}</td>
                <td>{{row.writer}}</td>
                <td>{{row.content}}</td>
                <td>{{row.hit}}</td>
                <td>{{row.reip}}</td>
                <td>{{row.bdate}}</td>
                <td>{{row.imgn}}</td>
              </tr>
            </tbody>
            <!-- <tfoot>
              <tr><td colspan="9">{{list}}</td></tr>
            </tfoot> 리스트 표 밑에 보여지게 하는 부분 -->
          </table>
          <button class="btn btn-default mt-3" type="submit">
            View Works<i class="lnr lnr-chevron-down-circle"></i>
          </button>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import axios from 'axios'
export default {
  name: "list",
  created() {
    this.fetchData()
  },
  data() {
    return {
      list: []
    }
  },
  methods: {
    fetchData() {
      axios.get("http://192.168.0.39/thismovie0607/uboard/upList")
      .then((resp) => {
        this.list = resp.data
      })
      .catch((err) => {
        console.error("Error fetching data: ", err)
      })
    },
    href(row) {
      this.$router.push({name: 'DetailView', params: row})
    }
  }
}
</script>

<style scoped>
.user-list-title {
  font-family: 'Dancing Script', cursive; /* 필기체 글꼴 적용 */
  font-size: 40px; /* 글자 크기 조절 */
  color: #629bdb; /* 글자 색상 설정, 밝은 파란색으로 설정 */
  font-weight: bold; /* 글자를 더욱 굵게 */
}

/* 이미지의 크기 조절 */
.custom-img {
  width: 100%; /* 이미지의 너비를 컨테이너에 맞춤 */
  height: auto; /* 이미지의 높이는 자동으로 설정 */
  max-width: 300px; /* 이미지의 최대 너비 설정 */
  display: block; /* 이미지를 블록 요소로 설정하여 가운데 정렬이 적용되도록 함 */
}

/* 이미지 컨테이너의 크기 조절 */
.info-img {
  text-align: center; /* 이미지 컨테이너의 텍스트 정렬을 가운데로 설정 */
}
</style>

<link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap" rel="stylesheet">
