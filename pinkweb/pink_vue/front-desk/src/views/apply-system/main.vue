<template>
  <div class="main">
    <header class="header" style="z-index: 1">
      <nav-menu>
        <nav-menu-item style="margin-left: 10%" path="/index/apply_home">首页</nav-menu-item>
        <nav-menu-item path="/index/apply_position">职位</nav-menu-item>
        <nav-menu-item path="/index/apply_company">公司</nav-menu-item>
        <!-- <nav-menu-item path="/index/apply_forum">论坛</nav-menu-item>
        <nav-menu-item path="/index/apply_application">APP</nav-menu-item>
        <nav-menu-item path="/index/apply_news">资讯</nav-menu-item> -->
        <el-submenu index="7" style="float: right">
          <template slot="title">
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
          </template>
          <nav-pull-item path="/index/apply_profile">个人信息</nav-pull-item>
          <nav-pull-item path="/premise/apply_login">切换账号</nav-pull-item>
        </el-submenu>
      </nav-menu>
    </header>
    <section class="main">
      <!-- <nav-bar style="z-index: 1"></nav-bar> -->
      <section class="route">
        <section style="z-index: 0">
          <router-view></router-view>
        </section>
        <footer-bar></footer-bar>
      </section>
    </section>
  </div>
</template>

<script>
// NavMenu组件
import NavMenu from "../../components/apply-system/navmenu/NavMenu";
// NavMenuItem组件
import NavMenuItem from "../../components/apply-system/navmenu/NavMenuItem";
// NavPullItem组件
import NavPullItem from "../../components/apply-system/navmenu/NavPullItem";
// NavBar组件
import NavBar from "../../components/apply-system/navbar/NavBar";
// Footer组件
import FooterBar from "../../components/apply-system/footer/Footer";
export default {
  name: "main",
  components: {NavPullItem, NavMenuItem, FooterBar, NavBar, NavMenu},
  data() {
    return {
      activeIndex: '1',
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/apply-system/main.css";
</style>
