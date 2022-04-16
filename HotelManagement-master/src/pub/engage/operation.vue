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
                <el-form-item label="预定编号：" prop="engageId">
                    <el-input v-model="item.engageId" clearable />
                </el-form-item>
                <el-form-item label="客房类型：" prop="typeId">
                    <el-input v-model="item.roomTypeName" clearable />
                </el-form-item>
                <el-form-item label="分配房间：" prop="room">
                    <el-select v-model="item.floor" placeholder="请选择楼层" @change="roomnumber" clearable>
                        <el-option v-for="i in floorList" :key="i.floor" :label="i.floor+'楼'" :value="i.floor" > </el-option>
                    </el-select>
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    <el-select v-model="item.roomId" placeholder="请选择房号" clearable>
                        <el-option v-for="i in roomnumberList" :key="i.roomId" :label="i.roomNumber+'号房间'" :value="i.roomId" > </el-option>
                    </el-select>
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
import { fingdByTypeId ,findByMap} from '@/api/engage';

export default {
    props: {
        title: String,
        default: 'title'
    },
    data() {
        return {
            visible: false,
            floorList:[],
            roomnumberList:[],
            item: {
                engageId: '',
                roomTypeName: '',
                floor: '',
                roomId:'',
            }
        };
    },
    methods: {
        open(item) {
            // item就是roomData
            console.log('查看详情的item', item);
            this.visible = true;
            if (item === null || item === undefined) {
                this.item = {};
            } else {
                this.item = item;
                this.item.roomTypeName = item.roomType.roomTypeName;
                fingdByTypeId({
                  id:item.typeId
                }).then(res=>{
                  this.floorList=res.data.obj
                })
            }
        },
        roomnumber(){
          const param={
            typeId:this.item.typeId,
            floor:this.item.floor,
            checkTime:this.item.checkTime,
            outTime:this.item.outTime,
          }

          findByMap(param).then(res=>{
            console.log(res.data)
            this.roomnumberList=res.data.obj
          })
          // console.log(param)
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
            this.item = {};
            this.visible = false;
        }
    }
};
</script>

<style scoped>
.el-input{
  width: 215px;
}
</style>
