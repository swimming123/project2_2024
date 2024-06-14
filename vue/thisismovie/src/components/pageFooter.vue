<template>
  <section class="footer_area">
    <div class="container">
      <div class="footer-main">
        <div class="row">
          <div class="col-md-6 col-sm-6 col-xs-12 all_rights">
            <p class="contributors">Project Contributors: Yoon Sooyoung | Jung Junyoung | Song Jimi</p>
          </div>
          <div class="col-md-6 col-sm-6 col-xs-12">
            <div class="social">
              <ul class="list-inline pull-right">
                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                <li><a href="#"><i class="fa fa-behance"></i></a></li>
                <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'Footer',
  props: {
    msg: String
  }
}
</script>

<style scoped>
.contributors {
  font-family: 'Dancing Script', cursive; /* 적용할 필기체 글꼴 */
  font-size: 23px; /* 글자 크기 */
  color: #15b7bd; /* 글자 색상 */
}
</style>

<!-- Google Fonts 링크 추가 -->
<link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap" rel="stylesheet">
