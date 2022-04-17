<template>
  <div>
    <Header></Header>
    <div class="submena clearfix">
      <a href=" ">房间详情</a>
    </div>

    <div class="center_con clearfix">
      <div class="main_menu fl">
        <img :src="Img" alt="" />

        <div class="surplus" v-show="surplus">
          <span class="remain">仅剩{{ availengageroomcount }}间</span>
        </div>
        <div class="surplus" v-show="noroom">
          <span class="remain1">无房</span>
        </div>
      </div>
      <div class="goods_detail_list fr">
        <h3>{{ roomTypeName }}</h3>
        <p class="intorduce">
          {{ introduce }}
        </p>
        <div class="prize_bar">
          <div class="show_prize fl">
            ￥<em>{{ roomdetailet.roomPrice }}</em>
          </div>
        </div>
        <div class="goods_num clearfix">
          <span>wifi (<span class="breakfast">免费</span>) </span><br />
          <br />
          <span
            >设施 (<span class="breakfast">{{ roomdetailet.facilities }}</span
            >)
          </span>
        </div>
        <div class="total">
          总价：<em>{{ roomdetailet.roomPrice }}元</em>
        </div>
        <div class="operate_btn">
          <a class="buy_btn" @click="engage(roomdetailet.rtypeId)">立即预定</a>
        </div>
        <el-drawer
          title="确认信息!"
          :before-close="handleClose"
          :visible.sync="dialog"
          direction="rtl"
          custom-class="demo-drawer"
          ref="drawer"
          size="50%"
        >
          <div>
            <el-form :model="form">
              <el-form-item label="姓名" :label-width="formLabelWidth">
                <el-input v-model="name"></el-input>
              </el-form-item>
              <el-form-item label="房间类型" :label-width="formLabelWidth">
                <el-input v-model="roomTypeName"></el-input>
              </el-form-item>
              <el-form-item label="入住时间" :label-width="formLabelWidth">
                <el-date-picker
                  v-model="form.createTimeRange"
                  value-format="yyyy-MM-dd"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  @blur="seldate"
                >
                </el-date-picker>
              </el-form-item>
              <el-form-item label="入住人数" :label-width="formLabelWidth">
                <el-input v-model="form.quantity"></el-input>
              </el-form-item>
              <el-form-item label="价格" :label-width="formLabelWidth">
                <el-input v-model="roomPrice"></el-input>
              </el-form-item>
            </el-form>
            <div>
              <el-button @click="cancelForm" id="btn">取 消</el-button>
              <el-button
                id="btn"
                type="primary"
                @click="$refs.drawer.closeDrawer()"
                :loading="loading"
                >{{ loading ? "提交中 ..." : "确 定" }}</el-button
              >
            </div>
          </div>
        </el-drawer>
      </div>
    </div>
  </div>
</template>
<script>
import Header from "@/components/Header.vue";
import { getRoomById } from "@/api/roomList";

import {
  getaddengage,
  getfindavailableroom,
  getUserMsgById
} from "@/api/engage";


