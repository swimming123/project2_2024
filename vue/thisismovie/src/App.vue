<template>
  <div>
    <pageHeader />   <!-- 컴포넌트 태그는 자동으로 '-'로 변환되어 인식됩니다 -->
    <router-view />
    <pageFooter />
  </div>

</template>
<script>
import pageHeader from './components/pageHeader.vue'
import pageFooter from './components/pageFooter.vue'
export default {
  name: 'App',
  components: {
    pageHeader,  // PascalCase 사용
    pageFooter
  }
}
</script>


