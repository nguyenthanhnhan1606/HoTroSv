<template>
  <router-view />
  <footer >
    <MyFooter />
  </footer>
</template>



<script>
import MyFooter from '../src/components/Footer.vue'
export default {
  components:{
    MyFooter
  }
}
</script>