export default {
  components: {
    Header,
  },
  data() {
    return {
      noroom: false,
      surplus: false,
      availengageroomcount: "",
      roomdetailet: [],
      roomTypeName: "",
      name: "",
      roomPrice: "",
      typeId: "",
      Img: "hotel/big/big1.webp",
      introduce: "",
      dialog: false,
      loading: false,
      formLabelWidth: "80px",
      form: {
        createTimeRange: "",
        quantity: "",
      },
    };
  },

  methods: {
    getRoomById() {
      getRoomById({
        roomId: this.$route.query.id,
      })
        .then((res) => {
          console.log(res.data.obj);
          this.roomdetailet = res.data.obj;
          this.roomTypeName = res.data.obj.roomType.roomTypeName;
          this.introduce = res.data.obj.roomType.introduce;
          this.roomPrice = res.data.obj.roomPrice;
          this.Img = res.data.obj.roomImg;
          this.typeId = res.data.obj.rtypeId;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    //判断是否有房间
    getavailableroom() {
      console.log(new Date());
      if (this.$route.query.checkTime != null) {
        getfindavailableroom({
          typeId: this.$route.query.typeId,
          checkTime: this.$route.query.checkTime,
          outTime: this.$route.query.outTime,
        }).then((res) => {
          console.log(res.data);

          if (res.data.obj <= 10) {
            this.surplus = true;
            this.noroom = false;
            this.availengageroomcount = res.data.obj;
            if (res.data.obj == 0) {
              this.surplus = false;
              this.noroom = true;
            }
          }
        });
      }
    },
    handleClose(done) {
      if (this.loading) {
        return;
      }
      this.$confirm("确定要提交表单吗？")
        .then((res) => {
          console.log(res);
          var user = this.$getSessionStorage("user");
          const param = {
            typeId: this.roomdetailet.rtypeId,
            quantity: this.form.quantity,
            total: this.roomdetailet.roomPrice,
            state: 1,
            userId: user.userId,
            checkTime: this.form.createTimeRange[0],
            outTime: this.form.createTimeRange[1],
          };
          console.log(param);
          if (this.noroom == false) {
            getaddengage(param).then((res) => {
              console.log(res);
              if (res.data.code === 2000) {
                this.loading = true;
                this.timer = setTimeout(() => {
                  done();
                  this.$message({
                    type: "success",
                    message: "预订成功",
                  });
                  this.$router.push("/engage");

                  // 动画关闭需要一定的时间
                  setTimeout(() => {
                    this.loading = false;
                  }, 400);
                }, 2000);
              } else {
                this.$message({
                  type: "info",
                  message: "预订失败!请重新预订",
                });
              }
            });
          }else{
            this.$message({
                  type: "info",
                  message: "该时间段已经无房,请重新选择时间后预订!",
                });
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    cancelForm() {
      this.loading = false;
      this.dialog = false;
      clearTimeout(this.timer);
    },

    seldate() {
      getfindavailableroom({
        typeId: this.$route.query.typeId,
        checkTime: this.form.createTimeRange[0],
        outTime: this.form.createTimeRange[1],
      }).then((res) => {
        console.log(res.data);
        if (res.data.obj <= 10) {
          this.surplus = true;
          this.noroom = false;
          this.availengageroomcount = res.data.obj;
          if (res.data.obj == 0) {
            this.surplus = false;
            this.noroom = true;
          }
        }
      });
    },

    //生成订单
    engage(value) {
      this.dialog = true;
      var user = this.$getSessionStorage("user");
      getUserMsgById({ userId: user.userId }).then((res) => {
        console.log(res.data);
        this.name = res.data.obj.name;
        this.idCard=res.data.obj.idCard
        // this.peopleNum=res.data.obj.peopleNum
        if (this.name == null || this.idCard==null) {
          this.$confirm("请前去补全身份信息", {
            cancelButtonText: "取消",
            confirmButtonText: "确认",
            lockScroll: false,
            type: "warning",
          }).then(() => {
            this.$router.push("/userinfo");
          });
        }
      });
      this.typeId = value;
      console.log(value);
    },
  },
  mounted() {
    this.getRoomById();
    this.getavailableroom();
  },
};
</script>
<style scoped>
body {
  font-family: "Microsoft YaHei";
  color: #666;
  font-size: 12px;
}
.breakfast {
  color: #4fb443;
}
.intorduce{
  font-size: 12px;
}
.el-form .el-input {
  width: 350px;
}
#btn {
  width: 100px;
}
#btn:nth-of-type(1) {
  margin-left: 130px;
}
/*顶部条状态*/
.header_con {
  height: 29px;
  background-color: #f7f7f7;
  border-bottom: 1px solid #ddd;
}
.header {
  width: 1200px;
  height: 29px;
  margin: 0 auto;
}
.center_con {
  position: relative;
}

.main_menu img {
  width: 38%;
  position: absolute;
}
.surplus {
  width: 200px;
  height: 200px;
  margin: 60px 130px;
  border-radius: 50%;
  background: rgba(65, 61, 61, 0.5);
  position: absolute;
}
.remain {
  font-size: 24px;
  color: #f7f2f2;
  position: absolute;
  margin-top: 85px;
  margin-left: 55px;
}
.remain1 {
  font-size: 24px;
  color: #f7f2f2;
  position: absolute;
  margin-top: 85px;
  margin-left: 72px;
}
.welcome {
  font: 12px/29px "Microsoft YaHei UI";
  float: left;
}
.user_login {
  height: 29px;
  line-height: 29px;
  float: right;
}
.user_login a {
  color: #666;
}
.user_login a:hover {
  color: #f80;
}
.search_bar {
  width: 1200px;
  height: 115px;
  margin: 0 auto;
}
.logo {
  width: 151px;
  height: 59px;
  margin: 29px 0 0 17px;
}
.search_con {
  width: 616px;
  height: 38px;
  border: 1px solid #37ab40;
  margin: 34px 0 0 123px;
  /*雪碧图的放大镜作背景图*/
  /* background:url() 10px -335px no-repeat; */
}
.search_con .input_text {
  width: 470px;
  height: 37px;
  border: 0px;
  margin-left: 37px;
  outline: none;
}
.search_con .input_btn {
  width: 100px;
  height: 38px;
  background-color: #37ab40;
  border: 0;
  font: 14px/38px "Microsoft YaHei UI";
  color: #fff;
  /*鼠标变成手*/
  cursor: pointer;
}
.guest_card {
  width: 200px;
  height: 40px;
  margin-top: 34px;
}
.card_name {
  width: 158px;
  height: 38px;
  border: 1px solid #ddd;
  font: 14px/38px "Microsoft YaHei UI";
  color: #37ab40;
  text-indent: 56px;
  /* background:url() 10px -300px no-repeat #f7f7f7; */
}
.goods_count {
  width: 40px;
  height: 40px;
  background-color: #f80;
  font: bold 18px/40px "Microsoft YaHei UI";
  text-align: center;
  color: #fff;
}
.navbar_con {
  height: 40px;
  border-bottom: 2px solid #37ab30;
  /*background: red;*/
}
.navbar {
  width: 1200px;
  height: 40px;
  margin: 0 auto;
  /*background: red;*/
}
.subnav_con h1 {
  width: 200px;
  height: 40px;
  background-color: #37ab40;
  font: 14px/40px "Microsoft YaHei UI";
  text-align: center;
  color: #fff;
}
.subnav_con i {
  width: 11px;
  height: 7px;
  /* background:url() left center no-repeat; */
  overflow: hidden;
  display: inline-block;
}
.navlist {
  overflow: hidden;
}
.navlist li {
  float: left;
  height: 14px;
  padding: 0 25px;
  border-left: 1px solid #666;
  margin-left: -2px;
  margin-top: 13px;
}
.submena {
  width: 1200px;
  height: 30px;
  margin: 0 auto;
  font-size: 14px;
  /*background:yellow;*/
}
.submena a {
  color: #37ab40;
  line-height: 30px;
}

.center_con {
  width: 1180px;
  height: 350px;
  padding: 10px;
  margin: 0 auto;
  background: #ffffff;
}
.center_con .main_menu {
  width: 440px;
  height: 440px;
  overflow: hidden;
}
.goods_detail_list {
  width: 700px;
  height: 350px;
}
.goods_detail_list h3 {
  font-size: 24px;
  line-height: 24px;
  color: #666;
  font-weight: normal;
}
.goods_detail_list p {
  color: #666;
  line-height: 40px;
}
.prize_bar {
  height: 72px;
  background-color: #fff5f5;
  line-height: 72px;
}
.prize_bar .show_prize {
  font-size: 20px;
  color: #ff3e3e;
  padding-left: 20px;
}
.prize_bar .show_pirze em {
  font-style: normal;
  font-size: 36px;
  padding-left: 10px;
}
.prize_bar .show_unit {
  padding-left: 150px;
}
.goods_num {
  height: 52px;
  margin-top: 19px;
  font-size: 12px;
  /*background: yellow;*/
}
.goods_num .num_name {
  width: 70px;
  height: 52px;
  line-height: 52px;
}
.goods_num .num_add {
  width: 75px;
  height: 50px;
  border: 1px solid #dddddd;
}
.goods_num .num_add .num_show {
  width: 49px;
  height: 50px;
  text-align: center;
  line-height: 50px;
  border: 0px;
  outline: none;
  font-size: 14px;
  color: #666;
}
.goods_num .num_add .add,
.goods_num .num_add .minus {
  width: 25px;
  line-height: 25px;
  text-align: center;
  border-left: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
  color: #666;
  font-size: 14px;
}
.goods_num .num_add .minus {
  border-bottom: 0px;
}
.total {
  height: 35px;
  line-height: 35px;
  margin-top: 25px;
  /*background: yellow;*/
}
.total em {
  font-style: normal;
  color: #ff3e3e;
  font-size: 18px;
}
.operate_btn {
  height: 40px;
  margin-top: 25px;
  font-size: 0;
  position: relative;
}
.operate_btn .buy_btn,
.operate_btn .add_cart {
  cursor: pointer;
  display: inline-block;
  width: 178px;
  height: 38px;
  border: 1px solid #c40000;
  font-size: 14px;
  color: #c40000;
  line-height: 38px;
  text-align: center;
  background-color: #ffeded;
}
.operate_btn .add_cart {
  background-color: #c40000;
  color: #fff;
  margin-left: 10px;
  position: relative;
  z-index: 10;
}

body,
p,
h1,
h2,
h3,
h4,
h5,
h6,
ul,
dl,
dt,
form,
input {
  margin: 0;
  padding: 0;
}

ul {
  list-style: none;
}
a {
  text-decoration: none;
}

em {
  font-style: normal;
}

img {
  border: 0px;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  font-size: 100%;
}
.clearfix:before,
.clearfix:after {
  content: "";
  display: table;
}
.clearfix:after {
  clear: both;
}
.clearfix {
  zoom: 1;
}

.fl {
  float: left;
}
.fr {
  float: right;
}
</style>
