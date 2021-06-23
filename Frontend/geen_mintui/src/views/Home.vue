<template>
  <hello-world />
</template>

<script>
  import HelloWorld from '../components/HelloWorld'

  export default {
    name: 'Home',
    data:() =>({
        returnlists:[],
        namelist: [],
    }),

    components: {
      HelloWorld,
    },
    mounted() {
        this.$axios.get(
            'http://124.70.23.6:8080/api/v1/course/search',
            {
            params: {
                param: '',
            }
        }).then(res=>{
        this.returnlists=res.data;
        for(var i in this.returnlists){
            this.namelist.push(this.returnlists[i].name);
        }
        sessionStorage.setItem('namelist',JSON.stringify(this.namelist));
        if(res.data){
            console.log('课程查询成功');
            console.log("动作:"+JSON.parse(sessionStorage.getItem('namelist')));
        }
        else{
            console.log('课程查询失败');
        }
        })
        .catch(error =>console.log(error.data));
    },
  }
</script>
