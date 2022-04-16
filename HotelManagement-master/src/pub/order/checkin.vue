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
            <el-form ref="roomForm" :model="item" label-width="110px">
                <el-form-item label="订单编号：">
                    <el-input v-model="item.orderId" clearable />
                </el-form-item>
                <el-form-item label="客房类型：">
                    <el-input v-model="item.roomTypeName" clearable />
                </el-form-item>
                <el-form-item label="房号：">
                    <el-input v-model="item.roomNumber" clearable />
                </el-form-item>
                <el-form-item label="支付押金：">
                    <el-input v-model="item.cashPledge" clearable />
                </el-form-item>
                <el-form-item
                    v-for="(domain, index) in item.domains"
                    :label="'入住人信息' + (index + 1)"
                    :key="domain.key"
                    :prop="'domains.' + index + '.checkName'"
                    :rules="{ required: true, message: '内容不能为空', trigger: 'blur' }">

                    <el-input v-model="domain.checkName" clearable placeholder="请输入姓名"></el-input>
                    <span>&nbsp;&nbsp;&nbsp;</span>
                    <el-input v-model="domain.checkIdCard" clearable placeholder="请输入身份证"></el-input>
                    <a class="remove-item" v-show="item.domains.length > 1" @click.prevent="removeDomain(domain)"
                        ><i class="el-icon-close"></i
                    ></a>
                </el-form-item>

                <el-form-item class="submit-btn">
                    <el-button @click="addDomain">新增一项</el-button>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button type="primary" @click="resetForm('roomForm')">取消</el-button>
                <el-button type="primary" @click="save('roomForm')">确认</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
export default {
    props: {
        title: String,
        default: 'title'
    },
    data() {
        return {
            visible: false,
            item: {
                orderId: '',
                roomNumber: '',
                roomTypeName: '',
                roomId:'',
                cashPledge:'',
                domains: [{
                        checkName: '',
                        checkIdCard: ''
                    }]
            }
        };
    },
    methods: {
        addDomain() {
            this.item.domains.push({
                checkName: '',
                checkIdCard: ''
            });
            // console.log(this.item)
        },
        removeDomain(item) {
            var index = this.item.domains.indexOf(item);
            if (index !== -1) {
                this.item.domains.splice(index, 1);
            }
        },

        open(item) {
            // item就是roomData
            // console.log('查看详情的item', item.checkTime);
            this.visible = true;
            if (item === null || item === undefined) {
                this.item = {};
            } else {
                this.item.orderId=item.orderId
                this.item.roomTypeName = item.roomType.roomTypeName;
                this.item.roomNumber = item.roomDetailet.roomNumber;
                this.item.roomId=item.roomId
            }
        },
        save(roomForm) {
            // console.log("submitForm:")
            console.log(this.item);
            this.$refs.roomForm.validate((valid) => {
                if (valid) {
                    this.$confirm('确认保存吗？', '是否保存', {
                        cancelButtonText: '取消',
                        confirmButtonText: '确认',
                        lockScroll: false,
                        type: 'warning'
                    }).then(() => {
                        this.$emit('confirmData', this.item);
                        this.resetForm('roomForm');
                    });
                }
            });
        },

        resetForm(roomForm) {
            this.$nextTick(() => {
                this.$refs.roomForm.clearValidate();
            });
            this.$router.go(0)
            this.visible = false;
        }
    }
};
</script>

<style scoped>
.el-input {
    width: 215px;
}
</style>
