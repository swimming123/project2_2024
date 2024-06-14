<template>
  <!-- 헤더 영역 -->
  <!-- "header_area" 클래스를 포함하는 최상위 div 태그. 이 클래스는 스타일링을 위해 사용 -->
  <div class="header_area">
     <!-- 네비게이션 바 -->
    <nav class="navbar">
      <router-link to="/">Home</router-link> |
      <!-- <router-link to="/about">About</router-link> | -->
      <router-link to="/addList">Add Posters</router-link> |
      <router-link to="/list">UpList</router-link> |
      <!-- <router-link to="/member">MemList</router-link> | -->
      <!-- <router-link to="/profile">Profile</router-link> | -->
      <!-- <router-link to="/fileup">FileUpLoad</router-link> -->
    </nav>
    <!-- Bootstrap의 "container" 클래스를 사용하여 내용을 가운데 정렬하고 패딩을 제공 -->
    <div class="container">
      <!-- "row" 클래스는 가로 줄을 만들어 한 줄에 여러 컬럼을 배치할 수 있게 해줌 -->
      <div class="row">
        <!-- "col-sm-12 col-md-12" 클래스는 반응형 웹 디자인을 지원하며, 모든 화면 크기에서 전체 폭을 사용하도록 설정 -->
        <div class="col-sm-12 col-md-12">
          <!-- "homepage text-center" 클래스를 사용하여 텍스트를 중앙에 배치 -->
          <div class="homepage text-center">
            <!-- 헤더의 주된 내용을 담는 "main_header" div -->
            <div class="main_header">
              <h2>ICT</h2>
              <h3>Group Project</h3>
              <p>June 10 - June 18, 2024</p>
              <!-- 사용자 행동 유도 버튼 -->
              <button class="btn btn-default btn-transparent" @click="goToList" type="button">
                Let's watch a movie tonight!
              </button>
            </div>
            <!-- 아이콘을 사용한 하이퍼링크. 일반적으로 페이지의 다른 섹션으로 스크롤하도록 설정 가능
            <a href="#"><i class="lnr lnr-chevron-down-circle"></i></a> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Header",
  methods: {
    goToList() {
      this.$router.push({ name: 'list' });
    },
  },
};
/*
export default {
  name: "Header", // 컴포넌트의 이름을 "Header"로 설정
  props: {
    msg: String, // 부모 컴포넌트로부터 전달받을 메시지 문자열을 위한 prop
  },
};*/
</script>

<style scoped>
/* 네비게이션 바 스타일 */
.navbar {
  background-color: #f8f9fa;
  padding: 10px 0;
  font-size: 19px;
  text-align: center;
  border-bottom: 1px solid #e7e7e7;
}

.navbar a, .navbar a.router-link-exact-active {
  margin: 0 10px;
  text-decoration: none;
  color: #555;
  font-weight: bold; /* 글씨를 진하게 */
  transition: color 0.3s; /* 색상 변화에 애니메이션 효과를 적용 */
}

.navbar a:hover {
  color: #ee3a0d; /* 마우스 오버 시 색상 변경 */
  text-decoration: none; /* 호버 시 밑줄 제거 */
}


/* 여기에는 헤더 컴포넌트에 특화된 CSS 스타일을 추가. "scoped" 속성은 이 스타일이 현재 컴포넌트에만 적용됨을 의미 */

/* src/components/pageHeader.vue
Vue.js에서 단일 파일 컴포넌트(Single File Component)로 구성된 헤더 컴포넌트
헤더는 웹 페이지의 상단에 위치하며, 특정 이벤트나 프로젝트 정보를 제공하고 사용자에게 특정 행동을 취하도록 유도
이 구조는 기본적으로 컴포넌트 기반의 설계를 따르며, 각 컴포넌트는 자체적으로 독립적인 기능과 스타일을 가지고 있음.
*/
</style>
