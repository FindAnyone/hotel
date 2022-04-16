<template>
    <div id="roomstate">
        <el-form v-model="searchForm" :inline="true">
            <el-row>
                <el-col :span="6">
                    <el-form-item label="预定编号：">
                        <el-input v-model="searchForm.engageId" placeholder="请输入预定编号" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="姓名">
                        <el-input v-model="searchForm.name" placeholder="请输入预定编号" clearable />
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
                <el-col :span="6">
                    <el-form-item label="手机号码">
                        <el-input v-model="searchForm.phone" placeholder="请输入预定编号" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-search" @click="getengageList(searchForm)">查询</el-button>

                        <el-button id="btn" type="primary" icon="el-icon-search" @click="handleDelete()">删除</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <el-table
            border
            height="600"
            :data="engageList"
            v-loading="loading"
            element-loading-text="拼命加载中"
            @selection-change="handleSelectionChange"
            @cell-mouse-enter="mouseEnter"
        >
            <el-table-column type="selection" width="55"> </el-table-column>
            <el-table-column label="预订编号" prop="engageId" />
            <el-table-column label="房间类别" prop="roomType.roomTypeName" width="100" />
            <el-table-column label="用户名" prop="users.name" />
            <el-table-column label="入住人数" prop="quantity" />
            <el-table-column label="入住时间" prop="checkTime" />
            <el-table-column label="退房时间" prop="outTime" />
            <el-table-column label="总价格" prop="total" />
            <el-table-column label="状态" prop="state">
                <template slot-scope="scope">
                    {{ scope.row.state === 1 ? '未支付' : '已支付' }}
                </template>
            </el-table-column>

            <el-table-column label="操作" prop="operation" width="200">
                <template>
                    <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete">删除</el-button>
                    <el-button type="text" icon="el-icon-edit" class="red" @click="$refs.Operation.open(engageData)">分配房间</el-button>
                </template>
            </el-table-column>
        </el-table>
        <Operation ref="Operation" title="新增" @confirmData="(item) => operation(item)" />

        <page-component :total="page.totalPage" :page="page" @pageChange="(item) => handlePageChange(item)" />
    </div>
</template>

<script>
import PageComponent from '@/components/Pagenation/index';
import { getengageList } from '@/api/engage';
import { deleteengage } from '@/api/engage';
import { addorder } from '@/api/order';

import Operation from './operation';

export default {
    components: {
        PageComponent,
        deleteengage,
        Operation
    },
    data() {
        return {
            loading: true,
            drawer: false, // 控制抽屉的显影

            searchForm: {
                pageNumber: 1, // 当前页，对应接口中的page
                pageSize: 10, // 每页条数，对应接口中的limit
                engageId: '',
                name: '',
                gender: '',
                phone: ''
            },
            engageList: [],
            engageData: {},
            delArr: [],
            arr: [],
            page: {
                currentPage: 1, // 当前页，对应接口中的page
                pageSize: 10, // 每页条数，对应接口中的limit
                totalSize: 0, // 中条数，对应接口中的res.data.page.totalRows
                totalPage: 0 // 总页数，对应接口中的res.data.page.totalPages
            }
        };
    },
    mounted() {
        this.getengageList();
    },
    methods: {
        //按页数和每页条数查询
        handlePageChange(item) {
            console.log(item); // currentPage=1  pageSize=30条
            const para = { pageNumber: item.currentPage, pageSize: item.pageSize };
            console.log(para);
            this.getengageList(para);
        },

        //按预订编号查询
        getengageList(param) {
            getengageList({
                pageNumber: this.page.currentPage,
                pageSize: this.page.pageSize
            }).then((res) => {
                console.log(res.data.obj);
                this.engageList = res.data.obj.list;
                this.page.totalPage = res.data.obj.total;
                this.loading = false;
            });
        },

        mouseEnter(data) {
            this.engageData = Object.assign({}, data);
            // console.log(this.engageData)
        },

        operation(item) {
            console.log('addroom:');
            console.log(item);
            addorder(item).then((res) => {
                if (res.data.code === 2000) {
                    this.$message({
                        type: 'success',
                        message: '操作成功'
                    });
                }
                this.getengageList();
            });
        },

        handleSelectionChange(val) {
            this.arr = val;
            console.log(this.arr);
        },
        //删除

        handleDelete() {
            // this.delArr=[];
            this.arr.forEach((e) => {
                this.delArr.push(e.engageId);
            });
            console.log(this.delArr);
            if (this.delArr.length > 0) {
                this.$confirm('此操作将永久删除已选择的数据，是否继续？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    center: true
                })
                    .then((res) => {
                        deleteengage(this.delArr).then((res) => {
                            if (res.data.code === 2000) {
                                this.$message({
                                    type: 'success',
                                    message: '删除成功'
                                });
                                this.$router.go(0);
                            }
                        });
                    })
                    .catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });
            } else {
                this.$message({
                    type: 'info',
                    message: '请选择要删除的列'
                });
            }
        }
    }
};
</script>

<style lang="less">
#room {
    .el-card__body {
        display: flex;
        .contentBox {
            padding: 14px;
            div {
                margin-bottom: 10px;
                a {
                    margin: 0 15px 15px 0;
                }
            }
        }
    }
}
#btn {
    margin-left: 10px;
}
</style>
