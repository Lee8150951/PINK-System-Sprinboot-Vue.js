<template>
  <el-menu-item @click="itemClick">
    <slot></slot>
  </el-menu-item>
</template>

<script>
  export default {
    name: "NavPullItem",
    props: {
      path: String
    },
    methods: {
      itemClick() {
        this.$router.push(this.path)
      }
    }
  }
</script>

<style scoped>

</style>
