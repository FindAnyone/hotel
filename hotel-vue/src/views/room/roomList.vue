<template>
  <div>
    <Header></Header>
    <Navigat></Navigat>
    <div class="hotel">
      <div class="nav">
        <ul>
          <li>
            <span class="sel">客房类型</span>
            <el-select
              v-model="searchForm.typeId"
              placeholder="请选择"
              clearable
            >
              <el-option
                v-for="item in roomType"
                :key="item.typeId"
                :label="item.roomTypeName"
                :value="item.typeId"
              >
              </el-option>
            </el-select>
          </li>
          <li>
            <span class="sel">可住人数</span>
            <el-select
              v-model="searchForm.number"
              placeholder="请选择"
              clearable
            >
              <el-option label="2" value="2"></el-option>
              <el-option label="4" value="4"></el-option>
              <el-option label="6" value="6"></el-option>
            </el-select>
          </li>
          <li>
            <span class="sel">窗户</span>
            <el-select
              v-model="searchForm.window"
              placeholder="请选择"
              clearable
            >
              <el-option label="有窗" value="1"></el-option>
              <el-option label="无窗" value="0"></el-option>
            </el-select>
          </li>
          <li>
            <span class="sel">入住时间</span>
            <el-date-picker
              v-model="createTimeRange"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker>
          </li>
          <li>
            <span class="sel">价格区间</span>
            <el-input v-model="searchForm.startPrice" clearable></el-input> -
            <el-input v-model="searchForm.endPrice" clearable></el-input>
          </li>
          <li>
            <span class="search" @click="search">搜索</span>
          </li>
        </ul>
      </div>
      <div class="main">
        <ul>
          <li v-for="(item, index) in roomList" :key="index">
            <div class="info">
              <div>
                <img :src= "item.roomImg" alt="" />
              </div>
              <!-- <div>{{ item.floor }}楼 {{ item.roomNumber }}房间</div> -->
              <div class="facility">
                <span class="breakfast">含早 </span>
                <span>{{ item.area }}平米</span>
                <span> {{ item.roomType.roomTypeName }} </span>
                <span>wifi (<span class="breakfast">免费</span>) </span>
              </div>
              <div class="facility">
                <span
                  >设施 (<span class="breakfast">{{ item.facilities }}</span
                  >)
                </span>
              </div>
              <div>
                <span class="price">¥{{ item.roomPrice }}</span>
              </div>
              <div class="engage">
                <!-- <span>不可取消</span> -->
                <span @click="engage(item.roomId,item.rtypeId)">预订/在线付</span>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div class="page">
        <div>
          <PageComponent
            :total="page.totalSize"
            :page="page"
            @pageChange="handlePageChange"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import Navigat from "@/components/Navigat.vue";
import { roomTypeList, findEngageRoom } from "@/api/roomList";
import PageComponent from "@/components/Pagenation/index";
export default {
  components: {
    Header,
    Navigat,
    PageComponent,
  },
  data() {
    return {
      createTimeRange: "",
      name: "",
      value: "",
      searchForm: {
        typeId: "",
        number: "",
        window: "",
        startPrice: "",
        checkTime: "",
        outTime: "",
        endPrice: "",
        pageNumber: "",
        pageSize: "",
      },
      roomList: [],
      roomType: [],
      page: {
        currentPage: 1, // 当前页，对应接口中的page
        pageSize: 9, // 每页条数，对应接口中的limit
        totalSize: 0, // 中条数，对应接口中的res.data.page.totalRows
        totalPage: 0, // 总页数，对应接口中的res.data.page.totalPages
      },
    };
  },
  methods: {
    //分页方法
    handlePageChange(item) {
      const para = { page: item.currentPage, limit: item.pageSize }
      this.getfindEngageRoom(para)
    },
    //搜索按钮
    search() {
      this.getfindEngageRoom()
    },

    //查询可使用的房间列表方法
    getfindEngageRoom(){
      this.searchForm.checkTime = this.createTimeRange[0];
      this.searchForm.outTime = this.createTimeRange[1];
      this.searchForm.pageNumber = this.page.currentPage;
      this.searchForm.pageSize = this.page.pageSize;

      const param = this.searchForm;

      findEngageRoom(param).then((res) => {
        this.page.totalSize = res.data.obj.total;
        this.page.totalPage = res.data.obj.pageCount;
        console.log(res.data.obj);
        this.roomList = res.data.obj.list;
      });
    },

    //预订按钮
    engage(item,rtypeId) {
      console.log(rtypeId);
      if(this.$getSessionStorage('user')==null){
        this.$router.push('/login')
      }else{
        this.$router.push({
        path:'/roomdetails',
        query:{
          id:item,
          typeId:rtypeId,
          checkTime:this.createTimeRange[0],
          outTime:this.createTimeRange[1],
        }
      })
      }
    },
  },
  created() {
    
    this.getfindEngageRoom()
    roomTypeList().then((res) => {
      // console.log(res.data);
      this.roomType = res.data.obj;
    });
  },
};
</script>

<style scoped>
.page {
  margin: 10px 0px 50px;
  height: 45px;
  width: 1200px;
  background: #ffffff;
}
.page div {
  float: right;
  margin-right: 20px;
}
.hotel {
  width: 1200px;
  margin: auto;
}
.facility {
  font-size: 14px;
}
.nav {
  width: 1200px;
  margin-top: 10px;
  background: #ffffff;
  display: flex;
}
.nav ul {
  width: 1200px;
  display: flex;
  flex-wrap: wrap;
}
.nav ul li {
  width: 350px;
  height: 60px;
  line-height: 60px;
  margin-left: 50px;
}
.nav ul li:nth-of-type(4) {
  width: 500px;
}
.nav ul li:nth-of-type(5) {
  margin-left: 20px;
  width: 450px;
}
.sel {
  margin-right: 40px;
}
.search {
  cursor: pointer;
  margin-left: 10px;
  line-height: 40px;
  border: 1px solid burlywood;
  border-radius: 20px;
  padding: 5px 20px;
}
.nav ul li:nth-of-type(5) .el-input {
  width: 160px;
}
.nav ul li:nth-of-type(6) {
  margin-left: 30px;
  width: 100px;
}

.main {
  width: 1200px;
  margin-top: 10px;
}
.main ul {
  display: flex;
  flex-wrap: wrap;
}
.main ul li {
  width: 392px;
  height: 410px;
  margin-right: 12px;
  background: #ffffff;
}
.main ul li:nth-of-type(3n) {
  margin-right: 0px;
  margin-bottom: 10px;
}
.info {
  width: 380px;
  margin: 5px auto 0px;
  line-height: 30px;
}
.main img {
  width: 380px;
  height: 250px;
}
.price {
  font-size: 24px;
  font-weight: bold;
  color: crimson;
}
.engage {
  height: 40px;
  position: relative;
}
/* .engage span {
  padding: 3px 8px;
  border: 1px solid #dcdcdc;
  color: #7d7d7d;
  font-size: 14px;
} */
.engage span:nth-of-type(1) {
  position: absolute;
  padding: 3px 15px;
  border: none;
  border-radius: 5px;
  bottom: 5px;
  right: 5px;
  background: crimson;
  color: #ffffff;
  float: right;
}
.breakfast {
  color: #4fb443;
}
</style>