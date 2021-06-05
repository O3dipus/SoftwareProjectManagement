<template>
    <div class="about">
        <h1>This is an my plan</h1>
        <v-btn
            elevation="2"
            @click="test"
            class="d-flex justify-center text-center mx-auto align-center"
        >查询</v-btn>

        <v-simple-table>
            <template v-slot:default>
            <thead>
                <tr>
                <th class="text-left">
                    id
                </th>
                <th class="text-left">
                    workoutName
                </th>
                <th class="text-left">
                    date
                </th>
                <th class="text-left">
                    groupNum
                </th>
                <th class="text-left">
                    times
                </th>
                <th class="text-left">
                    weight
                </th>
                </tr>
            </thead>
            <tbody>
                <tr
                v-for="item in testlist"
                :key="item.id"
                >
                <td>{{ item.id }}</td>
                <td>{{ item.workoutName }}</td>
                <td>{{ item.date }}</td>
                <td>{{ item.groupNum }}</td>
                <td>{{ item.times }}</td>
                <td>{{ item.weight }}</td>
                </tr>
            </tbody>
            </template>
        </v-simple-table>
    </div>
</template>

<script>

export default {
    data: () => ({
    user: {
        accountName:'761447951@qq.com',
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

