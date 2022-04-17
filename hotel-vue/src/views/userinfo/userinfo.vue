<template>
  <div class="personalCenter">
    <Header></Header>
    <el-form :inline="true">
      <el-main>
        <el-card class="box-card">
          <el-col :span="12">
            <div class="sub-title"></div>
            <div class="demo-basic--circle">
              <div class="img" @click="drawer = true">
                  <el-avatar
                    :size="100"
                    :src="circleUrl"
                    align="center"
                  ></el-avatar>
              </div>

              <el-drawer
                title="我是标题"
                :visible.sync="drawer"
                direction="rtl"
                :with-header="false">
                <span>上传头像</span>
                <div>
                  <el-upload
                        action="http://localhost:8080/hotel/FileUpload/upload"
                        list-type="picture-card"
                        name="files"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        :on-success="handleSuccess"
                    >
                        <i class="el-icon-plus"></i>
                    </el-upload>
                </div>
                <br>
                <el-button @click="updateimg">确认</el-button>
              </el-drawer>
               
              <div class="block">
                <span></span>
              </div>
            </div>
          </el-col>

          <el-input
            v-model="userdata.userId"
            type="text"
            placeholder="无"
            maxlength="6"
            show-word-limit
            disabled
          >
            <span slot="suffix">用户ID</span>
          </el-input>

          <el-input
            v-model="userdata.userName"
            type="text"
            placeholder="请输入账户昵称"
            show-word-limit
            ><span slot="suffix">账户昵称</span>
          </el-input>

          <el-input
            v-model="userdata.name"
            type="text"
            placeholder="请输入真实姓名"
            show-word-limit
          >
            <span slot="suffix">真实姓名</span>
          </el-input>
          <el-input
            v-model="userdata.idCard"
            type="text"
            placeholder="请输入身份信息"
            show-word-limit
          >
            <span slot="suffix">身份信息</span>
          </el-input>
          <el-input
            v-model="userdata.password"
            placeholder="请输入密码"
            show-password
          >
            <span slot="suffix">密码</span>
          </el-input>
            <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="userdata.birthday"  class="birthday"></el-date-picker>
          </el-col>
            <span slot="suffix">出生日期</span>
          <el-select v-model="userdata.gender" placeholder="性别">
            <el-option
              v-for="i in sex"
              :key="i.value"
              :label="i.label"
              :value="i.value"
            >
            </el-option>
          </el-select>
          <el-select v-model="userdata.grade" placeholder="级别" disabled>
            <el-option
              v-for="i in grade"
              :key="i.value"
              :label="i.label"
              :value="i.value"
            >
            </el-option>
          </el-select>
          <el-input
            v-model="userdata.phone"
            type="text"
            placeholder="请输入手机号码"
            maxlength="11"
            show-word-limit
          >
            <span slot="suffix">手机号码</span>
          </el-input>
          <el-input
            v-model="userdata.address"
            type="text"
            placeholder="地址"
            maxlength="100"
            show-word-limit
          ></el-input>
          <br />
          <el-button-group>
            <el-button type="primary" size="medium" round @click="save()"
              >保存修改</el-button
            >
            <el-button type="primary" size="medium" round ><router-link to="/login"><p>退出登录</p></router-link></el-button
            >
          </el-button-group>
        </el-card>
      </el-main>
    </el-form>
  </div>
</template>
<script>
import Header from "@/components/Header.vue";
import { getuserbyid, updateuser ,delImg} from "@/api/user";
export default {
  components: {
    Header,
  },
  data() {
    return {
      drawer: false,
      userdata: {
        //用户id
        userId: "",
        //账户昵称
        userName: "",
        //真实姓名
        name: "",
        idCard:'', 
        //密码
        password: "",
        //性别
        gender: "",
        //用户级别
        grad: "",
        birthday:'',
        //手机号
        phone: "",
        //地址
        address: "",
        userImg:''
      },
      //头像
      circleUrl: "",
      sizeList: [""],
      sex: [
        {
          value: 1,
          label: "男",
        },
        {
          value: 2,
          label: "女",
        },
      ],
      grade: [
        {
          value: 2,
          label: "会员",
        },
        {
          value: 3,
          label: "普通用户",
        },
      ],
    };
  },

  methods: {
    save() {
      this.$confirm("确认保存吗？", "是否保存", {
        cancelButtonText: "取消",
        confirmButtonText: "确认",
        lockScroll: false,
        type: "warning",
      })
        .then(() => {
          const param = this.userdata;

          updateuser(param).then((res) => {
            if (res.data.code == 2000) {
              this.$message({
                type: "success",
                message: "修改成功",
              });
              this.$router.go(0)
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改",
          });
        });
    },
    handlePictureCardPreview(file) {
            console.log('handlePictureCardPreview');
            console.log(file);
            this.dialogImageUrl = 'http://localhost:8080/hotel' + file.url;
            this.dialogVisible = true;
        },
        handleSuccess(response, file) {
            console.log('handleSuccess');
            console.log(response);
            console.log(file);
            file.url = 'http://localhost:8080/hotel/' + response.obj;
            this.userdata.userImg=file.url;
        },
       handleRemove(roomImg) {
            console.log(roomImg)
            roomImg=roomImg.response.obj
            delImg(roomImg).then((res) => {
                if (res.data.code === 2000) {
                    this.roomTypeList = res.data.obj;
                    console.log(res.data);
                }
            });
        },

        updateimg(){
          this.save()
        }
  },
  created() {
    const user = this.$getSessionStorage("user");
    console.log(user.userId);
    getuserbyid({
      userId: user.userId,
    }).then((res) => {
      this.userdata = res.data.obj;
      console.log(res.data.obj)
      this.circleUrl=res.data.obj.userImg
    });
  },
};
</script>
<style scoped>
p{
  color: #ffffff;
}
.el-header {
  line-height: 60px;
  text-align: center;
  background-color: #b3c0d1;
}
.birthday{
  margin-left: 100px;
  width: 200px;
}
.el-aside {
  line-height: 44px;
  text-align: center;
  background-color: #d3dce6;
}
.el-main {
  line-height: 36px;
  text-align: center;
  background-color: #e9eef3;
  display: flex;
  width: 1200px;
  margin: auto;
  justify-content: center;
}

.el-card__body {
  width: 100%;
  padding: 30px;
  justify-content: center;
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  align-items: center;
}

.el-input,
.el-select {
  width: 400px;
  padding-top: 6px;
}
.el-avatar > img {
  width: 100%;
}
.el-button-group {
  padding-top: 10px;
}
.el-button-group > .el-button:first-child:last-child.is-round {
  margin-top: 10px;
}
.block {
  font-weight: bold;
}
.text {
  font-size: 14px;
}
.item {
  margin-bottom: 18px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 55%;
  border-radius: 30px;
  display: flex;
  justify-content: center;
}

.el-col-12 {
  width: 100%;
  display: flex;
  justify-content: center;
}
.el-input-group {
  width: 100%;
}
</style>