<template>
  <div id="orderDetail">
    <el-dialog :title="title" :visible.sync="visible" top="0.5rem" :lock-scroll="false" :show-close="false" :close-on-click-modal="false">
      <el-form ref="orderForm" :model="item" :rules="rules" label-width="100px" :disabled='true'>

        <el-form-item label="订单编号:" prop="orderDetailetId">
          <el-input v-model="item.orderDetailetId" clearable/>
        </el-form-item>

        <el-form-item label="房间类别:" prop="roomTypeId">
          <el-input v-model="item.roomTypeId" clearable/>
        </el-form-item>
        <el-form-item label="房间编号:" prop="roomId">
          <el-input v-model="item.roomId" clearable/>
        </el-form-item>
        <el-form-item label="用户编号:" prop="userID">
          <el-input v-model="item.userID" clearable/>
        </el-form-item>

        <el-form-item label="员工编号:" prop="empId">
          <el-input v-model="item.empId" clearable/>
        </el-form-item>

        <el-form-item label="入住人数:" prop="quantity">
          <el-input v-model="item.quantity" clearable/>
        </el-form-item>

        <el-form-item label="总价格:" prop="total">
          <el-input v-model="item.total" clearable/>
        </el-form-item>

        <el-form-item label="入住时间:" prop="checkTime">
          <el-input v-model="item.checkTime" clearable/>
        </el-form-item>

        <el-form-item label="退房时间:" prop="outTime">
          <el-input v-model="item.outTime" clearable/>
        </el-form-item>

        <el-form-item label="押金:" prop="cashPledge">
          <el-input v-model="item.cashPledge" clearable/>
        </el-form-item>
        
        <el-form-item label="是否已支付:" prop="state">
          <el-input v-model="item.state" clearable/>
        </el-form-item>

      </el-form>
      <span slot="footer">
      <el-button type="primary" @click="resetForm('orderForm')">确认</el-button>
    </span>
    </el-dialog>
  </div>
</template>

<script>


import { 获取订单表方法名 } from '请求来源路径';
import { 获取订单详情表方法名 } from '请求来源路径';

export default {
  props: {
    title: String,
    default: 'title'
  },
  data () {
   
    return {
      visible: false,
      orderId: '',
      roomTypeId: '',
      roomId: '',
      userID: '',
      empId: '',


      uptoken: {
        key: ''
      },
      positionList: [], // 保存职位信息
      param: '', // 表单要提交的参数
      fileFlag: false,
      fileUploadPercent: 0,
      fileList: [],
      item: { 
        
        
        quantity: '',
        orderDetailetId: '',
        total: '',
        checkTime: '',
        outTime: '',
        cashPledge:'',
        state:'',
      },
      
    }
  },
  mounted () {

  },
  methods: {
      //初始化
    open (item) {
      this.visible = true
      if (item === null || item === undefined) {
        this.item = {}
      } else {
        //将父标签传入的item写入到表上
        this.item = item
        // console.log('this item 是，传入值：',this.item)
        //将请求的Order表数据写入
    //   roomTypeId: '',
    //   roomId: '',
    //   userID: '',
    //   empId: '',
    this.roomTypeId=item.roomTypeId,
    this.roomId=item.roomId,
    this.userID=item.userID,
    this.empId=item.empId,
    this.quantity=item.quantity,
    
        // 查询详细表
        this.getOrderDtailsById()
      }
    },
    getOrderDtailsById() {
      getOrderDtailsById(this.OrderDetailetId).then(res => {
        console.log('根据ID查询该表:',res)
        //查询详细信息
        if(res.data.code === 2000) {
          this.item = res.data.obj
          this.orderDetailetId = item.orderDetailetId
            this.orderId=item.orderId
            this.total=item.total
            this.checkTime=item.checkTime
            this.outTime=item.outTime
            this.cashPledge=item.cashPledge
            this.state=item.state

          // this.item.position = res.data.data.position.positionName
        }
      })
    },

    resetForm (orderForm) {
      this.$nextTick(() => {
        this.$refs.orderForm.clearValidate()
      })
      this.item = {}
      this.fileFlag = false;
      this.fileUploadPercent = 0
      this.visible = false
    }
  }
}
</script>
<style lang="less">
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  // position: relative;
  // overflow: hidden;
  // text-align: left;
  width: 28%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 80px;
  height: 80px;
  line-height: 80px;
  text-align: center;
}
.el-upload__tip {
  margin-top: 0;
}
.avatar {
  width: 80px;
  height: 80px;
  display: block;
}
</style>
