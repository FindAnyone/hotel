<template>
  <div id="login">
    <div class="loginConbox">
      <img src="@/assets/img/login.jpg" alt="" class="loginimg" width="100%" />
      <div class="header">
        <div class="header-left"></div>
        <div class="header-right">
          <p>msy酒店</p>
        </div>
      </div>
      <div class="loginBox" v-show="show">
        <div class="loginCon">
          <el-card shadow="always" class="login-module" v-if="smdl">
            <div slot="header" class="clearfix formTitlt">
              <span>密码登录</span>
              <span> | </span>
              <span class="way" @click="codeway">验证码登录</span>
            </div>
            <el-form
              :model="loginForm"
              status-icon
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item>
                <el-input
                  type="text"
                  v-model="loginForm.userid"
                  auto-complete="off"
                  placeholder="请输入账号"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <el-input
                  type="text"
                  v-model="loginForm.password"
                  auto-complete="off"
                  placeholder="请输入密码"
                >
                </el-input>
              </el-form-item>
              <el-form-item>
                <el-button class="subBtn" type="primary" @click="submitPass"
                  >登录</el-button
                >
              </el-form-item>
              <p class="smalltxt">
                <router-link class="a" to="#">忘记密码</router-link>
                <router-link class="a" to="/register">免费注册</router-link>
              </p>
            </el-form>
          </el-card>
        </div>
      </div>

      <div class="loginBox" v-show="show2">
        <div class="loginCon">
          <el-card shadow="always" class="login-module" v-if="smdl">
            <div slot="header" class="clearfix formTitlt">
              <span>验证码登录</span>
              <span> | </span>
              <span class="way" @click="passway">密码登录</span>
            </div>
            <el-form
              :model="loginForm"
              status-icon
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item>
                <el-input
                  type="text"
                  v-model="loginForm.phone"
                  auto-complete="off"
                  placeholder="请输入手机号"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <el-input
                  type="text"
                  v-model="loginForm.code"
                  auto-complete="off"
                  placeholder="请输入验证码"
                >
                </el-input>
                <el-button round size="small" class="getCode" @click="getCode"
                  >获取验证码{{ n }}</el-button
                >
              </el-form-item>
              <el-form-item>
                <el-button class="subBtn" type="primary" @click="submitForm"
                  >登录</el-button
                >
              </el-form-item>
              <p class="smalltxt">
                <router-link class="a" to="/register">免费注册</router-link>
              </p>
            </el-form>
          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getLogin, getCode, getPassLogin } from "@/api/login";
export default {
  data() {
    return {
      show: false,
      show2: true,
      n: "",
      timer: "",
      code_server: "",
      smdl: true,
      loginForm: {
        phone: "18178303876",
        code: "",
        userid: "",
        password: "",
      },
    };
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
        const param = {
          phone: this.loginForm.phone,
        };
        getCode(param)
          .then((res) => {
            console.log(res);
            if (res.data.flag) {
              //如果获取验证码成功
              this.code_server = res.data.obj;
            } else {
              this.n = "";
              alert(res.data.msg);
            }
          })
          .catch((error) => {
            console.log(error);
          });
        //60秒
        this.n = 60;
        this.timer = setInterval(() => {
          this.n--;
        }, 1000);
      }
    },
    submitPass() {
      const param = {
        userId: this.loginForm.userid,
        password: this.loginForm.password,
      };
      getPassLogin(param)
        .then((res) => {
          console.log(res.data);
          if (res.data.flag) {
            this.$setSessionStorage("user", res.data.obj);
            this.$router.push("/roomlist");
          } else {
            //失败
            alert("服务器错误");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    submitForm() {
      if (this.loginForm.phone == "") {
        alert("手机号码不能为空！");
        return;
      }
      if (this.loginForm.code == "") {
        alert("请输入验证码！");
        return;
      }
      if (this.loginForm.code == this.code_server) {
        const param = {
          phone: this.loginForm.phone,
        };
        getLogin(param)
          .then((res) => {
            console.log(res.data);
            if (res.data.flag) {
              this.$setSessionStorage("user", res.data.obj);
              this.$router.push("/roomlist");
            } else {
              //失败
              alert("服务器错误");
            }
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        alert("验证码错误");
      }
    },
    passway() {
      this.show = true;
      this.show2 = false;
    },
    codeway() {
      this.show2 = true;
      this.show = false;
    },
  },
};
</script>

<style>
#app {
  position: absolute;
  top: 0px;
  bottom: 0px;
  width: 100%;
  margin-top: 0px;
}
.way {
  font-size: 14px;
  color: #8d8d8d;
  cursor: pointer;
}
.loginimg {
  position: fixed;
}

#login {
  width: 100%;
  height: 100%;
}
#login .loginConbox {
  background: #ffffff;
}

#login .header {
  height: 60px;
  position: relative;
  background: rgba(0, 0, 0, 0.05);
  /*border-bottom: 1px solid rgba(255, 255, 255, 0.3);*/
}
#login .header .header-left {
  float: left;
  width: 15%;
}

