<template>
  <div id="register" :style="back">
    <div class="loginConbox">
            <img src="@/assets/img/login.jpg" alt="" class="loginimg" width="100%">

       <div class="header">
        <div class="header-left">
        </div>
        <div class="header-right">
          <p>msy酒店</p>
        </div>
      </div>
      <div class="registerBox">
        <div class="registerCon">
          <el-card shadow="always" class="register-module" v-if="smdl">
            <div slot="header" class="clearfix formTitlt">
              <span>用户注册</span>
            </div>
            <el-form
              :model="loginForm"
              :rules="rules"
              status-icon
              label-width="100px"
              class="demo-ruleForm"
            >
             <el-form-item prop="username">
                <el-input
                  type="text"
                  v-model="loginForm.username"
                  auto-complete="off"
                  placeholder="请输入用户名"
                ></el-input>
              </el-form-item>
             <el-form-item prop="pass">
                <el-input
                  type="password"
                  v-model="loginForm.pass"
                  auto-complete="off"
                  placeholder="请输入密码"
                ></el-input>
              </el-form-item>
               <el-form-item prop="checkpass">
                <el-input
                  type="password"
                  v-model="loginForm.checkpass"
                  auto-complete="off"
                  placeholder="请输入确认密码"
                ></el-input>
              </el-form-item>
              <el-form-item prop="phone">
                <el-input
                  type="text"
                  v-model="loginForm.phone"
                  auto-complete="off"
                  placeholder="请输入手机号"
                ></el-input>
              </el-form-item>
              <el-form-item prop="code">
                <el-input
                  type="text"
                  v-model="loginForm.code"
                  auto-complete="off"
                  placeholder="请输入验证码"
                >
                </el-input>
                <el-button round size="small" class="getCode" @click="getCode">获取验证码{{ n }}</el-button>
              </el-form-item>
             
               <el-form-item>
                <el-button class="subBtn" type="primary" @click="submitForm"
                  >注册</el-button
                >
              </el-form-item>
              <p class="smalltxt">
                <router-link class="a" to="/login">已有账号-返回登录</router-link>
              </p>
            </el-form>
          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
       var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.loginForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
    return {
      back:{
        backgroundImage:"url('../../assets/img/logo.jfif')",
        backgroundSize:"100% 100%"
      },
      n: "",
      timer: "",
      code_server:"",
      smdl: true,
      loginForm: {
          username:"",
          pass:"",
          checkpass:"",
          phone: "",
          code: ""
      },
       rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkpass: [
            { validator: validatePass2, trigger: 'blur' }
          ]
        }
    }
  },

  
  watch: {
    n(newvarl) {
      if (newvarl == 0) {
        //清除定时器
        clearInterval(this.timer);
        this.n = "";
      }
    },
  },
  methods: {
      getCode() {
      if (this.loginForm.phone == "") {
        alert("手机号码不能为空！");
        return;
      }
      //可以发送请求
      if (this.n == "") {
        //发送请求获取验证码
        this.$axios.get('/hotel/getCode',{
            params:{
                phone:this.loginForm.phone
            }
        }).then((res) => {
            console.log(res)
            if(res.data.flag){//如果获取验证码成功
                this.code_server=res.data.obj
            }else{
                this.n=''
                alert(res.data.msg)
            }
        }).catch(error=>{
            console.log(error)
        });
        //60秒
        this.n = 60;
        this.timer = setInterval(() => {
          this.n--;
        }, 1000);
      }
    },

    submitForm() {
      if (this.loginForm.phone == "") {
        alert("手机号码不能为空！");
        return;
      }
      if (this.loginForm.model == "") {
        alert("密码不能为空！");
        return;
      }
      if(this.loginForm.code==this.code_server){
          this.$axios.get('/hotel/user/register',
          {
            params:{
                userName:this.loginForm.username,
                password:this.loginForm.pass,
                phone:this.loginForm.phone
            }
          }).then(res=>{
            console.log(res.data)
            if(res.data.code==700){
                alert("未知错误！")
            }
            if(res.data.flag){
            //   this.$setSessionStorage('user',res.data.obj)
              this.$router.push('/login')
            }else{//失败                                                   
              alert("该手机已被注册！")

             }
          }).catch(error=>{
            console.log(error)
          })
      }else{
          alert("验证码错误")
      }
    },
  },
}
</script>

<style>
#app {
    position: absolute;
    top: 0px;
    bottom: 0px;
    width: 100%;
    margin-top: 0px;
}
.loginimg{
  position: fixed;
}

#register {
  width: 100%;
  height: 100%;

}
#register .registerConbox {
  background: #ffffff;
}

#register .header {
  height: 60px;
  position: relative;
  /*border-bottom: 1px solid rgba(255, 255, 255, 0.3);*/
}

#register .header .header-left{
  float: left;
  width: 15%;
}

#register .header .header-right{
  float: right;
  width: 85%;
}


#register .header .header-right p{
  float: left;
  font-weight: bold;
  line-height: 60px;
  font-size: 16px;
}


#register .header .logo {
  margin-left: 30px;
  width: 500px;
  float: left;
  height: 40px;
  padding-top: 10px;
}

#register .header .logo img {
  height: 100%;
}
#register .registerBox {
  padding: 74px 0 118px;
   background: #ffffff;
}
#register .registerBox .iconcolor {
  color: #409eff;
}
#register .registerCon {
  width: 990px;
  margin: auto;
  position: relative;
  height: 600px;
  
}
#register .el-card__header {
  border-bottom: 0px;
}

#register .title {
  font-size: 36px;
  font-weight: 600;
  color: #ffffff;
  width: 500px;
  float: left;
  margin-top: 0px;
}
#register .title:nth-of-type(1) {
  font-size: 34px;
  margin-top: 50px;
  margin-bottom: 30px;
}
#register .register-module {
  width: 380px;
  height: 500px;
  margin-top: 60px;
  border: none;
  position: absolute;
  right: 0;
}
#register .register-module .formTitlt {
  font-size: 18px;
  font-weight: 400;
}
#register .register-module .titIconbox {
  float: right;
}
#register .register-module .titIconbox .pointer {
  cursor: pointer;
}

#register .smalltxt {
  text-align: right;
}

#register .smalltxt .a {
  text-decoration: none;
  color: #999999;
  font-size: 12px;
  margin-left: 8px;
}
#register .getCode {
  top: 25vw;
  right: 10vw;
}

#register .el-form .el-form-item:nth-of-type(5) .el-form-item__content .el-button {
  position: absolute;
  width: 100px;
  top: 4px;
  border-radius: 5px;
  right: 8px;
}

#register .el-form-item__content {
  margin-left: 0px !important;
}

#register .el-form-item__content .subBtn {
  width: 100%;
}

#register .el-input__inner,
.el-button,
.el-card,
.el-message {
  border-radius: 0px !important;
}

#register .el-form-item__content .ico {
  position: absolute;
  top: 0px;
  left: 0px;
  z-index: 999;
  width: 40px;
  height: 39px;
  text-align: center;
  border-right: 1px solid #ccc;
}

#register .ewmbox {
  width: 100%;
  height: 240px;
  margin-top: -25px;
}

#register .ewm {
  width: 140px;
  height: 140px;
  margin: 20px auto;
}
#register .ewmbox .ewm p {
  font-size: 12px;
  padding-left: 40px;
  margin: 0;
}
#register .ewmicon {
  width: 140px;
  margin: 20px auto 0;
}

#register .ewmicon .iconfont {
  float: left;
}

#register .ewmicon p {
  font-size: 12px;
  padding-left: 40px;
  margin: 0;
}
</style>