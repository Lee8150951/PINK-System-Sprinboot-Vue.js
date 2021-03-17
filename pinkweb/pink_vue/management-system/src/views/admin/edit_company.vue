<template>
  <div class="main-body">
    <div class="header-title">
      <i class="el-icon-document-copy"></i>
      公司详情 Company
    </div>
    <el-main class="main">
      <el-form ref="form" :model="form" label-width="80px">
        <el-row>
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="企业名称">
                <el-input v-model="form[0].e_name" :value="form[0].e_name" :placeholder="form[0].e_name" :disabled="true" ></el-input>
              </el-form-item>
              <el-form-item label="企业类型">
                <el-input v-model="form[0].e_type" placeholder="请选择企业类型" :disabled="true">
                  <!-- <el-option label="合资企业" value="joint"></el-option>
                  <el-option label="独资企业" value="owned"></el-option>
                  <el-option label="国有企业" value="state"></el-option>
                  <el-option label="私营企业" value="private"></el-option> -->
                </el-input>
              </el-form-item>
              <el-form-item label="法人代表">
                <el-input v-model="form[0].e_legalrepresentative" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="注册资本">
                <el-input v-model="form[0].e_registeredcapital" :disabled="true">
                  <template slot="append">元</template>
                </el-input>
              </el-form-item>
              <el-form-item label="成立时间" >
                <el-date-picker v-model="form[0].e_establishtime" type="date" placeholder="form[0].e_establishtime" :disabled="true"></el-date-picker>
              </el-form-item>
              <el-form-item label="企业状态">
                <el-input v-model="form[0].e_operationstatus" placeholder="请选择企业状态" :disabled="true">
                  <!-- <el-option label="在业" value="industry"></el-option>
                  <el-option label="停业" value="closed"></el-option>
                  <el-option label="吊销" value="revoked"></el-option>
                  <el-option label="清算" value="liquid"></el-option> -->
                </el-input>
              </el-form-item>
              <el-form-item label="注册地址">
                <el-input v-model="form[0].e_registeredaddress" :disabled="true"></el-input>
              </el-form-item>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content">
              <el-form-item label="信用代码">
                <el-input v-model="form[0].e_unifiedcreditcode" :disabled="true"></el-input>
              </el-form-item>
              <!-- <el-form-item label="经营范围">
                <el-select v-model="form.businessscope" :placeholder="$route.query.type">
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
              </el-form-item> -->
              <el-form-item label="企业简介">
                <el-input type="textarea" :autosize="{ minRows: 10, maxRows: 10}" placeholder="请输入内容" v-model="form[0].e_introduction" :disabled="true">
                </el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onBack" class="sub-btn">返回公司列表</el-button>
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
  name: "edit_admin",
  data() {
    return {
      test: "123",
      form: [
        // {
        // name: '',
        // type: '',
        // legalrepresentative: '',
        // registeredcapital: '',
        // establishtime: '',
        // operationstatus: '',
        // registeredaddress: '',
        // unifiedcreditcode: '',
        // businessscope: '',
        // introduction: ''
        // }
      ]
    }
  },
  created(){
    const v = this
    this.$http.get('http://localhost:8085/index/company_detail/'+this.$route.query.e_id)
      .then(function (response) {
        v.form.push(response.data)
        console.log(v.form[0])

      })
      .catch(function (error) {
        console.log(error);
      });
  },
  methods: {
    onBack() {
        this.$router.push({
        path: '/admin/manage_company',
        query: {
          key: '',
          flag: 'undefined'
        }
      })
      location.reload()
    }
  }
}
</script>

<style scoped>
@import "../../assets/css/admin/admin_public.css";
@import "../../assets/css/admin/admin_form.css";
</style>
