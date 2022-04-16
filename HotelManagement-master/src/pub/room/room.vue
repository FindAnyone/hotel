<template>
  <div id="roomstate">
    <el-form v-model="searchForm" :inline="true">
      <el-row>
        <el-col :span="6">
          <el-form-item label="房号：">
            <el-input v-model="searchForm.roomNumber" placeholder="请输入房号" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="客房类型：" prop="typeId">
            <el-select v-model="searchForm.typeId" placeholder="请选择客房类型" clearable>
              <el-option
                v-for="item in roomTypeList"
                :key="item.typeId"
                :label="item.roomTypeName"
                :value="item.typeId">
              </el-option>
            </el-select> 
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="可住人数：">
            <el-input v-model="searchForm.peopleNum" placeholder="请输入可住人数" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="楼层：">
            <el-input v-model="searchForm.floor" placeholder="请输入楼层" clearable/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
       
        <el-col :span="6">
          <el-form-item label="客房状态：">
            <el-select v-model="searchForm.state" placeholder="请选择客房状态" clearable>
              <el-option key="1" label="已打扫" value="1"></el-option>
              <el-option key="2" label="未打扫" value="2"></el-option>
            
            </el-select>
          </el-form-item>
        </el-col>
        <!-- <el-col :span="12">
          <el-form-item label="房费：" prop="startMoney">
            <el-input v-model="searchForm.startPrice" placeholder="最低房费" clearable style="width:48%"/>-
            <el-input v-model="searchForm.endPrice" placeholder="最高房费" clearable style="width:48%"/>
          </el-form-item>
        </el-col> -->
         <el-button type="primary" icon="el-icon-search" @click="getroomAllList(searchForm)">查询</el-button>
      <el-button type="primary" icon="el-icon-plus" @click="$refs.addDialog.open(null)">新增</el-button>
      <el-button type="primary" icon="el-icon-delete" @click="handleDelete">一键删除</el-button>
      </el-row>
     
    </el-form>
    <el-table
      border
      height="600"
      :data="roomList"
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
      <el-table-column label="房号" prop="roomNumber"/>
      <el-table-column label="图片" prop="roomImg" width="150">
        <template slot-scope="scope">            
              <img :src="scope.row.roomImg"  min-width="70" height="70" />
        </template> 
      </el-table-column>
      <el-table-column label="客房类型" prop="roomType.roomTypeName" width="100"/>
       <el-table-column label="楼层" prop="floor" >
                <template slot-scope="scope">
                    {{scope.row.floor}} 楼
                </template>
            </el-table-column>
       <el-table-column label="面积" prop="area">
              <template slot-scope="scope">
                    {{scope.row.area}} 平米
                </template>
                </el-table-column>
      <el-table-column label="可住人数" prop="peopleNum"/>
      <el-table-column label="价格" prop="roomPrice"/>
      <el-table-column label="是否有窗" >
        <template slot-scope="scope">{{ scope.row.window===1?'有':'无' }}</template>
      </el-table-column>
      
      <el-table-column label="客房状态" width="120">
        <template slot-scope="scope">
          <el-tag :type="scope.row.state | statusFilter">{{scope.row.state | formatStata}}</el-tag>
      </template>
      </el-table-column>
      <el-table-column label="客房设施" prop="facilities" width="300"/>
      <el-table-column label="备注" prop="remark" width="200"/>
      <el-table-column label="操作" prop="operation" width="240">
        <template>
          <el-button
            type="text"
            icon="el-icon-edit"
            @click="$refs.updateDialog.open(roomData)"
          >查看详情</el-button>
          <el-button
            type="text"
            style="margin-left: 16px;"
            icon="el-icon-s-tools"
            @click="open"
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
         <div class="img">
            <img :src="roomImg" width="400" alt="图片暂时无法找到">
         </div>
      </el-drawer>
    </el-table>
    <add-dialog ref="addDialog" title="新增"  @confirmData="(item,fileList) => addroom(item,fileList)"/>
    <update-dialog ref="updateDialog" title="修改"  @confirmData="(item,fileList) => updateroom(item,fileList)"/>
    <!-- <page-component :total="page.totalSize" :page="page" @pageChange="(item)=>handlePageChange(item)" /> -->
  </div>
</template>

