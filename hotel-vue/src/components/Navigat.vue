<template>
<div class="header">
    
    <header> 
      <div class="header">
          <ul>
              <li v-for="item,index in hotel" :key="index" @click="toptype(index)" @mouseleave="out(index)" @mouseenter="movein(index)" ref="ptype">
                      {{item}}
              </li>
          </ul>
          <!-- <input v-model="name"/> -->
      </div>
  </header>
</div>
  
</template>

<script>

export default {
    name:'Header',
    data(){
        return{
            name:'',
            hotel:['酒店预订','酒店详情','交通位置','公告栏']
        }
    },
    methods:{
        toptype(index){
             return this.$router.push({
                path:'/phone',
                query:{
                    page:index
                }
            })
        },
        movein(index){
            for(let i=0;i<this.$refs.ptype[index].parentElement.children.length;i++){
                this.$refs.ptype[i].style.border='none'
            }
            this.$refs.ptype[index].style.borderBottom='2px solid red'
        },
        out(index){
             for(let i=0;i<this.$refs.ptype[index].parentElement.children.length;i++){
                this.$refs.ptype[i].style.border='none'
            }
        }
    },
    created(){
        this.pname=this.$getSessionStorage('phonetype')
    }
}
</script>

<style scoped>
#app{
    margin: 0px;
}
*{
    margin: 0px;
    padding: 0px;
    list-style: none;
}
a{
    text-decoration: none;
}
.header{
    width: 100%;
    background-color: #ffffff;
}

header{
    width: 1200px;
    height: 74px;
    margin: auto;
    
    display: flex;
    border-bottom: 1px solid #f0f0f0;
}
.header{
    width: 100%;
    height: 74px;
    flex-direction: column;
}
.header ul li{
    float: left;
    margin-top: 28px;
    height: 25px;
    margin-right: 50px;
    /* border-bottom: ; */
    
}
.header input{
    margin-left: 100px;
    padding-left: 10px;
    margin-top: 20px;
    width: 250px;
    height: 30px;
    border-radius: 50px;
    float: right;
    border: 1px solid #8c8c8c;
    background-color: #f0f0f0;
    outline: none;
}

</style>