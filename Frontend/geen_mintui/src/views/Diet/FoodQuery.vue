<template>
    <div class="about">
        <h1>Query for food</h1>

        <v-simple-table>
            <template v-slot:default>
            <thead>
                <tr>
                <th class="text-left">
                    name
                </th>
                <th class="text-left">
                    measurement
                </th>
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
                <th class="text-left">
                    Intake the food
                </th>
                </tr>
            </thead>
            <tbody>
                <tr
                v-for="item in foodList"
                :key="item.id"
                >
                <td>{{ item.name }}</td>
                <td>{{ item.measurement }}</td>
                <td>{{ item.carbohydrate }}</td>
                <td>{{ item.fat }}</td>
                <td>{{ item.protein }}</td>
                <td>{{ item.vitamin }}</td>
                <td><v-btn
                    color="grey darken-1"
                    class="d-flex"
                    @click="addToMyDietPlan(item.name)"
                ><v-icon>mdi-death-star</v-icon></v-btn>
                </td>
                </tr>
            </tbody>
            </template>
        </v-simple-table>

        <v-dialog
            v-model="showDialog"
            >
            <v-card>
                <v-card-title class="text-h5">
                添加至我的饮食计划
                </v-card-title>

                <v-card-text>
                    <v-card
                        class="mx-auto"
                        outlined>
                        <v-list-item three-line>
                        <v-list-item-content>
                            <td>
                            <v-text-field
                            label="请输入摄入量(单位：g)"
                            hide-details="auto"
                            v-model="addToDiet.intake"
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
                    @click="addToDietPlan"
                >
                    添加
                </v-btn>
                <v-btn
                    color="green darken-1"
                    text
                    @click="showDialog = false"
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
        foodName:'',
        foodList: [{}],
        showDialog:false,
        addToDiet:{
            intake:'',
            foodName:'',
        },
        accountName:'',
        alert:false,
        alertMessgae:'',
    }),
    mounted() {
        this.accountName=JSON.parse(sessionStorage.getItem('accountName'));
        this.$axios.get(
            'http://124.70.23.6:8080/api/v1/food')
        .then(res=>{
        this.foodList=res.data;

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
        addToMyDietPlan(name){
            this.showDialog=true;
            this.addToDiet.foodName=name;
            console.log(this.addToDiet.foodName);
        },
        addToDietPlan(){
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/createDietPlan',
                {
                params: {
                    accountName: this.accountName,
                    foodName: this.addToDiet.foodName,
                    amount: this.addToDiet.intake,
                }
            })
            .then(res=>{
                console.log('res=>',res.data);
                if(res.data){
                    console.log('创建成功');
                    this.showDialog=false;
                    this.alertMessgae="创建成功";
                    this.alert=true;
                }
                else{
                    console.log('创建失败');
                    this.showDialog=false;
                    this.alertMessgae="创建失败";
                    this.alert=true;
                }
            })
            .catch(error =>console.log(error.data));
        },
        test(){
        }
    }
}
</script>

