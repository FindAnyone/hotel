<template>
  <div id="users">
    <el-tabs type="card" v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="用户管理" name="allUser" @click='queryUserList'>
        <childUser v-if="isUser" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import myUser from './users'
import myPosition from './position'
export default {
  components: {
    childUser: myUser,
    childPosition: myPosition
  },
  data () {
    return {
      activeName: 'allUser',
      isUser: true,
      isPosition: false
    }
  },
  methods: {
    handleClick (tab) {
      if (tab.name === 'allUser') {
        this.isUser = true
        this.isPosition = false
      } else if (tab.name === 'allPosition') {
        this.isPosition = true
        this.isUser = false
      }
    },

    queryUserList(){
      this.$axios.post('user/queryUserList',{
                pageNumber:this.pageNumber,
                pageSize:this.pageSize,
                username:this.condition.username,
                sex:this.condition.sex
            })
            .then(res=>{
                console.log(res)
                this.tableData=res.data.object.list
                this.total=res.data.object.total
            })
            .catch(error=>{
                console.log(error)
            })
    }
  }
}
</script>
