<template>
  <div>
    <Header></Header>
    <div class="wrapper">
      <ul class="business">
        <li v-for="(item, index) in historyorderList" :key="index">
          <img :src=" item.roomDetailet.roomImg" />
          <div class="business-info">
            <div class="business-info-h">
              <h3>{{ item.roomType.roomTypeName }}</h3>
            </div>
            <div class="business-info-star">
              <div class="business-info-star-left">
                <p id="orderId">订单编号:{{ item.orderId }}</p>
              </div>
            </div>
            <div class="business-info-delivery">
              <p>
                &#165; {{ item.roomDetailet.roomPrice }} |
                {{ item.roomType.roomTypeName }}
              </p>
              <p>
                入住时间：{{ item.checkTime }} | 退房时间：{{ item.outTime }}
              </p>
            </div>
            <span class="state">{{
              item.state == 1 ? "未入住" : "正在入住"
            }}</span>
            <!-- <span
              class="del"
              @click="cancel(item.orderId, index)"
              ref="cancel"
              >{{ item.state == 1 ? "取消" : "不可取消" }}</span
            > -->
            <span class="del" @click="cancel(item.orderId, index)" ref="cancel">{{item.state | formatStata}}</span>

            
            <div class="business-info-promotion">
              <div class="business-info-promotion-left">
                <div
                  class="business-info-promotion-left-incon"
                  style="background-color: #f1884f"
                >
                  特
                </div>
                <p>总价格：&#165; {{ item.roomDetailet.roomPrice }}</p>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script scoped>
//导入共通组件
import Header from "@/components/Header.vue";
import { gethistoryorder, delorder } from "@/api/order";
export default {
  components: {
    Header,
  },
  data() {
    return {
      historyorderList: [],
      arr: [],
    };
  },

  filters: {
    // 状态显示转换
    formatStata(status) {
      const statusMap = {
        1: '取消',
        2: '不可取消',
        3: '等待确认',
      }
      return statusMap[status]
    }
  },
  methods: {
    gethistoryorder() {
      var user = this.$getSessionStorage("user");
      gethistoryorder({
        id: user.userId,
        state: [1, 2, 3],
      }).then((res) => {
        console.log(res.data.obj);
        this.historyorderList = res.data.obj;
      });
    },
    del(value) {
      console.log(value);
    },
    cancel(value, index) {
      if (this.$refs.cancel[index].innerText === "取消") {
        this.$confirm("是否取消订单？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
          center: true,
        }).then(() => {
          delorder({
            ids: [value],
          }).then((res) => {
            if (res.data.code === 2000) {
              this.$message({
                type: "success",
                message: "等待客服确认",
              });
              this.gethistoryorder();
            } else {
              this.$message({
                type: "error",
                message: "取消订单失败",
              });
            }
          });
        });
      }
    },
  },

  created() {
    this.gethistoryorder();
  },
};
</script>

<style scoped>
.wrapper {
  width: 1200px;
  margin: auto;
  position: relative;
}
.state {
  position: absolute;
  margin-left: 800px;
  padding: 3px 14px;
  background: rgb(235, 10, 55);
  color: #f1eaea;
  font-size: 14px;
}
.del {
  position: absolute;
  margin-left: 900px;
  padding: 3px 14px;
  background: rgb(235, 10, 55);
  color: #f1eaea;
  font-size: 14px;
}
/****************** 总容器 ******************/
.wrapper {
  height: 100%;
  overflow: auto;
}
/****************** 推荐商家列表部分 ******************/
.wrapper .business {
  width: 100%;
  margin-bottom: 14px;
}
.wrapper .business li {
  width: 100%;
  box-sizing: border-box;
  padding: 14px;
  user-select: none;
  border-bottom: solid 1px #ddd;

  display: flex;
}
.wrapper ul li {
  background: #ffffff;
  margin-top: 10px;
}

.wrapper .business li img {
  width: 160px;
  height: 120px;
}

.wrapper .business li .business-info {
  width: 100%;
  box-sizing: border-box;
  padding-left: 14px;
}

.wrapper .business li .business-info .business-info-h {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 14px;
}

.wrapper .business li .business-info .business-info-h h3 {
  font-size: 14px;
  color: #333;
}

.wrapper .business li .business-info .business-info-h .business-info-like {
  width: 14px;
  height: 14px;
  background-color: #666;
  color: #fff;
  font-size: 14px;
  margin-right: 14px;

  display: flex;
  justify-content: center;
  align-items: center;
}

.wrapper .business li .business-info .business-info-star {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 14px;
  font-size: 14px;
}

.wrapper
  .business
  li
  .business-info
  .business-info-star
  .business-info-star-left {
  display: flex;
  align-items: center;
}

.wrapper
  .business
  li
  .business-info
  .business-info-star
  .business-info-star-left
  .fa-star {
  color: #fec80e;
}

.wrapper
  .business
  li
  .business-info
  .business-info-star
  .business-info-star-left
  p {
  color: #666;
}

.wrapper
  .business
  li
  .business-info
  .business-info-star
  .business-info-star-right {
  background-color: #0097ff;
  color: #fff;
  font-size: 14px;
  border-radius: 14px;
  padding: 0 14px;
}

.wrapper .business li .business-info .business-info-delivery {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 14px;

  color: #666;
  font-size: 14px;
}

.wrapper .business li .business-info .business-info-explain {
  display: flex;
  align-items: center;
  margin-bottom: 14px;
}

.wrapper .business li .business-info .business-info-explain div {
  border: solid 1px #ddd;
  font-size: 14px;
  color: #666;
  border-radius: 14px;
  padding: 0 14px;
}

.wrapper .business li .business-info .business-info-promotion {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 14px;
}

.wrapper
  .business
  li
  .business-info
  .business-info-promotion
  .business-info-promotion-left {
  display: flex;
  align-items: center;
}

.wrapper
  .business
  li
  .business-info
  .business-info-promotion
  .business-info-promotion-left
  .business-info-promotion-left-incon {
  width: 14px;
  height: 14px;
  background-color: #70bc46;
  border-radius: 3px;
  font-size: 14px;
  color: #fff;

  display: flex;
  justify-content: center;
  align-items: center;
}

.wrapper
  .business
  li
  .business-info
  .business-info-promotion
  .business-info-promotion-left
  p {
  color: #666;
  font-size: 14px;
  margin-left: 14px;
}

.wrapper
  .business
  li
  .business-info
  .business-info-promotion
  .business-info-promotion-right {
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #999;
}

.wrapper
  .business
  li
  .business-info
  .business-info-promotion
  .business-info-promotion-right
  p {
  margin-right: 14px;
}

.div {
  width: 100%;
  height: 14px;
}
</style>