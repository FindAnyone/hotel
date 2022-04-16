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
                    <el-input v-model="item.orderId" clearable readonly />
                </el-form-item>
                <el-form-item label="客房类型：">
                    <el-input v-model="item.roomTypeName" clearable readonly />
                </el-form-item>
                <el-form-item label="房号：">
                    <el-input v-model="item.roomNumber" clearable readonly />
                </el-form-item>
                <el-form-item label="押金：" >
                    <el-input v-model="item.cashPledge" clearable readonly />
                </el-form-item>
                <el-form-item 
                v-for="(domain, index) in item.domains" 
                :key="index"
                :label="'入住人信息' + (index + 1)"
                >
                    <el-input v-model="domain.checkName" clearable readonly></el-input>
                    <span>&nbsp;&nbsp;&nbsp;</span>
                    <el-input v-model="domain.checkIdCard" clearable readonly></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer">
                <el-button type="primary" @click="resetForm('roomForm')">返回</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import { getfindByOrderAndCheckIn } from '@/api/order';
export default {
    props: {
        title: String,
        default: 'title'
    },
    data() {
        return {
            visible: false,
            data:"",
            item: {
                orderId: '',
                roomNumber: '',
                roomTypeName: '',
                roomId:'',
                cashPledge:'',
                domains: []
            }
        };
    },
  
    methods: {
        open(item_son) {
            // item就是roomData
            console.log('查看详情的item', item_son);
            this.visible = true;
            if (item_son === null || item_son === undefined) {
                this.item = {};
            } else {
                
                 this.item.orderId=item_son.orderId
                 this.item.roomNumber=item_son.roomDetailet.roomNumber
                this.item.roomTypeName=item_son.roomType.roomTypeName
                const id=item_son.orderId
                getfindByOrderAndCheckIn({id:id}).then(res=>{
                    
                    this.item.cashPledge=res.data.obj.cashPledge
                    this.item.domains=res.data.obj.checkInList
                  
                })
            }
        },
         resetForm(roomForm) {
            // this.$nextTick(() => {
            //     this.$refs.roomForm.clearValidate();
            // });
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
