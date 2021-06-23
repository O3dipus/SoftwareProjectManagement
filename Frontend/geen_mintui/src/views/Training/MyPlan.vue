<template>
    <div class="about pa-2">
        <h1>My traning plan</h1>

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
                <th class="text-left">
                    delete
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
                <td>{{ item.workoutName }}</td>
                <td>{{ dateFormat(item.date).slice(0,10) }}</td>
                <td>{{ item.groupNum }}</td>
                <td>{{ item.times }}</td>
                <td>{{ item.weight }}</td>
                <td><v-btn
                    color="grey darken-1"
                    class="d-flex"
                    @click="deletePlan(item.id)"
                ><v-icon>mdi-delete-circle</v-icon></v-btn>
                </td>
                <td><v-btn
                    color="grey darken-1"
                    class="d-flex"
                    @click="modifyPlan(item.id)"
                ><v-icon>mdi-clipboard-edit</v-icon></v-btn>
                </td>
                </tr>
            </tbody>
            </template>
        </v-simple-table>

        <!--弹出的对话框-->
        <v-dialog max-width="500" v-model="show" persistent>
            <v-card>
                <!--对话框的标题-->
                <v-toolbar dense dark color="#424242">
                    <v-toolbar-title>删除成功</v-toolbar-title>
                </v-toolbar>
                <!--对话框的内容，表单-->
                <v-card-text class="px-5">
                    训练计划删除成功
                </v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="#424242"
                        text
                        @click="show=false"
                    >
                        返回
                    </v-btn>
                    </v-card-actions>
            </v-card>
        </v-dialog>

        <v-row class="pa-6">
            <v-col align="center"
            justify="center"
            col=6>
                <v-btn
                :disabled="!valid"
                color="grey"
                class="mr-4"
                @click="addToHistory"
                >
                添加到训练记录
                </v-btn>
            </v-col>

            <v-col 
            align="center"
            justify="center"
            col=6>
                <v-btn
                color="grey"
                class="mr-4"
                router :to="router.trainingHome"
                >
                返回主页
                </v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="12">
                <h1>获取训练容量</h1>
            </v-col>
            
            <v-col cols="8">
                <v-select outlined label="日期" :items="datelist" v-model="selectDate"></v-select>
            </v-col>
            <v-col cols="4">
                <v-btn
                color="grey"
                class="mr-4"
                @click="checkCapacity"
                >
                查询训练容量
                </v-btn>
            </v-col>
            <v-col cols="8">
                <v-text-field
                v-model="capacity"
                label="当日训练容量"
                solo
                readonly
                ></v-text-field>
            </v-col>
            <v-col cols="4">
                <v-btn
                color="grey"
                class="mr-4"
                @click="showDialog = true"
                >
                添加到训练记录
                </v-btn>
            </v-col>

            <v-dialog
            v-model="showDialog"
            >
            <v-card>
                <v-card-title class="text-h5">
                添加至我的训练记录
                </v-card-title>

                <v-card-text>
                    <v-card
                        class="mx-auto"
                        outlined>
                        <v-list-item three-line>
                        <v-list-item-content>
                            <v-select outlined label="请选择锻炼部位" :items="categoryList" v-model="category"></v-select>
                        </v-list-item-content>
                        </v-list-item>
                    </v-card>
                </v-card-text>

                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="green darken-1"
                    text
                    @click="addToHistory"
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
        </v-row>

        <v-dialog
            v-model="showModifyPlan"
            >
            <v-card>
                <v-card-title class="text-h5">
                更改训练计划
                </v-card-title>

                <v-card-text>
                    <v-card
                        class="mx-auto"
                        outlined>
                        <v-list-item three-line>
                        <v-list-item-content>
                            <td>
                            <v-select 
                            outlined 
                            label="请选择要更改的动作" 
                            :items="workoutNameList" 
                            v-model="modifyInfo.workoutName"
                            ></v-select>
                            </td>
                            <td>
                            <v-text-field
                            label="请输入组数"
                            hide-details="auto"
                            v-model="modifyInfo.groupNum"
                            ></v-text-field>
                            </td>
                            <td>
                            <v-text-field
                            label="请输入次数"
                            hide-details="auto"
                            v-model="modifyInfo.times"
                            ></v-text-field>
                            </td>
                            <td>
                            <v-text-field
                            label="请输入负重（KG）"
                            hide-details="auto"
                            v-model="modifyInfo.weight"
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
                    @click="modifyThePlan"
                >
                    更改
                </v-btn>
                <v-btn
                    color="green darken-1"
                    text
                    @click="showModifyPlan = false"
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
            {{this.alertMessage}}
            </v-alert>
    </div>
