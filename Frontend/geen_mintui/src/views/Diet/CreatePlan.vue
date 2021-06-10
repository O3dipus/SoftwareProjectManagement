<template>
    <div class= "pa-3, ma-2">
        <v-form
            ref="form"
            v-model="valid"
            lazy-validation
        >

            <v-text-field
            v-model="foodName"
            :counter="10"
            :rules="foodNameRules"
            label="食物名称"
            required
            ></v-text-field>

            <v-text-field
            v-model="amount"
            :counter="10"
            :rules="amountRules"
            label="摄入数量"
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
                    饮食计划创建成功
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
        accountName:'',

        foodName: '',
        foodNameRules: [
        v => !!v || 'foodName is required',
        v => (v && v.length <= 10) || 'foodName must be less than 10 characters',
        ],

        amount: '',
        amountRules: [
        v => !!v || 'amount is required',
        v => (v && !isNaN(v) && v.length <= 10) || 'amount must be a number less than 10 characters',
        ],
    }),
    mounted() {
        this.accountName=JSON.parse(sessionStorage.getItem('accountName'));
    },
    methods: {
        validate () {
            this.$refs.form.validate();
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/createDietPlan',
                {
                params: {
                    accountName: this.accountName,
                    foodName: this.foodName,
                    amount: this.amount,
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
            this.$router.push("/diet/home");
        }
    },
}
</script>