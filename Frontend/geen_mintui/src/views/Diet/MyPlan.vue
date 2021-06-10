<template>
    <div class="about">
        <h1>This is an my plan</h1>

        <v-simple-table>
            <template v-slot:default>
            <thead>
                <tr>
                <th class="text-left">
                    id
                </th>
                <th class="text-left">
                    foodName
                </th>
                <th class="text-left">
                    amount
                </th>
                <th class="text-left">
                    date
                </th>
                <th class="text-left">
                    option
                </th>
                </tr>
            </thead>
            <tbody>
                <tr
                v-for="item in testlist"
                :key="item.id"
                >
                <td>{{ item.id }}</td>
                <td>{{ item.foodName }}</td>
                <td>{{ item.amount }}</td>
                <td>{{ dateFormat(item.date).slice(0,10) }}</td>
                <td><v-btn
                    color="grey darken-1"
                    class="d-flex"
                    @click="deletePlan(item.id)"
                ><v-icon>mdi-delete-circle</v-icon></v-btn>
                </td>
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
            accountName:'',
        },
        testlist: [{}]
    }),
    mounted() {
        this.user.accountName=JSON.parse(sessionStorage.getItem('accountName'));
        this.$axios.get(
            'http://124.70.23.6:8080/api/v1/dietPlan',
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
    },
    methods: {
        deletePlan(id){
            
            console.log(id);
            let url='http://124.70.23.6:8080/api/v1/deleteDietPlan/'+id;
            this.$axios.get(url)
            .then(res=>{
                console.log('res=>',res.data);
                if(res.data){
                console.log('删除成功');
                this.show=true;
                this.$router.push("/training/myPlan");
                }
                else{
                console.log('删除失败');
                }
            })
        },
        dateFormat:function(time) {
            var date=new Date(time);
            var year=date.getFullYear();
            /* 在日期格式中，月份是从0开始的，因此要加0
            * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
            * */
            var month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
            var day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
            var hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
            var minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
            var seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
            // 拼接
            return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
        }
    }
}
</script>

