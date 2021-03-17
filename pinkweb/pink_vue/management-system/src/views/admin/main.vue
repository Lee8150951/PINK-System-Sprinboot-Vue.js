<template>
  <el-row class="height-all all-bgc">
    <el-col :span="4" class="height-all">
      <div class="aside-bar">
        <div class="logo-col">
          <el-row>
            <el-col :span="5">
              <img src="../../assets/img/logo/logo_white.png" alt="logo" class="company-logo">
            </el-col>
            <el-col :span="19">
              <ul>
                <li class="main-title">PINK-panel</li>
              </ul>
            </el-col>
          </el-row>
        </div>
        <div class="feature-list">
          <ul>
            <aside-bar-item v-for="(item, index) in items" :key="item.index" :item="item"></aside-bar-item>
          </ul>
        </div>
      </div>
    </el-col>
    <el-col :span="20" class="height-all">
      <div class="right-content">
        <div class="nav-bar">
          <el-row>
            <el-col :span="22">
              <div class="search-col">
                <el-input prefix-icon="el-icon-search" v-model="search" class="search-input"></el-input>
              </div>
            </el-col>
            <el-col :span="2">
              <div class="avatar-col">
                <avatar-item></avatar-item>
              </div>
            </el-col>
          </el-row>
        </div>
        <div class="main-body">
          <router-view></router-view>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import AsideBarItem from "../../components/admin/asidebar/AsideBarItem";
import AvatarItem from "../../components/admin/navmenu/AvatarItem";
export default {
  name: "main",
  components: {AvatarItem, AsideBarItem},
  data() {
    return {
      search: '',
      items: [
        {
          index: 0,
          path: '/admin/admin_home',
          icon: 'el-icon-odometer',
          title: '总览 Dashboard'
        },
        {
          index: 1,
          path: '/admin/manage_company',
          icon: 'el-icon-document-copy',
          title: '公司 Company'
        },
        {
          index: 2,
          path: '/admin/manage_user',
          icon: 'el-icon-user',
          title: '用户 User'
        },
        {
          index: 3,
          path: '/admin/manage_admin',
          icon: 'el-icon-set-up',
          title: '员工 Admin'
        },
        {
          index: 4,
          path: '/admin/admin_profile',
          icon: 'el-icon-setting',
          title: '我的 Profile'
        },
      ]
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/admin/admin_main.css";
</style>
