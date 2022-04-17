<template>
  <div class="header-top">
        <div class="header-top-main">
            <div class="header-top-main-left">
                <ul>
                    <li><router-link to="">首页</router-link></li>
                    <li><router-link to="/roomlist">房间预订</router-link></li>  
                </ul>
            </div>
            <div class="header-top-main-right">
                <ul>
                    <li><router-link to="/userinfo">个人信息</router-link></li>
                    <li><router-link to="/engage">预订订单</router-link></li>
                    <li><router-link to="/order">我的订单</router-link></li>
                    <li><router-link to="/historyorder">历史订单</router-link></li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script scoped>
export default {

}
</script>

<style scoped>
a{
  color: #AFAFAF;  
}
.header-top{
    width: 100%;
    height: 36px;
    background: #2E2828;
}
.header-top-main{
    width: 1200px;
    height: 36px;
    display: flex;
    margin: auto;
}
.header-top-main-left,.header-top-main-right{
    width: 50%;
    height: 36px;
}
.header-top-main-left ul,.header-top-main-right ul{
    display: flex;
    line-height: 36px;
}
.header-top-main-left ul li,.header-top-main-right ul li{
    font-size: 12px;
    margin-right: 10px;
    color: #AFAFAF;
}
.header-top-main-right ul{
    justify-content:flex-end;
}
</style>