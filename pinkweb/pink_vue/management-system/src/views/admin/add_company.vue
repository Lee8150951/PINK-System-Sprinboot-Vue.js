<template>
  <div class="main-body">
    <div class="header-title">
      <i class="el-icon-document-copy"></i>
      新增公司 Company
    </div>
    <el-main class="main">
      <el-form ref="form" :model="form" label-width="80px">
        <el-row>
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="企业名称">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
              <el-form-item label="企业类型">
                <el-select v-model="form.type" placeholder="请选择企业类型">
                  <el-option label="合资企业" value="joint"></el-option>
                  <el-option label="独资企业" value="owned"></el-option>
                  <el-option label="国有企业" value="state"></el-option>
                  <el-option label="私营企业" value="private"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="法人代表">
                <el-input v-model="form.legalrepresentative"></el-input>
              </el-form-item>
              <el-form-item label="注册资本">
                <el-input v-model="form.registeredcapital">
                  <template slot="append">元</template>
                </el-input>
              </el-form-item>
              <el-form-item label="成立时间">
                <el-date-picker v-model="form.establishtime" type="date" placeholder="选择日期"></el-date-picker>
              </el-form-item>
              <el-form-item label="企业状态">
                <el-select v-model="form.operationstatus" placeholder="请选择企业状态">
                  <el-option label="在业" value="industry"></el-option>
                  <el-option label="停业" value="closed"></el-option>
                  <el-option label="吊销" value="revoked"></el-option>
                  <el-option label="清算" value="liquid"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="注册地址">
                <el-input v-model="form.registeredaddress"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="信用代码">
                <el-input v-model="form.unifiedcreditcode"></el-input>
              </el-form-item>
              <el-form-item label="经营范围">
                <el-select v-model="form.businessscope" placeholder="请选择经营范围">
                  <el-option label="农林牧渔业" value="agroforestry"></el-option>
                  <el-option label="制造业" value="manufacturing"></el-option>
                  <el-option label="能源业" value="energy"></el-option>
                  <el-option label="交通业" value="transportation"></el-option>
                  <el-option label="服务业" value="services"></el-option>
                  <el-option label="IT业" value="it"></el-option>
                  <el-option label="零售业" value="retail"></el-option>
                  <el-option label="地产业" value="estate"></el-option>
                  <el-option label="餐饮业" value="catering"></el-option>
                  <el-option label="其他" value="other"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="企业简介">
                <el-input type="textarea" :autosize="{ minRows: 10, maxRows: 10}" placeholder="请输入内容" v-model="form.introduction">
                </el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit" class="sub-btn">立即创建</el-button>
              </el-form-item>
            </div>
          </el-col>
        </el-row>
      </el-form>
    </el-main>
  </div>
</template>

<script>
export default {
  name: "add_company",
  data() {
    return {
      form: {
        name: '',
        type: '',
        legalrepresentative: '',
        registeredcapital: '',
        establishtime: '',
        operationstatus: '',
        registeredaddress: '',
        unifiedcreditcode: '',
        businessscope: '',
        introduction: ''
      }
    }
  },
  methods: {
    onSubmit() {
      console.log(this.form);
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/admin/admin_public.css";
@import "../../assets/css/admin/admin_form.css";
</style>
