<template>
  <el-row class="height-all all-bgc">
    <el-col :span="4" class="height-all">
      <div class="left-content height-all">
        <div class="logo-col">
          <img src="../../assets/img/logo/logo_03.png" alt="logo" class="logo">
          <span class="logo-title">PINK</span>
        </div>
        <div class="feature-col">
          <ul>
            <nav-menu-item v-for="(item, index) in navMenuItems" :key="item.index" :item="item"></nav-menu-item>
          </ul>
        </div>
      </div>
    </el-col>
    <el-col :span="20">
      <div class="right-content">
        <el-row class="top-menu">
          <el-col :span="12">
            <div class="left-search-content">
              <el-input placeholder="Search..." v-model="search">
                <el-button slot="append" icon="el-icon-search"></el-button>
              </el-input>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="right-search-content">
              <span>
                <el-badge :value="23" :max="99" class="item-badge">
                  <el-button type="text" class="top-btn" @click="turnChat">
                    <i class="el-icon-chat-dot-round"></i>
                  </el-button>
                </el-badge>
              </span>
              <avatar-item></avatar-item>
            </div>
          </el-col>
        </el-row>
        <div class="main-body">
          <router-view></router-view>
          <drawer-box></drawer-box>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
// 导入NavMenuItem模块
import NavMenuItem from "../../components/hire-system/navmenu/NavMenuItem";
// 导入AvatarItem模块
import AvatarItem from "../../components/hire-system/navmenu/AvatarItem";
// 导入DrawerBox模块
import DrawerBox from "../../components/hire-system/drawer/DrawerBox";
export default {
  name: "main",
  components: {DrawerBox, AvatarItem, NavMenuItem},
  data() {
    return {
      search: '',
      navMenuItems: [
        {
          index: '0',
          title: '总览 Dashboard',
          icon: 'el-icon-odometer',
          path: '/hire/hire_home'
        },
        {
          index: '1',
          title: '招揽 Attract',
          icon: 'el-icon-attract',
          path: '/hire/hire_attract'
        },
        {
          index: '2',
          title: '发布 Release',
          icon: 'el-icon-position',
          path: '/hire/hire_release'
        },
        {
          index: '3',
          title: '讯息 Chats',
          icon: 'el-icon-chat-round',
          path: '/hire/hire_chat'
        },
        {
          index: '4',
          title: '个人 Profile',
          icon: 'el-icon-user',
          path: '/hire/hire_profile'
        },
        // {
        //   index: '5',
        //   title: '测试 Test',
        //   icon: 'el-icon-user',
        //   path: '/hire/test'
        // },
      ]
    }
  },
  methods: {
    turnChat() {
      this.$router.push({
        path: '/hire/hire_chat'
      })
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/hire-system/main.css";
</style>
