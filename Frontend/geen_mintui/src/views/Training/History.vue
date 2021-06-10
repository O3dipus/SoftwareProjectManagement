<template>
    <div class="about pa-2">
        <h1>My traning history</h1>

        <v-row>
            <v-col cols="8">
                <v-select outlined label="请要查询的锻炼部位" :items="categoryList" v-model="category"></v-select>
            </v-col>
            <v-col>
                <v-btn
                color="grey"
                class="mr-4"
                @click="check"
                >
                查询训练记录
                </v-btn>
            </v-col>
        </v-row>

        <v-simple-table v-model="show">
            <template v-slot:default>
            <thead>
                <tr>
                <th class="text-left">
                    id
                </th>
                <th class="text-left">
                    category
                </th>
                <th class="text-left">
                    capacity
                </th>
                <th class="text-left">
                    date
                </th>
                <th class="text-left">
                    Option
                </th>
                </tr>
            </thead>
            <tbody>
                <tr
                v-for="item in testlist"
                :key="item.id"
                >
                <td>{{ item.id }}</td>
                <td>{{ item.category }}</td>
                <td>{{ item.capacity }}</td>
                <td>{{ dateFormat(item.date).slice(0,10) }}</td>
                <td><v-btn
                    color="grey darken-1"
                    class="d-flex"
                    @click="deleteHistory(item.id)"
                ><v-icon>mdi-delete-circle</v-icon></v-btn>
                </td>
                </tr>
            </tbody>
            </template>
        </v-simple-table>

        <!--弹出的对话框-->
        <v-dialog max-width="500" v-model="showDialog" persistent>
            <v-card>
                <!--对话框的标题-->
                <v-toolbar dense dark color="#424242">
                    <v-toolbar-title>删除成功</v-toolbar-title>
                </v-toolbar>
                <!--对话框的内容，表单-->
                <v-card-text class="px-5">
                    训练记录删除成功
                </v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="#424242"
                        text
                        @click="showDialog=false"
                    >
                        返回
                    </v-btn>
                    </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>

export default {
    data: () => ({
        accountName:JSON.parse(sessionStorage.getItem('accountName')),
        show: false,
        valid: true,
        router:{
            trainingHome:'/training/home',
        },
        testlist: [{}],
        showDialog: false,
        category:'',
        categoryList:['胸','背','腿','肩','三头','二头','腹'],
    }),
    methods: {
        check(){
            this.show=true;
            this.$axios.get(
            'http://124.70.23.6:8080/api/v1/trainingRecord',
            {
            params: {
                accountName: this.accountName,
                category: this.category,
            }
            }).then(res=>{
            this.testlist=res.data;
            console.log(this.testlist);

            if(res.data){
                console.log('成功');
            }
            else{
                console.log('失败');
            }
            })
            .catch(error =>console.log(error.data));
        },
        deleteHistory(id){
            console.log(id);
            let url='http://124.70.23.6:8080/api/v1/deleteTrainingRecord/'+id;
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