</template>

<script>

export default {
    data: () => ({
        accountName:JSON.parse(sessionStorage.getItem('accountName')),
        workoutNameList:[],
        show: false,
        alert:false,
        alertMessage:'',
        showModifyPlan:false,
        valid: true,
        user: {
            accountName:'',
        },
        router:{
            trainingHome:'/training/home',
        },
        testlist: [{}],
        datelist:[],
        selectDate:'',
        capacity:'',
        showDialog: false,
        category:'',
        categoryList:['胸','背','腿','肩','三头','二头','腹'],
        modifyInfo:{
            id:'',
            workoutName:'',
            groupNum:'',
            times:'',
            weight:'',
        }
    }),
    mounted() {
        this.user.accountName=JSON.parse(sessionStorage.getItem('accountName'));
        this.workoutNameList=JSON.parse(sessionStorage.getItem('namelist'));
        this.$axios.get(
            'http://124.70.23.6:8080/api/v1/trainingPlan',
            {
            params: {
                accountName: this.user.accountName,
            }
        }).then(res=>{
        this.testlist=res.data;
        console.log(this.testlist);
        for(var i in this.testlist){
            //console.log(this.returnlists[i].name);
            this.datelist.push(this.dateFormat(this.testlist[i].date).slice(0,10));
        }
        console.log(this.datelist);

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
        modifyPlan(id){
            console.log(id);
            this.showModifyPlan=true;
            this.modifyInfo.id=id;
        },
        modifyThePlan(){
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/changeTrainingPlan',
                {
                params: {
                    id: this.modifyInfo.id,
                    workoutName: this.modifyInfo.workoutName,
                    groupNum: this.modifyInfo.groupNum,
                    times: this.modifyInfo.times,
                    weight: this.modifyInfo.weight,
                }
            })
            .then(res=>{
                console.log('res=>',res.data);
                if(res.data){
                console.log('更改成功');
                this.showModifyPlan=false;
                this.alertMessage='更改成功';
                this.alert=true;
                location.reload();
                }
                else{
                console.log('更改失败');
                this.showModifyPlan=false;
                this.alertMessage='更改失败';
                this.alert=true;
                }
            })
            .catch(error =>console.log(error.data));
        },
        deletePlan(id){
            console.log(id);
            let url='http://124.70.23.6:8080/api/v1/deleteTrainingPlan/'+id;
            this.$axios.get(url)
            .then(res=>{
                console.log('res=>',res.data);
                if(res.data){
                console.log('删除成功');
                this.showModifyPlan=false;
                this.alertMessage='删除成功';
                this.alert=true;
                location.reload();
                }
                else{
                console.log('删除失败');
                this.showModifyPlan=false;
                this.alertMessage='删除失败';
                this.alert=true;
                }
            })
        },
        addToHistory(){
            console.log(this.accountName,this.category,this.capacity,this.selectDate);
            this.$axios.get(
            'http://124.70.23.6:8080/api/v1/addTrainingRecord',
            {
            params: {
                accountName: this.accountName,
                category: this.category,
                capacity:this.capacity,
                time: this.selectDate,
            }
            })
            .then(res=>{
                console.log('res=>',res.data);
                this.capacity=res.data;
                if(res.data){
                console.log('成功');
                this.showDialog = false;
                }
                else{
                console.log('失败');
                }
            })
            .catch(error =>console.log(error.data));
        },
        checkCapacity(){
            console.log(this.selectDate);
            this.$axios.get(
            'http://124.70.23.6:8080/api/v1/trainingPlan/capacity',
            {
            params: {
                accountName: this.accountName,
                time: this.selectDate,
            }
            })
            .then(res=>{
                console.log('res=>',res.data);
                this.capacity=res.data;
                if(res.data){
                console.log('成功');
                }
                else{
                console.log('失败');
                }
            })
            .catch(error =>console.log(error.data));
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

