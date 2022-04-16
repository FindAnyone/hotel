<template>
    <div id="position">
        <el-form v-model="searchForm" :inline="true">
            <el-row>
                <el-col :span="6" :offset="1">
                    <el-form-item label="职位：">
                        <el-input v-model="searchForm.position" placeholder="请输入职位" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="6">
                    <el-form-item label="职位描述：">
                        <el-input v-model="searchForm.introduce" placeholder="请输入职位描述" clearable />
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-search" @click="getPositionList(searchForm)">查询</el-button>
                        <el-button type="primary" icon="el-icon-plus" @click="$refs.addDialog.open(null)">新增</el-button>
                        <el-button type="primary" icon="el-icon-reduce" @click="delPosition">删除</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <!-- el-table中的height用于固定表头 -->
        <el-table
            border
            height="380"
            :data="positionList"
            v-loading="loading"
            element-loading-text="拼命加载中"
            @cell-mouse-enter="mouseEnter"
            @selection-change="handleSelectionChange"
        >
            <el-table-column type="selection" align="center" />
            <el-table-column label="序号" type="index" width="55">
                <template slot-scope="scope">
                    <!-- (当前页 - 1) * 当前显示数据条数 + 当前行数据的索引 + 1  -->
                    <span>{{ (page.currentPage - 1) * page.pageSize + scope.$index + 1 }}</span>
                </template>
            </el-table-column>
            <el-table-column label="职位编号" prop="positionId" />
            <el-table-column label="职位" prop="position" />
            <el-table-column label="职位描述" prop="introduce" />
            <el-table-column label="操作" prop="operation" width="200">
                <template>
                    <el-button type="text" icon="el-icon-edit" @click="$refs.updateDialog.open(positionData)">修改</el-button>
                    <el-button type="text" icon="el-icon-delete" class="red" @click="deletePosition(positionData)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <add-dialog ref="addDialog" title="新增" @confirmData="(item) => addPosition(item)" />
        <update-dialog ref="updateDialog" title="修改" @confirmData="(item) => updatePosition(item)" />
        <page-component :total="page.totalSize" :page="page" @pageChange="(item) => handlePageChange(item)" />
    </div>
</template>

<script>
import AddDialog from './addPosition';
import updateDialog from './addPosition';
import { getPositionPageList, addPosition, updatePosition, deletePosition } from '@/api/position';
import PageComponent from '@/components/Pagenation/index';
import { delImg } from '@/api/room';

export default {
    components: {
        PageComponent,
        AddDialog,
        updateDialog
    },
    data() {
        return {
            loading: true,
            searchForm: {
                position: '',
                introduce: ''
            },
            arr: [],
            positionList: [],
            positionData: {},
            page: {
                currentPage: 1, // 当前页，对应接口中的page
                pageSize: 10, // 每页条数，对应接口中的limit
                totalSize: 0, // 中条数，对应接口中的res.data.page.totalRows
                totalPage: 0 // 总页数，对应接口中的res.data.page.totalPages
            }
        };
    },
    mounted() {
        this.getPositionPageList();
    },
    methods: {
        handlePageChange(item) {
            const para = { page: item.currentPage, limit: item.pageSize };
            this.getPositionPageList(para);
        },
        handleSelectionChange(val) {
            this.arr = val;
            console.log(this.arr);
        },
        getPositionPageList() {
            const param = {
                pageNumber: this.page.currentPage,
                pageSize: this.page.pageSize,
                position: this.searchForm.position,
                introduce: this.searchForm.introduce
            };
            getPositionPageList(param).then((res) => {
                this.page.currentPage = res.data.obj.pageNumber;
                this.page.pageSize = res.data.obj.pageSize;
                this.page.totalSize = res.data.obj.total;

                this.positionList = res.data.obj.list;
                this.loading = false;
            });
        },

        mouseEnter(data) {
            this.positionData = Object.assign({}, data);
            // console.log(this.positionData)
        },

        addPosition(item) {
            console.log('新增职位信息', item);
            addPosition(item).then((res) => {
                console.log(res.data);
                if (res.data.code === 2000) {
                    this.$message({
                        type: 'success',
                        message: '新增职位成功'
                    });
                }
                this.getPositionPageList();
            });
        },

        updatePosition(item) {
            const param = {
                introduce: item.introduce,
                positionId: item.positionId,
                position: item.position
            };
            // console.log('修改职位信息param', param)
            updatePosition(param).then((res) => {
                console.log('返回的修改职位信息是', res.data);
                if (res.data.code === 2000) {
                    this.$message({
                        type: 'success',
                        message: '修改职位成功'
                    });
                }
                this.getPositionPageList();
            });
        },

        //批量删除
        delPosition() {
            this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then((res) => {
                this.delArr = [];
                this.arr.forEach((e) => {
                    this.delArr.push(e.positionId);
                });
                // console.log(this.delArr);
                if (this.delArr.length > 0) {
                    deletePosition({
                        ids: this.delArr
                    }).then((res) => {
                        if (res.data.code == 2000) {
                            this.getPositionPageList();
                        } else {
                            this.$message({
                                type: 'info',
                                message: '已取消删除'
                            });
                        }
                    });
                }
            }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
        },

        //单个删除
        deletePosition() {
            this.$confirm('此操作将永久删除该数据，是否继续？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then((res) => {
                    deletePosition({
                      ids:[this.positionData.positionId]
                    }).then((res) => {
                        if (res.data.code === 2000) {
                            this.$message({
                                type: 'success',
                                message: '删除成功'
                            });
                            this.getPositionPageList();
                        }
                    });
                })
                .catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
        }
    }
};
</script>

<style lang="less">
</style>
