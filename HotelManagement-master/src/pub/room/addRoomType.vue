<template>
  <div id="roomtypeAdd">
    <el-dialog :title="title" :visible.sync="visible" top="2rem" :lock-scroll="false" :show-close="false" :close-on-click-modal="false">
      <el-form ref="roomtypeForm" :model="item" label-width="120px">
    
        <el-form-item label="客房类型名称：" prop="roomTypeName">
          <el-input v-model="item.roomTypeName" placeholder="请输入客房类型名称" clearable/>
        </el-form-item>
        <el-form-item label="简介：" prop="introduce">
          <el-input type="textarea" :rows="2" v-model="item.introduce" resize="none" maxlength="200" show-word-limit palceholder="房间设施说明"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button type="primary" @click="resetForm('roomtypeForm')">取消</el-button>
        <el-button type="primary" @click="submitForm('roomtypeForm')">提交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'
import { getRoomTypeById } from '@/api/roomtype'
import { uploadFile } from '@/api/uploadFile'
export default {
  props: {
    title: String,
    default: 'title'
  },
  data () {
    return {
      visible: false,
      bedTypeList: [],// 保存床型
      fileList: [],
      item: {
        roomTypeName: '',
        introduce: ''       
      }
    }
  },
  mounted() {
  
  },
  methods: {
    open (item) { // item就是roomTypeData
    // console.log('item',item)
      this.visible = true
      if (item === null || item === undefined) {
        this.item = {}
      } else {
        this.getRoomTypeById(item.typeId)
      }
    },
    getRoomTypeById(id) {
      // console.log('进入到根据ID查询客房类型')
      getRoomTypeById(id).then(res => {
        // console.log('根据ID查询客房类型返回数据是，', res)
        if(res.data.code === 2000){
          this.item = res.data.obj
          let imgList = res.data.data.imgList
          this.fileList = imgList.map(item => ({url:item}))
        }
      })
    },
    beforeUpload(file) {
      // console.log('file信息是',file)
      let files = new FormData();
      files.append('multipartFile',file)
      let headers = {'Content-Type': 'multipart/form-data'}
      uploadFile(files,headers).then((res) => {
        // console.log('文件上传返回数据',res.data.data)
        if (res.data.code === 0){
          let fileChild = {
            // name: file.name,
            url: res.data.data
          }
          this.fileList.push(fileChild)
        }
      })
      // console.log('this.fileList',this.fileList)
    },
    handleRemove(file, fileList) {
      // console.log('file', file);
      // console.log('fileList', fileList);
      this.fileList = this.fileList.filter((val) => {
        (val.url).indexOf(file.url) < 0
      })
    },
    handlePreview(file) {
      console.log(file);
    },
    submitForm (roomtypeForm) {
      this.$refs.roomtypeForm.validate(valid => {
        if (valid) {
          this.$confirm('确认保存吗？', '是否保存', {
            cancelButtonText: '取消',
            confirmButtonText: '确认',
            lockScroll: false,
            type: 'warning'
          }).then(() => {
            console.log('this.fileList', this.fileList)
            this.$emit('confirmData', this.item,this.fileList)
            this.resetForm('roomtypeForm')
          })
        }
      })
    },
    resetForm (roomtypeForm) {
      this.$nextTick(() => {
        this.$refs.roomtypeForm.clearValidate()
      })
      this.item = {}
      this.visible = false
    }
  }
}
</script>

<style lang="scss">

</style>
