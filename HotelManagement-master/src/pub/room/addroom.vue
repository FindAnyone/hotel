<template>
    <div id="roomAdd">
        <el-dialog
            :title="title"
            :visible.sync="visible"
            top="0.5rem"
            :lock-scroll="false"
            :show-close="false"
            :close-on-click-modal="false"
        >
            <el-form ref="roomForm" :model="item" label-width="120px">
                <el-form-item label="房号：" prop="roomNumber">
                    <el-input v-model="item.roomNumber" placeholder="请输入房号" clearable />
                </el-form-item>
                <el-form-item label="客房类型：" prop="rtypeId">
                    <el-select v-model="item.rtypeId" placeholder="请选择客房类型" clearable>
                        <el-option v-for="i in roomTypeList" :key="i.typeId" :label="i.roomTypeName" :value="i.typeId"> </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="面积：" prop="area">
                    <el-input v-model="item.area" placeholder="请输入面积" clearable />
                </el-form-item>
                <el-form-item label="可住人数：" prop="peopleNum">
                    <el-input v-model="item.peopleNum" placeholder="请输入可住人数" clearable />
                </el-form-item>
                <el-form-item label="客房状态：">
                    <el-select v-model="item.state" placeholder="请选择客房状态" clearable>
                        <el-option key="1" label="已打扫" :value="1"></el-option>
                        <el-option key="2" label="未打扫" :value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="楼层：" prop="floor">
                    <el-input v-model="item.floor" placeholder="请输入楼层" clearable />
                </el-form-item>
                <el-form-item label="价格：" prop="roomPrice">
                    <el-input v-model="item.roomPrice" placeholder="请输入价格" clearable />
                </el-form-item>
                <el-form-item label="是否有窗:" prop="window">
                    <el-radio-group v-model="item.window">
                        <el-radio v-model="item.window" :label="1" border>有窗</el-radio>
                        <el-radio v-model="item.window" :label="0" border>无窗</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="房间设施说明：" prop="facilities">
                    <el-input
                        type="textarea"
                        :rows="2"
                        v-model="item.facilities"
                        resize="none"
                        maxlength="200"
                        show-word-limit
                        palceholder="房间设施说明"
                    ></el-input>
                </el-form-item>
                <el-form-item label="备注：" prop="remark">
                    <el-input
                        type="textarea"
                        :rows="2"
                        v-model="item.remark"
                        resize="none"
                        maxlength="200"
                        show-word-limit
                        palceholder="床型具体说明"
                    ></el-input>
                </el-form-item>
                <el-form-item label="文件上传：">
                    <el-upload
                        action="http://localhost:8080/hotel/FileUpload/upload"
                        list-type="picture-card"
                        name="files"
                        :on-preview="handlePictureCardPreview"
                        :on-remove="handleRemove"
                        :on-success="handleSuccess"
                    >
                        <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="dialogImageUrl" alt="" />
                    </el-dialog>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button type="primary" @click="resetForm('roomForm')">取消</el-button>
                <el-button type="primary" @click="save('roomForm')">提交</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { getAllRoomTypeList, getRoomTypeById } from '@/api/roomtype';
import { getroomById ,delImg } from '@/api/room';
export default {
    props: {
        title: String,
        default: 'title'
    },
    data() {
        return {
            visible: false,
            roomTypeList: [], // 客房类型
            fileList: [],
            dialogImageUrl: '',
            dialogVisible: false,
            item: {
                rtypeId: '',
                roomPrice: '',
                state: '',
                roomNumber: '',
                roomImg:'',
                floor: '',
                area: '',
                window: '',
                propleNum: '',
                facilities: '',
                remark: ''
            },
            
        };
    },
    mounted() {
        this.getAllRoomTypeList();
    },
    methods: {
        open(item) {
            // item就是roomData
            console.log('查看详情的item', item);
            this.visible = true;
            if (item === null || item === undefined) {
                this.item = {};
            } else {

                this.getroomById(item.roomId);
            }
        },
        getAllRoomTypeList() {
            getAllRoomTypeList().then((res) => {
                if (res.data.code === 2000) {
                    this.roomTypeList = res.data.obj;
                    console.log(res.data);
                }
            });
        },
        getroomById(id) {
            getroomById(id).then((res) => {
                if (res.data.code === 2000) {
                    this.item = res.data.obj;
                    console.log(res.data);
                    let imgList = res.data.data.imgList;
                    this.fileList = imgList.map((item) => ({ url: item }));
                }
            });
        },
        formateDate(date) {
            let theDate = new Date(date);
            let year = theDate.getFullYear();
            let month = theDate.getMonth() + 1;
            let day = theDate.getDate();
            let hour = theDate.getHours();
            let minute = theDate.getMinutes();
            let second = theDate.getSeconds();
            return (
                year +
                '-' +
                this.formatTen(month) +
                '-' +
                this.formatTen(day) +
                ' ' +
                this.formatTen(hour) +
                ':' +
                this.formatTen(minute) +
                ':' +
                this.formatTen(second)
            );
        },
        formatTen(num) {
            return num > 9 ? num + '' : '0' + num;
        },
        //
        handleRemove(file, fileList) {
            console.log('handleRemove');
            console.log(file, fileList);
            this.$axios.post(`/FileUpload/upload`, file).then((res) => {
                this.url = res.obj;
                console.log(res);
            });
        },
        handlePictureCardPreview(file) {
            console.log('handlePictureCardPreview');
            console.log(file);
            this.dialogImageUrl = 'http://localhost:8080/hotel' + file.url;
            this.dialogVisible = true;
        },
        handleSuccess(response, file) {
            console.log('handleSuccess');
            console.log(response);
            console.log(file);
            file.url = 'http://localhost:8080/hotel/' + response.obj;
            this.item.roomImg=file.url;
        },

         handleRemove(roomImg) {
            console.log(roomImg)
            roomImg=roomImg.response.obj
            delImg(roomImg).then((res) => {
                if (res.data.code === 2000) {
                    this.roomTypeList = res.data.obj;
                    console.log(res.data);
                }
            });
        },

        handlePreview(file) {
            console.log(file);
        },
        save(roomForm) {
            // console.log("submitForm:")
            // console.log(this.item)
            this.$refs.roomForm.validate((valid) => {
                if (valid) {
                    this.$confirm('确认保存吗？', '是否保存', {
                        cancelButtonText: '取消',
                        confirmButtonText: '确认',
                        lockScroll: false,
                        type: 'warning'
                    }).then(() => {
                        this.$emit('confirmData', this.item, this.fileList);
                        this.resetForm('roomForm');
                    });
                }
            });
        },
        resetForm(roomForm) {
            this.$nextTick(() => {
                this.$refs.roomForm.clearValidate();
            });
            this.item = {};
            this.fileFlag = false;
            this.fileUploadPercent = 0;
            this.visible = false;
        }
    }
};
</script>

<style lang="scss">
</style>
