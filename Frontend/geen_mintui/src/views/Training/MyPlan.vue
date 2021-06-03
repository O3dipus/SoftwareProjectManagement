<template>
    <div class="about">
        <h1>This is an my plan</h1>

        <v-text-field
            v-model="user.accountName"
            label="accountName"
            required
            ></v-text-field>
        <v-btn
            elevation="2"
            @click="test"
        >fuck me{{this.testlist[0].id}}</v-btn>
    </div>
</template>

<script>

export default {
    data: () => ({
    user: {
        accountName:'',
    },
    returnList: [{
        id:[],
        accountName:[],
        date:[],
        groupNum:[],
        times:[],
        weight:[],
        workoutName:[],
    }],
    testlist: [{}]
    }),
    methods: {
    test(){
        this.$axios.get(
            'http://124.70.23.6:8080/api/v1/trainingPlan',
            {
            params: {
                accountName: this.user.accountName,
            }
        }).then(res=>{
        this.testlist=res.data;
        for(let i in this.testlist){
            console.log(this.testlist[i].id);
        }
        console.log(this.testlist);
        console.log('我是傻逼');
        console.log(this.testlist[1]);
        console.log('我是傻逼2');
        console.log('res[1].id=>',this.testlist[1].id);

        if(res.data){
            console.log('成功');
        }
        else{
            console.log('失败');
        }
        })
        .catch(error =>console.log(error.data));
        }
    }
}
</script>

