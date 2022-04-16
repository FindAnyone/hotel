<template>
  <div id="users">
    <el-form v-model="searchForm" :inline="true">
      <el-row>
        <el-col :span="6" :offset="1">
          <el-form-item label="账号编号：">
            <el-input v-model="searchForm.number" placeholder="请输入账号编号" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="姓名：">
            <el-input v-model="searchForm.readName" placeholder="请输入姓名" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="6">
            <el-form-item>
                <el-select v-model="searchForm.sex" placeholder="请输入性别" clearable>
                    <el-option key="1" label="男" value="1"></el-option>
                    <el-option key="2" label="女" value="0"></el-option>
                </el-select>
            </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="getempList(searchForm)">查询</el-button>
            <el-button type="primary" icon="el-icon-plus" @click="$refs.addDialog.open(null)">新增</el-button>
            <el-button type="primary" icon="el-icon-minus" @click="delusers">批量删除</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <!-- el-table中的height用于固定表头 -->
    <el-table
      border
      height="600"
      :data="empList"
      v-loading="loading"
      element-loading-text="拼命加载中"
      @cell-mouse-enter="mouseEnter"
      @selection-change="handleSelectionChange"
        >
        <el-table-column type="selection" width="55"/>
       <el-table-column label="序号" type="index" width="55">
        <template slot-scope="scope">
          <!-- (当前页 - 1) * 当前显示数据条数 + 当前行数据的索引 + 1  -->
          <span>{{ (page.currentPage - 1) * page.pageSize + scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      
      <el-table-column label="用户编号" prop="userId"/>
      <el-table-column label="用户名" prop="userName"/>
      <el-table-column label="级别" >
        <template slot-scope="scope">
          <el-tag :type="scope.row.grade | statusFilter">{{scope.row.grade | formatStata}}</el-tag>
      </template>
      </el-table-column>
      <el-table-column label="性别" prop="gender">
         <template slot-scope="scope">
                    {{ scope.row.gender ===1?'男':'女' }}
                </template>
      </el-table-column>
      <el-table-column label="姓名" prop="name"/>
      <el-table-column label="电话" prop="phone"/>     
      <el-table-column label="出生日期" prop="birthday"/>
      <el-table-column label="身份证" prop="idCard"/>
      
      <el-table-column label="地址" prop="address"/>

       <el-table-column label="图片"  width="150">
        <template slot-scope="scope">            
              <img :src="scope.row.userImg"  min-width="70" height="70" class="userImg" />
        </template> 
      </el-table-column>

      <el-table-column label="地址" prop="position.positionName"/>
      
      <el-table-column label="操作" prop="operation" width="200">

        <template>
          <el-button
            type="text"
            icon="el-icon-edit"
            @click="$refs.updateDialog.open(empData)"
          >查看详情</el-button>

          <el-button
            type="text"
            icon="el-icon-delete"
            class="red"
            @click="handleDelete"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <edit-dialog ref="addDialog" title="新增" @confirmData="(item) => addemp(item)"/>
    <update-dialog ref="updateDialog" title="修改" @confirmData="(item) => update(item)"/>
    <page-component :total="page.totalSize" :page="page" @pageChange="(item)=>handlePageChange(item)" />
  </div>
</template>

<script>
import editDialog from './addEmp'
import updateDialog from './update'
import { getempList,addemp, update, resetPassword,updatePassword,delemp } from '@/api/users';
import PageComponent from '@/components/Pagenation/index'
export default {
  components: {
    PageComponent,
    editDialog,
    updateDialog
  },
  data () {
    return {
      loading: true,
      searchForm: {
        number:'',
        readName:'',
        sex:''
      },
      empList: [],
      empData: {},
      page: {
        currentPage: 1, // 当前页，对应接口中的page
        pageSize: 10, // 每页条数，对应接口中的limit
        totalSize: 0, // 中条数，对应接口中的res.data.page.totalRows
        totalPage: 0// 总页数，对应接口中的res.data.page.totalPagess
      },
      delArr:[],
      arr:[],
    }
  },
  mounted () {
    this.getempList();
  },
  methods: {
    handlePageChange(item) {
      const para = { page: item.currentPage, limit: item.pageSize };
      console.log(para)
      console.log(item)
      this.getempList(para);

    },

  handleSelectionChange(val) {
             this.arr = val;
			console.log(this.arr)
        },


        delusers() {
            this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then((res) => {
                    this.delArr = [];
                    //把id遍历进新的数组
                    this.arr.forEach((e) => {
                        this.delArr.push(e.userId);
                    });

                    console.log(this.delArr);
                    if (this.delArr.length > 0) {
                        delemp({
                            ids: this.delArr
                        }).then((res) => {
                                if (res.data.code == 2000) {
                                    this.$message({
                                        type: 'success',
                                        message: '删除成功'
                                    });
                                    this.getempList();
                                } else {
                                    alert('删除失败');
                                }
                            })
                            .catch((error) => {
                                console.log(error);
                            });
                    } else {
                        alert('请选择');
                    }
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消删除'
                    });
                });
        },

    getempList() { 
      const param={
        userId:this.searchForm.number,
        name:this.searchForm.readName,
        gender:this.searchForm.sex,
        pageNumber:this.page.currentPage,
        pageSize:this.page.pageSize,

      }
      getempList(param)     
      .then(res => {   
        this.empList = res.data.obj.list;
        this.pageNumber=this.page.currentPage,
        this.pageSize=this.page.pageSize,
        this.page.totalSize=res.data.obj.total,
        //查询传回
        console.log('查询传回param:'+param)
        this.loading=false

//翻页再设定
        this.page.currentPage = res.data.obj.pageNumber
        this.page.pageSize = res.data.obj.pageSize
        this.page.totalSize = res.data.obj.total
        this.page.totalPage=res.data.obj.pageCount
        console.log('返回的数据是',res.data)
        if (res.data.code === 2000) {
          this.$message({
            type: 'success',
            message: res.data.msg
          })
        }
        this.empList = res.data.obj.list;
        this.loading = false;
      })
    },
    mouseEnter (data) {
      this.empData = Object.assign({}, data)
      // console.log('this.empData', this.empData)
    },
    addemp (item) {
      console.log('新增员工参数', item)
      const param = {
        //绑定实体
        userName: item.number,
        name: item.readName,
        positionId: item.position,
        phone: item.telPhone,
        gender: item.sex,
        headImg: item.headImg,

        birthday:item.birthday,
        idcard:item.idcard,
        address:item.address,
        grade:item.grade,
      }
      //执行请求,将param返回到请求头
      addemp(param).then(res => {
        console.log(param);
        if (res.data.code === 2000) {
          this.$message({
            type: 'success',
            message: '新增员工成功'
          })
          this.getempList()
        }
      })
    },
    update (item) {
      console.log('修改员工信息item',item)
      const param = {
        userId: item.userId,
        grade: item.grade,
        name: item.name,
        phone: item.phone,
        address: item.address,
        idCard: item.idCard,
        gender: item.gender,
        userImg: item.userImg,
        birthday: item.birthday,
        password: item.password,
        userName: item.number,
      }
      update(param).then(res =>{
        if(res.data.code === 2000) {
          this.$message({
            type: 'success',
            message: '修改成功'
          })
        }
        this.getempList()
      })
    },

   handleDelete () {
      this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then((res) => {
      
        delemp({
          ids:[this.empData.userId]
        }).then(res => {
          if (res.data.code === 2000) {
            this.$message({
              type: 'success',
              message: '删除成功'
            })
            
          }
          this.getempList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消删除'
        })
      })
    },
    handleReset() { // 重置密码
      this.$confirm('是否重置密码为123abc?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: 'true'
      }).then((res) => {
        const employeeId = this.empData.employeeId
        resetPassword(employeeId).then(res => {
          if(res.data.code === 0) {
            this.$message({
              type: 'success',
              message: '重置密码成功'
            })
          }
          this.$router.push({ path: '/users' })
        })
      }).catch((res) => {
        this.$message({
          type: 'info',
          message: '取消重置密码'
        })
        this.$router.push({ path: '/users' })
      })
    },
    
  },
  filters: {
    // el-tag类型转换
    statusFilter(status) {
      const statusMap = {
        
        1: 'success',
        2: 'info',
        3:'danger'
      }
      return statusMap[status]
    },
    // 状态显示转换
    formatStata(status) {
      const statusMap = {
        1: '管理员',
        2: '普通用户',
        3: '会员'
      }
      return statusMap[status]
    }
  },
}
</script>

<style lang="less">
.cell {
    text-align: center;
}
.userImg{
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
</style>