<script>
import AddDialog from './addroom'
import updateDialog from './addroom'
import { addroomx,updateroom,delroom,getroomAllList } from '@/api/room';
import { getAllRoomTypeList } from '@/api/roomtype'
// import PageComponent from '@/components/Pagenation/index'
export default {
  components: {
    // PageComponent,
    AddDialog,
    updateDialog
  },
  data () {
    return {
      loading: true,
      drawer: false, // 控制抽屉的显影
      searchForm: {
        roomNumber:'',
        typeId:'',
        peopleNum: '',
        floor: '',
        state: '',
      },
      roomList: [],
      bedTypeList: [],
      roomTypeList: [], // 客房类型
      roomData: [], // 保存该条数据的roomId
      page: {
        currentPage: 0, // 当前页，对应接口中的page
        pageSize: 0, // 每页条数，对应接口中的limit
        totalSize: 0, // 中条数，对应接口中的res.data.page.totalRows
        totalPage: 0 // 总页数，对应接口中的res.data.page.totalPages
      },
      roomImg:"",
    delArr:[],
    arr:[]
    }
  },
  mounted () {
    this.getroomAllList()
    this.getAllRoomTypeList()
  },
  methods: {
    open(){
      this.drawer=true
      this.roomImg=this.roomData.roomImg
    },
    mouseEnter (data) {
      this.roomData = Object.assign({}, data)
      
    },
  
    getroomAllList() {
      const param={
        
        roomNumber:this.searchForm.roomNumber,
        rTypeId:this.searchForm.typeId,
        peopleNum:this.searchForm.peopleNum,
        floor:this.searchForm.floor,
        state:this.searchForm.state
      }
      console.log(param)
      getroomAllList(param).then(res => {
        if (res.data.code === 2000){
          console.log('返回的客房类型数据是',res.data.obj)
          this.roomList = res.data.obj         
          this.loading = false;
        } 
      })
    },

    getAllRoomTypeList() {
      getAllRoomTypeList().then(res => {
        if(res.data.code === 2000){
          console.log(res.data.obj)
          this.roomTypeList = res.data.obj
        }
      })
    },
    addroom (item,fileList) {
      console.log("addroom:")
      console.log(item)
      let imgUrls = []
      fileList.forEach((ele,idx) => {
        imgUrls.push(ele.url)
      })
      // console.log('imgUrls', imgUrls)
      item.imgUrls = imgUrls.join()
      item.roomType = item.roomTypeName
      delete item.imgList
      delete item.roomTypeName
      delete item.roomTypeId
      // console.log('新增客房的canshu是', item)
      addroomx(item).then(res => {
        console.log('新增客房返回数据是', res)
        if(res.data.code == 2000) {
          
          this.$message({
            type: 'success',
            message: '新增客房成功'
          })
          this.getroomAllList()
 
        } 
        if(res.data.code == 600) {
          this.$message({
            type: 'info',
            message: res.data.data
          })
        }
      })
    },
    updateroom (item,fileList) { // 修改客房类型
    // console.log('修改客房参数是', item)
      let imgUrls = []
      fileList.forEach((ele,idx) => {
        imgUrls.push(ele.url)
      })
      item.imgUrls = imgUrls.join()
      delete item.imgList
      // console.log('修改客房参数是', item)

      updateroom(item).then(res => {
        console.log('修改客房类型返回数据是', res)
        if(res.data.code == 2000) {
          this.$message({
            type: 'success',
            message: '修改客房详情成功'
          })
          this.getroomAllList()
        }
      })
    },

    handleSelectionChange(val){
           this.arr=val
    },

    handleDelete () {
      this.delArr=[]
      this.arr.forEach(e=>{
          this.delArr.push(e.roomId)
      })
      if(this.delArr.length>0){
        this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then((res) => {
        delroom(this.delArr).then(res => {
          if (res.data.code === 2000) {
            this.$message({
              type: 'success',
              message: '删除成功'
            })
            this.getroomAllList()
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
     filters: {
    // el-tag类型转换
    statusFilter(status) {
      const statusMap = {
        
        1: 'success',
        2: 'info',
      }
      return statusMap[status]
    },
    // 状态显示转换
    formatStata(status) {
      const statusMap = {
        1: '已打扫',
        2: '未打扫',
      }
      return statusMap[status]
    }
  },
}
</script>

<style lang="less">
.img{
  text-align: center;
}
.el-drawer__body {
  overflow: auto;
}
#room{

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
.cell {
    text-align: center;
}
</style>