#login .header .header-right {
  float: right;
  width: 85%;
}

#login .header .header-right p {
  float: left;
  font-weight: bold;
  line-height: 60px;
  font-size: 16px;
}

#login .header .logo {
  margin-left: 30px;
  width: 500px;
  float: left;
  height: 40px;
  padding-top: 10px;
}

#login .header .logo img {
  height: 100%;
}
#login .loginBox {
  padding: 74px 0 118px;
}
#login .loginBox .iconcolor {
  color: #409eff;
}
#login .loginCon {
  width: 990px;
  margin: auto;
  position: relative;
  height: 600px;
}
#login .el-card__header {
  border-bottom: 0px;
}
#login .title {
  font-size: 36px;
  font-weight: 600;
  color: #ffffff;
  width: 500px;
  float: left;
  margin-top: 0px;
}
#login .title:nth-of-type(1) {
  font-size: 34px;
  margin-top: 50px;
  margin-bottom: 30px;
}
#login .login-module {
  width: 380px;
  height: 315px;
  margin-top: 60px;
  border: none;
  position: absolute;
  right: 0;
}
#login .login-module .formTitlt {
  font-size: 18px;
  font-weight: 400;
}
#login .login-module .titIconbox {
  float: right;
}
#login .login-module .titIconbox .pointer {
  cursor: pointer;
}

#login .smalltxt {
  text-align: right;
}

#login .smalltxt .a {
  text-decoration: none;
  color: #999999;
  font-size: 12px;
  margin-left: 8px;
}
#login .getCode {
  top: 25vw;
  right: 10vw;
}

#login .el-form .el-form-item:nth-of-type(2) .el-form-item__content .el-button {
  position: absolute;
  width: 100px;
  top: 4px;
  border-radius: 5px;
  right: 8px;
}

#login .el-form-item__content {
  margin-left: 0px !important;
}

#login .el-form-item__content .subBtn {
  width: 100%;
}

#login .el-input__inner,
.el-button,
.el-card,
.el-message {
  border-radius: 0px !important;
}

#login .el-form-item__content .ico {
  position: absolute;
  top: 0px;
  left: 0px;
  z-index: 999;
  width: 40px;
  height: 39px;
  text-align: center;
  border-right: 1px solid #ccc;
}

#login .ewmbox {
  width: 100%;
  height: 240px;
  margin-top: -25px;
}

#login .ewm {
  width: 140px;
  height: 140px;
  margin: 20px auto;
}
#login .ewmbox .ewm p {
  font-size: 12px;
  padding-left: 40px;
  margin: 0;
}
#login .ewmicon {
  width: 140px;
  margin: 20px auto 0;
}

#login .ewmicon .iconfont {
  float: left;
}

#login .ewmicon p {
  font-size: 12px;
  padding-left: 40px;
  margin: 0;
}
</style>