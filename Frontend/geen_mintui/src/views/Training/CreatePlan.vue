<template>
    <div class= "pa-3, ma-2">
        <v-form
            ref="form"
            v-model="valid"
            lazy-validation
        >

            <v-text-field
            v-model="workoutName"
            :counter="10"
            :rules="workoutNameRules"
            label="动作名称"
            required
            ></v-text-field>

            <v-text-field
            v-model="groupNum"
            :counter="10"
            :rules="groupNumRules"
            label="组数"
            required
            ></v-text-field>

            <v-text-field
            v-model="times"
            :counter="10"
            :rules="timesRules"
            label="次数"
            required
            ></v-text-field>

            <v-text-field
            v-model="weight"
            :counter="10"
            :rules="weightRules"
            label="负重(KG)"
            required
            ></v-text-field>

            <v-row>
                <v-col align="center"
                justify="center"
                col=6>
                    <v-btn
                    :disabled="!valid"
                    color="success"
                    class="mr-4"
                    @click="validate"
                    >
                    Validate
                    </v-btn>
                </v-col>

                <v-col 
                align="center"
                justify="center"
                col=6>
                    <v-btn
                    color="error"
                    class="mr-4"
                    @click="reset"
                    >
                    Reset Form
                    </v-btn>
                </v-col>
            </v-row>
        </v-form>

        
        <!--弹出的对话框-->
        <v-dialog max-width="500" v-model="show" persistent>
            <v-card>
                <!--对话框的标题-->
                <v-toolbar dense dark color="#424242">
                    <v-toolbar-title>创建成功</v-toolbar-title>
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
                        @click="backToHome"
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
        valid: true,
        show: false,

        workoutName: '',
        workoutNameRules: [
        v => !!v || 'workoutName is required',
        v => (v && v.length <= 10) || 'workoutName must be less than 10 characters',
        ],

        groupNum: '',
        groupNumRules: [
        v => !!v || 'groupNum is required',
        v => (v && !isNaN(v) && v.length <= 2) || 'groupNum must be a number less than 2 characters',
        ],

        times: '',
        timesRules: [
        v => !!v || 'times is required',
        v => (v && !isNaN(v) && v.length <= 2) || 'times must be a number less than 2 characters',
        ],

        weight: '',
        weightRules: [
        v => !!v || 'weight is required',
        v => (v && !isNaN(v) && v.length <= 3) || 'weight must be a number less than 3 characters',
        ],
    }),
    mounted() {
        this.email=JSON.parse(sessionStorage.getItem('accountName'));
    },
    methods: {
        validate () {
            this.$refs.form.validate();
            console.log(this.email);
            console.log(this.workoutName);
            console.log(this.groupNum);
            console.log(this.times);
            console.log(this.weight);
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/createTrainingPlan',
                {
                params: {
                    accountName: this.email,
                    workoutName: this.workoutName,
                    groupNum: this.groupNum,
                    times: this.times,
                    weight: this.weight,
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
        },
        reset () {
            this.$refs.form.reset()
        },
        backToHome(){
            //创建成功后跳转到指定页面
            this.$router.push("/training/home");
        }
    },
}
</script>