<template>
    <div class="about pa-3">
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
                    delte
                </th>
                <th class="text-left">
                    modify
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
                <td>{{ item.amount +"g"}}</td>
                <td>{{ dateFormat(item.date).slice(0,10) }}</td>
                <td><v-btn
                    color="grey darken-1"
                    class="d-flex"
                    @click="deletePlan(item.id)"
                ><v-icon>mdi-delete-circle</v-icon></v-btn>
                <td><v-btn
                    color="grey darken-1"
                    class="d-flex"
                    @click="showModifyDietPlan(item.id)"
                ><v-icon>mdi-clipboard-edit</v-icon></v-btn>
                </td>
                </tr>
            </tbody>
            </template>
        </v-simple-table>

        <v-row>
            <v-col cols="12">
                <h1>营养成分查询</h1>
            </v-col>
            
            <v-col cols="8">
                <v-select outlined label="日期" :items="datelist" v-model="selectDate"></v-select>
            </v-col>
            <v-col cols="4">
                <v-btn
                color="grey"
                class="mr-4"
                @click="checkNutrition"
                >
                查询营养成分
                </v-btn>
            </v-col>
            <v-col>
                <v-simple-table>
                    <template v-slot:default>
                    <thead>
                        <tr>
                        <th class="text-left">
                            carbohydrate
                        </th>
                        <th class="text-left">
                            fat
                        </th>
                        <th class="text-left">
                            protein
                        </th>
                        <th class="text-left">
                            vitamin
                        </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        <td>{{ nutritionlist.carbohydrate}}</td>
                        <td>{{ nutritionlist.fat }}</td>
                        <td>{{ nutritionlist.protein }}</td>
                        <td>{{ nutritionlist.vitamin }}</td>
                        </tr>
                    </tbody>
                    </template>
                </v-simple-table>
            </v-col>
        </v-row>

        <v-dialog
            v-model="showModifyDialog"
            >
            <v-card>
                <v-card-title class="text-h5">
                更改饮食计划
                </v-card-title>

                <v-card-text>
                    <v-card
                        class="mx-auto"
                        outlined>
                        <v-list-item three-line>
                        <v-list-item-content>
                            <td>
                            <v-text-field
                            label="请输入食物名称"
                            hide-details="auto"
                            v-model="modifyDietPlan.foodName"
                            ></v-text-field>
                            </td>
                            <td>
                            <v-text-field
                            label="请输入数量(单位：g)"
                            hide-details="auto"
                            v-model="modifyDietPlan.amount"
                            ></v-text-field>
                            </td>
                        </v-list-item-content>
                        </v-list-item>
                    </v-card>
                </v-card-text>

                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="green darken-1"
                    text
                    @click="modifyTheDietPlan"
                >
                    更改
                </v-btn>
                <v-btn
                    color="green darken-1"
                    text
                    @click="showModifyDialog = false"
                >
                    返回
                </v-btn>
                </v-card-actions>
            </v-card>
            </v-dialog>

            
            <v-alert
            v-model="alert"
            dismissible
            color="teal--#424242"
            border="left"
            elevation="2"
            colored-border
            >
            {{this.alertMessgae}}
            </v-alert>
    </div>
</template>

<script>

export default {
    data: () => ({
        user: {
            accountName:'',
        },
        testlist: [{}],
        nutritionlist:'',
        datelist:[],
        selectDate:'',
        showModifyDialog:false,
        modifyDietPlan:{
            id:'',
            foodName:'',
            amount:'',
        },
        alert:false,
        alertMessgae:'',
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
        
        for(var i in this.testlist){
            //console.log(this.returnlists[i].name);
            this.datelist.push(this.dateFormat(this.testlist[i].date).slice(0,10));
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
        checkNutrition(){
            this.$axios.get(
            'http://124.70.23.6:8080/api/v1/dietPlan/nutrition',
            {
            params: {
                accountName:JSON.parse(sessionStorage.getItem('accountName')),
                time:this.selectDate,
            }
            }).then(res=>{
            this.nutritionlist=res.data;
            console.log(this.nutritionlist)
            if(res.data.carbohydrate != 0){
                console.log('查询成功');
            }
            else{
                console.log('失败');
                this.alertMessgae="查询失败，请选择日期";
                this.alert=true;
            }
            })
            .catch(error =>console.log(error.data));
        },
        showModifyDietPlan(id){
            console.log(id);
            this.modifyDietPlan.id=id;
            this.showModifyDialog=true;
        },
        modifyTheDietPlan(){
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/changeDietPlan',
                {
                params: {
                    id: this.modifyDietPlan.id,
                    foodName: this.modifyDietPlan.foodName,
                    amount: this.modifyDietPlan.amount,
                }
            })
            .then(res=>{
                console.log('res=>',res.data);
                if(res.data){
                console.log('更改成功');
                this.showModifyDialog=false;
                this.alertMessgae="更改成功";
                this.alert=true;
                location.reload();
                }
                else{
                console.log('更改失败');
                this.showModifyDialog=false;
                this.alertMessgae="更改失败";
                this.alert=true;
                location.reload();
                }
            })
            .catch(error =>console.log(error.data));
        },
        deletePlan(id){
            console.log(id);
            let url='http://124.70.23.6:8080/api/v1/deleteDietPlan/'+id;
            this.$axios.get(url)
            .then(res=>{
                console.log('res=>',res.data);
                if(res.data){
                console.log('删除成功');
                this.show=true;
                this.$router.push("/diet/home");
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

