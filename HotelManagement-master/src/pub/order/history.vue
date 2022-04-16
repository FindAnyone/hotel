<template>
    <div id="roomstate">
        <el-form v-model="searchForm" :inline="true">
            <el-row>
                <el-col :span="6">
                    <el-form-item label="订单号：">
                        <el-input v-model="searchForm.orderId" placeholder="请输入订单号" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="房间号：">
                        <el-input v-model="searchForm.roomId" placeholder="请输入房间号" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="客房类型：" prop="typeId">
                        <el-select v-model="searchForm.typeId" placeholder="请选择客房类型" clearable>
                            <el-option v-for="item in typeList" :key="item.typeId" :label="item.typeName" :value="item.typeName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="姓名：">
                        <el-input v-model="searchForm.name" placeholder="请输入姓名" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="楼层：">
                        <el-input v-model="searchForm.floor" placeholder="请输入楼层" clearable />
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
                <el-button type="primary" icon="el-icon-search" @click="getorderList(searchForm)">查询</el-button>
                <el-button type="primary" icon="el-icon-reduce" @click="delorder">删除</el-button>
            </el-row>
        </el-form>
        <el-table
            border
            height="600"
            :data="orderList"
            v-loading="loading"
            element-loading-text="拼命加载中"
            @cell-mouse-enter="mouseEnter"
            @selection-change="handleSelectionChange"
            @sort-change="sortChange"
        >
            <el-table-column type="selection" width="55"> </el-table-column>
            <el-table-column label="序号" type="index" width="55">
                <template slot-scope="scope">
                    <!-- (当前页 - 1) * 当前显示数据条数 + 当前行数据的索引 + 1  -->
                    <span>{{ (page.currentPage - 1) * page.pageSize + scope.$index + 1 }}</span>
                </template>
            </el-table-column>

            <el-table-column label="订单号" prop="orderId" sortable="custom" />
            <el-table-column label="用户" prop="users.name" />
            <el-table-column label="性别">
                <template slot-scope="scope">
                    {{ scope.row.users.gender === 1 ? '男' : '女' }}
                </template>
            </el-table-column>
            <el-table-column label="手机号" prop="users.phone" />
            <el-table-column label="房间类型" prop="roomType.roomTypeName" />
            <el-table-column label="楼层" prop="roomDetailet.floor">
                <template slot-scope="scope"> {{ scope.row.roomDetailet.floor }} 楼 </template>
            </el-table-column>
            <el-table-column label="房号" prop="roomDetailet.roomNumber" />

            <el-table-column label="入住时间" prop="checkTime" />
            <el-table-column label="退房时间" prop="outTime" />
            <el-table-column label="面积" prop="roomDetailet.area">
                <template slot-scope="scope"> {{ scope.row.roomDetailet.area }} 平米 </template>
            </el-table-column>
            <el-table-column label="操作">
                <template>
                    <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <PageComponent :total="page.totalSize" :page="page" @pageChange="handlePageChange" />
    </div>
</template>

<script scoped>
import { getorderList, delorder } from '@/api/historyorder';
import PageComponent from '@/components/Pagenation/index';
export default {
    components: {
        PageComponent
    },
    data() {
        return {
            loading: true,
            drawer: false, // 控制抽屉的显影
            searchForm: {
                orderId: '',
                roomId: '',
                typeId: '',
                name: '',
                floor: '',
                gender: ''
            },
            arr: [],
            sort: '',
            orderList: [],
            typeList: [], // 客房类型
            orderData: '', // 保存该条数据的roomId
            page: {
                currentPage: 1, // 当前页，对应接口中的page
                pageSize: 10, // 每页条数，对应接口中的limit
                totalSize: 0, // 中条数，对应接口中的res.data.page.totalRows
                totalPage: 0 // 总页数，对应接口中的res.data.page.totalPages
            }
        };
    },
    methods: {
        handlePageChange(item) {
            // console.log(item);// currentPage=1  pageSize=30条
            // const para = { currentPage: item.currentPage, pageSize: item.pageSize };
            const para = { page: item.currentPage, limit: item.pageSize };
            this.getorderList(para);
        },
        getorderList() {
            this.getorderList();
        },
        handleSelectionChange(val) {
            this.arr = val;
            console.log(this.arr);
        },
        mouseEnter(data) {
            this.orderData = Object.assign({}, data);
        },
        handleDelete() {
            this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            })
                .then((res) => {
                    delorder({
                        ids: [this.orderData.orderId]
                    }).then((res) => {
                        console.log(res);
                        this.getorderList();
                    });
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
        },
        delorder() {
            this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            })
                .then((res) => {
                    this.delArr = [];
                    this.arr.forEach((e) => {
                        this.delArr.push(e.orderId);
                    });
                    delorder({
                        ids: this.delArr
                    }).then((res) => {
                        console.log(res);
                        this.getorderList();
                    });
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
        },
        sortChange(column, prop, order) {
            if (column.order == 'descending') {
                this.sort = 'desc';
            } else {
                this.sort = '';
            }
            this.getorderList();
        },

        getorderList() {
            getorderList({
                orderId: this.searchForm.orderId,
                roomId: this.searchForm.roomId,
                typeId: this.searchForm.typeId,
                floor: this.searchForm.floor,
                name: this.searchForm.name,
                gender: this.searchForm.gender,
                pageNumber: this.page.currentPage,
                pageSize: this.page.pageSize,
                sort: this.sort
            }).then((res) => {
                this.orderList = res.data.obj.list;
                (this.page.currentPage = res.data.obj.pageNumber),
                    (this.page.pageSize = res.data.obj.pageSize),
                    (this.page.totalSize = res.data.obj.total);
                console.log(res.data.obj);
                this.loading = false;
            });
        }
    },
    created() {
        this.getorderList();
    }
};
</script>

<style lang="less">
.el-drawer__body {
    overflow: auto;
}
.cell {
    text-align: center;
}
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
</style>
