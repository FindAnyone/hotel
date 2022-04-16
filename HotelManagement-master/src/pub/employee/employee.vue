<template>
    <div id="employee">
        <el-form v-model="searchForm" :inline="true">
            <el-row>
                <el-col :span="6" :offset="1">
                    <el-form-item label="员工号：">
                        <el-input v-model="searchForm.number" placeholder="请输入员工号" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="姓名：">
                        <el-input v-model="searchForm.readName" placeholder="请输入姓名" clearable />
                    </el-form-item>
                </el-col>
                 <el-col :span="6">
                    <el-form-item label="性别：">
                        <el-select v-model="searchForm.gender" placeholder="请选择性别" clearable>
                            <el-option key="1" label="男" value="1"></el-option>
                            <el-option key="2" label="女" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-search" @click="getempList(searchForm)">查询</el-button>
                        <el-button type="primary" icon="el-icon-plus" @click="$refs.addDialog.open(null)">新增</el-button>
                        <el-button type="primary" icon="el-icon-reduce" @click="delemp">删除</el-button>
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
            @sort-change="sortChange">
         <el-table-column type="selection" width="55"> </el-table-column>
            <el-table-column label="序号" type="index" width="55">
                <template slot-scope="scope">
                    <!-- (当前页 - 1) * 当前显示数据条数 + 当前行数据的索引 + 1  -->
                    <span>{{ (page.currentPage - 1) * page.pageSize + scope.$index + 1 }}</span>
                </template>
            </el-table-column>
           

            <el-table-column label="员工号" prop="empId" sortable="custom" />
            <el-table-column label="头像" prop="empImg" >
                 <template slot-scope="scope">            
              <img :src="scope.row.empImg"  min-width="70" height="70" class="userImg" />
        </template> 
            </el-table-column>
            <el-table-column label="姓名" prop="empName" />
            <el-table-column label="性别" prop="gender" >
                 <template slot-scope="scope">
                    {{ scope.row.gender ===1?'男':'女' }}
                </template>
            </el-table-column>

            <el-table-column label="身份证" prop="idCard" />
            <el-table-column label="手机" prop="empPhone" />
            <el-table-column label="职位" prop="position.position" />
            <el-table-column label="工资" prop="salary" />
            <el-table-column label="操作" prop="operation" width="150">
                <template>
                    <el-button type="text" icon="el-icon-edit" @click="$refs.updateDialog.open(empData)">修改</el-button>
                    <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <edit-dialog ref="addDialog" title="新增" @confirmData="(item) => addemp(item)" />
        <update-dialog ref="updateDialog" title="修改" @confirmData="(item) => update(item)" />
        <PageComponent :total="page.totalSize" :page="page" @pageChange="handlePageChange" />
    </div>
</template>

<script>
import editDialog from './addEmp';
import updateDialog from './update';
import { getempList, addemp, update, resetPassword, updatePassword, delemp } from '@/api/employee';
import PageComponent from '@/components/Pagenation/index';
export default {
    components: {
        PageComponent,
        editDialog,
        updateDialog
    },
    data() {
        return {
            loading: true,
            searchForm: {
                number: '',
                readName: '',
                gender: ''
            },
            
            empList: [],
            arr: [],
            sort: '',
            empData: {},
            page: {
                currentPage: 1, // 当前页，对应接口中的page
                pageSize: 10, // 每页条数，对应接口中的limit
                totalSize: 0, // 中条数，对应接口中的res.data.page.totalRows
                totalPage: 0 // 总页数，对应接口中的res.data.page.totalPages
            }
        };
    },
    mounted() {
        this.getempList(null);
    },
    methods: {
        handlePageChange(item) {
            // console.log(item);// currentPage=1  pageSize=30条
            // const para = { currentPage: item.currentPage, pageSize: item.pageSize };
            const para = { page: item.currentPage, limit: item.pageSize };
            this.getempList(para);
        },
        getempList(params) {
            getempList({
                empId: this.searchForm.number,
                empName: this.searchForm.readName,
                gender: this.searchForm.gender,
                pageNumber: this.page.currentPage,
                pageSize: this.page.pageSize,
                sort: this.sort
            })
                .then((res) => {
                    this.page.totalSize = res.data.obj.total;
                    this.page.totalPage = res.data.obj.pageCount;
                    this.empList = res.data.obj.list;
                    console.log(res.data.obj);
                    this.loading = false;
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        mouseEnter(data) {
            this.empData = Object.assign({}, data);
            // console.log(this.empData)
        },
        addemp(item) {
            // console.log('新增员工参数', item)
            const param = {
                empName: item.readName,
                grade: item.position,
                empPhone: item.telPhone,
                idCard: item.IDCard,
                salary: item.salary,
                gender: item.sex,
                empImg: item.headImg
            };
            addemp(param).then((res) => {
                if (res.data.code === 2000) {
                    this.$message({
                        type: 'success',
                        message: '新增员工成功'
                    });
                    this.getempList();
                }
            });
        },
        update(item) {
            console.log('修改员工信息', item);
            const param = {
                empId: item.empId,
                empName: item.empName,
                gender: item.gender,
                grade: item.grade,
                empPhone: item.empPhone,
                empImg: item.empImg,
                salary: item.salary,
                idCard: item.idCard
            };
            update(param).then((res) => {
                if (res.data.code === 2000) {
                    this.$message({
                        type: 'success',
                        message: '修改成功'
                    });
                }
                this.getempList();
            });
        },
        handleSelectionChange(val) {
            this.arr = val;
            console.log(this.arr)
        },

        //排序
        sortChange(column, prop, order) {
            if (column.order == 'descending') {
                this.sort = 'desc';
            } else {
                this.sort = '';
            }
            this.getempList();
        },

        //批量删除
        delemp() {
            this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            })
                .then((res) => {
                    this.delArr = [];
                    //把id遍历进新的数组
                    this.arr.forEach((e) => {
                        this.delArr.push(e.empId);
                    });

                    // console.log(this.delArr);

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
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消删除'
                    });
                });
        },
        //单个删除
        handleDelete() {
            this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            })
                .then((res) => {
                    console.log(this.empData.empId);
                    delemp({
                        ids: [this.empData.empId]
                    }).then((res) => {
                        if (res.data.code == 2000) {
                            this.getempList();
                        } else {
                            alert('删除失败');
                        }
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消删除'
                    });
                });
        }
    }
};
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
