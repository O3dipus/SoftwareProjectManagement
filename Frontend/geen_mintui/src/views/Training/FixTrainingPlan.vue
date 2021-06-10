<template>
<div class='fixPlan'>
    <v-card plain>
        <v-card-title>
            Fix Training Plan
        </v-card-title>
        <v-card-subtitle>
            Fix Your Own Training Plan
        </v-card-subtitle>
        <v-form>
            <v-container>
                <v-row no-gutters>
                    <v-col cols="8">
                        <v-select outlined label="Movement" :items="movement" v-model="select"></v-select>
                    </v-col>
                    <v-col cols="5">
                        <v-text-field label="Group Number" v-model="group_number" outlined>
                        </v-text-field>
                    </v-col>
                    <v-col cols="6" offset="1">
                        <v-slider
                            v-model="group_number"
                            step="1"
                            max=20
                            min=0
                            thumb-label
                            dense
                        ></v-slider>
                    </v-col>
                    <v-col cols="5">
                        <v-text-field label="Times Each Group" v-model="times_each_group" outlined>
                        </v-text-field>
                    </v-col>
                    <v-col cols="6" offset="1">
                        <v-slider
                            v-model="times_each_group"
                            step="1"
                            max=100
                            min=0
                            thumb-label
                        ></v-slider>
                    </v-col>
                    <v-col cols="5">
                        <v-text-field label="Weight Each Group" v-model="weight_each_group" outlined>
                        </v-text-field>
                    </v-col>
                    <v-col cols="6" offset="1">
                        <v-slider
                            v-model="weight_each_group"
                            step="1"
                            max=100
                            min=0
                            thumb-label
                            dense
                        ></v-slider>
                    </v-col>
                </v-row>
            </v-container>
            <v-btn bottom block @click="submmit">Submmit</v-btn>
        </v-form>
    </v-card>
    <!--弹出的对话框-->
        <v-dialog max-width="500" v-model="show" persistent>
            <v-card>
                <!--对话框的标题-->
                <v-toolbar dense dark color="#424242">
                    <v-toolbar-title>提示</v-toolbar-title>
                </v-toolbar>
                <!--对话框的内容，表单-->
                <v-card-text class="px-5">
                    训练计划创建成功
                </v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="#424242"
                        text
                        router :to="router.trainingHome"
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
    name: 'FixTrainingPlan',

    components: {
    },
    mounted() {
        //this.email=JSON.parse(sessionStorage.getItem('accountName'));
        this.movement=JSON.parse(sessionStorage.getItem('namelist'));
        //console.log(this.movement);
    },
    data: () => ({
        router:{
            trainingHome:'/training/home',
        },
        show:false,
        test:[],
        movement:[],
        group_number:0,
        weight_each_group:0,
        times_each_group:0,
        select:'',
    }),
    methods:{
        submmit(){
            //console.log(this.select);
            //console.log(this.group_number);
            
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/createTrainingPlan',
                {
                params: {
                    accountName: JSON.parse(sessionStorage.getItem('accountName')),
                    workoutName: this.select,
                    groupNum: this.group_number,
                    times: this.weight_each_group,
                    weight: this.times_each_group,
                }
            })
            .then(res=>{
                console.log('res=>',res.data);
                if(res.data){
                console.log('创建成功');
                this.show=true;
                }
                else{
                console.log('创建失败');
                }
            })
            .catch(error =>console.log(error.data));
        }
    }
}
</script>