<template>
  <div class="page-component">
    <el-pagination
      background
      layout="total, sizes,prev, pager, next,jumper"
      :total="total"
      :current-page="page.currentPage"
      :page-size.sync="page.pageSize"
      :page-sizes="[9, 18, 24]"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>

</template>
<script>
export default {
  props: {
    total: {
      required: true,
      type: Number
    },
    page: {
      required: true,
      type: Object
    }
  },
  methods: {
    handleSizeChange (val) {
      this.page.pageSize = val
      this.$emit('pageChange', this.page)
    },
    handleCurrentChange (val) {
      this.page.currentPage = val
      this.$emit('pageChange', this.page)
    }
  }
}
</script>
<style scoped>
.el-form {
  background-color: #fff;
}
.el-pagination{padding:.5rem 0!important}
.page-component{
  text-align:center
}
</style>

