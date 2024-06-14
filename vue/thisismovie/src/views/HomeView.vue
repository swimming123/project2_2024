<template>
  <!-- Vue.js 템플릿 시작 -->
  <!-- "home" 클래스를 가진 최상위 div, 페이지의 메인 컨테이너 역할 -->
  <div class="home">
    <!-- 기타 컨텐츠 및 구조를 위한 div -->
    <div>
      <!-- 로딩 스피너를 보여주기 위한 div, 페이지 로드 시 사용자에게 표시 -->
      <div class="preloader"><div class="loaded">&nbsp;</div></div>
    </div>
  </div>
  <!-- 포트폴리오 영역 -->
  <!-- 메인 마케팅 메시지 또는 행동 촉구를 위한 포트폴리오 섹션 -->
  <div id="portfolio_area" class="wrap">
    <div class="container">
      <div class="row">
        <div class="portfolio">
          <!-- 각각의 포트폴리오 아이템들을 나열, 프로젝트 또는 작품을 보여줌 -->
          <div class="col-md-4 col-sm-6 col-xs-12">
             <div class="portfolio-item">
    <h3> "Detecting <span class="highlight">spam</span> keywords"</h3>
  </div>

              <br>
              <h4> "스팸단어 구분하기"</h4>
              <br>
              <p>
                상세설명 기록 예정
              
              </p>
              <img src="img/pf1.jpg" alt="" class="grayscale" />
            </div>
          </div>
          <!-- 추가 포트폴리오 아이템들, 각 아이템은 프로젝트 또는 작품 이미지 포함 -->
          <!-- ... 추가 아이템 코드 반복 ... -->
          <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="portfolio-item">
              <img src="img/pf2.jpg" alt="" class="grayscale" />
            </div>
          </div>

          <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="portfolio-item">
              <img src="img/pf3.jpg" alt="" class="grayscale" />
            </div>
          </div>

          <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="portfolio-item">
              <img src="img/pf4.jpg" alt="" class="grayscale" />
            </div>
          </div>

          <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="portfolio-item">
              <img src="img/pf6.jpg" alt="" class="grayscale" />
            </div>
          </div>

          <div class="col-md-4 col-sm-6 col-xs-12">
            <div class="portfolio-item">
              <img src="img/pf5.jpg" alt="" class="grayscale" />
            </div>
          </div>
        </div>
      </div>
    </div>

</template>

<script>
// Vue 컴포넌트 정의
import HelloWorld from "@/components/HelloWorld.vue";// HelloWorld 컴포넌트를 임포트

export default {
  name: "HomeView",  // 컴포넌트의 이름 지정
  components: {
    HelloWorld, // 사용할 컴포넌트 등록
  },
};
</script>

<style scoped>


/* 텍스트 하이라이트 스타일 */
.highlight {
  color: red !important; /* !important를 추가하여 우선순위 강제 적용 */
}


/* 흑백 이미지 스타일 */
.grayscale {
  filter: grayscale(100%); /* 모든 색상을 그레이스케일로 변환 */
}


/* 포트폴리오 영역 배경색 설정 */
#portfolio_area {
    background-color: #f5f5f5; /* 연한 그레이 색상 */
}

/* 컴포넌트 스타일을 여기에 추가, scoped는 스타일이 해당 컴포넌트에만 적용됨을 의미 */

/*HomeView.vue
HomeView.vue는 웹 페이지의 메인 화면을 구성
각 섹션은 사용자에게 다양한 정보를 제공하고, 포트폴리오를 통해 사용자의 작업을 보여주는 데 중점을 둠.
*/
</style>
