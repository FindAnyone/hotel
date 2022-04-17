<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',

}


</script>

<style>
em{
  font-style: normal;
}
li{
  cursor: pointer;
}
*{
  margin: 0px;
  padding: 0px;
  list-style: none;
}
body{
  background-color: #f0f0f0;
}
a{
  text-decoration: none;
}
</style>