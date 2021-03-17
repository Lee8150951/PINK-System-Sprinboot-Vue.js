<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted() {
    let beginTime = 0; //开始时间
    let differTime = 0; //时间差
    window.onunload = function() {
      differTime = new Date().getTime() - beginTime;
      if (differTime <= 5) {
        localStorage.removeItem('token');

        console.log("这是关闭");
      } else {
        console.log("这是刷新");
      }
    };

    window.onbeforeunload = function() {
      beginTime = new Date().getTime();
    };
  }

}
</script>

<style>

</style>
