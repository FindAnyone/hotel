<template>
  <div id="roomtype">
    <el-form v-model="searchForm" :inline="true">
      <el-row>
        <el-col :span="6">
          <el-button type="primary" icon="el-icon-plus" @click="$refs.addDialog.open(null)">新增</el-button>
          <el-button type="primary" icon="el-icon-delete" @click="handleDelete">一键删除</el-button>  
        </el-col>
      </el-row>
    </el-form>
    <el-table
      border
      height="600"
      :data="roomTypeList"
      v-loading="loading"
      element-loading-text="拼命加载中"
      @selection-change="handleSelectionChange"
      @cell-mouse-enter="mouseEnter"
      >
      <el-table-column label="序号" type="index" width="55">
        <template slot-scope="scope">
          <!-- (当前页 - 1) * 当前显示数据条数 + 当前行数据的索引 + 1  -->
          <span>{{ (page.currentPage - 1) * page.pageSize + scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column type="selection" width="55"> </el-table-column>
      <el-table-column label="房间类型名称" prop="roomTypeName"/>
      <el-table-column label="简介" prop="introduce"/>
      <el-table-column label="操作" prop="operation" width="240">
        <template>
          <el-button
            type="text"
            icon="el-icon-edit"
            @click="$refs.updateDialog.open(roomTypeData)"
          >查看详情</el-button>
          <el-button
            type="text"
            style="margin-left: 16px;"
            icon="el-icon-s-tools"
            @click="drawer = true"
          >查看图片</el-button>
          <el-button
            type="text"
            icon="el-icon-delete"
            class="red"
            @click="handleDelete"
          >删除</el-button>
        </template>
      </el-table-column>
      <el-drawer
        title="客房图片详情"
        :visible.sync="drawer"
        :append-to-body='true'
        direction="rtl"
        size="50%">
          <div v-for="(img, index) in roomTypeData.imgList" :key="index">
            <img :src="img" width="400" alt="图片暂时无法找到">
          </div>
      </el-drawer>
    </el-table>
    <add-dialog ref="addDialog" title="新增"  @confirmData="(item,fileList) => addroomtype(item,fileList)"/>
    <update-dialog ref="updateDialog" title="修改"  @confirmData="(item,fileList) => updateroomtype(item,fileList)"/>
  </div>
</template>

<script>
import AddDialog from './addRoomType'
import updateDialog from './addRoomType'
import axios from 'axios'
import { getRoomTypeList,addRoomType,updateRoomType,delRoomType } from '@/api/roomtype';
export default {
  components: {
    AddDialog,
    updateDialog
  },
  data () {
    return {
      loading: true,
      drawer: false,
      searchForm: {
        roomTypeName: '',
        introduce: '',
      },
      roomTypeList: [],
      bedTypeList: [],
      roomTypeData: {},
    delArr:[],
    arr:[],
    page: {
        currentPage: 0, // 当前页，对应接口中的page
        pageSize: 0, // 每页条数，对应接口中的limit
        totalSize: 0, // 中条数，对应接口中的res.data.page.totalRows
        totalPage: 0 // 总页数，对应接口中的res.data.page.totalPages
      },
    }
  },
  mounted () {
    this.getRoomTypeList(null);
  },
  methods: {
    getRoomTypeList(param) {
      getRoomTypeList(param).then(res => {
        if (res.data.code === 2000){
          this.roomTypeList = res.data.obj
          this.loading = false;
        } else if (res.data.code === 3) {
          alert('登录已过期，请重新登录')
          this.$router.push({ path:'/login'} );
        }
      })
    },
    mouseEnter (data) { // 这个数据拿不到
      this.roomTypeData = Object.assign({}, data)
    },
    addroomtype (item,fileList) {
      let imgUrls = []
      for (let i = 0; i < fileList.length; i++){
        imgUrls.push(fileList[i].url)
      }
      item.imgUrls = imgUrls.join()
      // console.log('canshu', item)
      addRoomType(item).then(res => {
        // console.log('新增客房返回数据是', res)
        if(res.data.code == 2000) {
          this.$message({
            type: 'success',
            message: '新增客房类型成功'
          })
          this.getRoomTypeList()
        } 
        if(res.data.code == 5) {
          this.$message({
            type: 'info',
            message: res.data.data
          })
        }
      })
    },
    updateroomtype (item,fileList) { // 修改客房类型
      let imgUrls = []
      fileList.forEach((ele,idx) => {
        imgUrls.push(ele.url)
      })
      item.imgUrls = imgUrls.join()
      delete item.imgList
      // console.log('修改客房类型item', item)
      updateRoomType(item).then(res => {
        // console.log('修改客房类型返回数据是', res)
        if(res.data.code == 2000) {
          this.$message({
            type: 'success',
            message: '修改客房类型成功'
          })
          this.getRoomTypeList()
        }
      })
    },
    handleSelectionChange(val){
           this.arr=val
    },
    handleDelete () {
      this.delArr=[]
      this.arr.forEach(e=>{
          this.delArr.push(e.typeId)
      })
      if(this.delArr.length>0){
        this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then((res) => {
        delRoomType(this.delArr).then(res => {
          if (res.data.code === 2000) {
            this.$message({
              type: 'success',
              message: '删除成功'
            })
            this.getRoomTypeList()
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
      }
      else{
          this.$message({
          type: 'info',
          message: '请选择要删除的列'
        })
      }
      
    }
  },
  created(){
   getRoomTypeList().then(res => {
          // console.log(res.data.obj);
          this.roomTypeList = res.data.obj
          this.loading = false;
        })
        .catch((error) => {
          console.log(error);
        });
  }
}
</script>

<style lang="less">
.cell {
    text-align: center;
}
#roomtype{
   .el-card__body {
    display: flex;
    .contentBox {
      padding: 14px;
      div{
        margin-bottom: 10px;
        a {
          margin: 0 15px 15px 0;
        }
      }
    }
  }
}
</style>
