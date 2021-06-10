<template>
    <div class= "pa-3, ma-2">
        <v-form
            ref="form"
            v-model="valid"
            lazy-validation
        >

            <v-text-field
            v-model="age"
            :counter="10"
            :rules="ageRules"
            label="年龄"
            required
            ></v-text-field>

            <v-text-field
            v-model="userName"
            :counter="10"
            :rules="userNameRules"
            label="用户昵称"
            required
            ></v-text-field>

            <v-text-field
            v-model="sex"
            :counter="10"
            :rules="sexRules"
            label="性别"
            required
            ></v-text-field>

            <v-text-field
            v-model="weight"
            :counter="10"
            :rules="weightRules"
            label="体重"
            required
            ></v-text-field>

            <v-text-field
            v-model="height"
            :counter="10"
            :rules="heightRules"
            label="身高"
            required
            ></v-text-field>

            <v-text-field
            v-model="armCircum"
            :counter="10"
            :rules="armCircumRules"
            label="臂围"
            required
            ></v-text-field>

            <v-text-field
            v-model="weight"
            :counter="10"
            :rules="weightRules"
            label="胸围"
            required
            ></v-text-field>

            <v-text-field
            v-model="hipCircum"
            :counter="10"
            :rules="hipCircumRules"
            label="臀围"
            required
            ></v-text-field>

            <v-text-field
            v-model="waistline"
            :counter="10"
            :rules="waistlineRules"
            label="腰围"
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
                    <v-toolbar-title>更改成功</v-toolbar-title>
                </v-toolbar>
                <!--对话框的内容，表单-->
                <v-card-text class="px-5">
                    训练计划更改成功
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

        age: '',
        ageRules: [
        v => !!v || 'age is required',
        v => (v && !isNaN(v) && v.length <= 3) || 'age must be less than 3 characters',
        ],

        userName: '',
        userNameRules: [
        v => !!v || 'userName is required',
        v => (v && v.length <= 10) || 'userName must be less than 10 characters',
        ],

        groupNum: '',
        groupNumRules: [
        v => !!v || 'groupNum is required',
        v => (v && !isNaN(v) && v.length <= 2) || 'groupNum must be a number less than 2 characters',
        ],

        sex: '',
        sexRules: [
        v => !!v || 'sex is required',
        v => (v && v.length <= 2) || 'sex must be a number less than 2 characters',
        ],

        weight: '',
        weightRules: [
        v => !!v || 'weight is required',
        v => (v && !isNaN(v) && v.length <= 4) || 'weight must be a number less than 4 characters',
        ],

        height: '',
        heightRules: [
        v => !!v || 'height is required',
        v => (v && !isNaN(v) && v.length <= 4) || 'height must be a number less than 4 characters',
        ],

        chestCircum: '',
        chestCircumRules: [
        v => !!v || 'chestCircum is required',
        v => (v && !isNaN(v) && v.length <= 4) || 'chestCircum must be a number less than 4 characters',
        ],

        armCircum: '',
        armCircumRules: [
        v => !!v || 'armCircum is required',
        v => (v && !isNaN(v) && v.length <= 4) || 'armCircum must be a number less than 4 characters',
        ],

        hipCircum: '',
        hipCircumRules: [
        v => !!v || 'hipCircum is required',
        v => (v && !isNaN(v) && v.length <= 4) || 'hipCircum must be a number less than 4 characters',
        ],

        waistline: '',
        waistlineRules: [
        v => !!v || 'waistline is required',
        v => (v && !isNaN(v) && v.length <= 4) || 'waistline must be a number less than 4 characters',
        ],
    }),
    mounted() {
        this.accountName=JSON.parse(sessionStorage.getItem('accountName'));
    },
    methods: {
        validate () {
            this.$refs.form.validate();
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/changeInfo',
                {
                params: {
                    accountName: this.accountName,
                    age: this.age,
                    userName: this.userName,
                    sex: this.sex,
                    weight: this.weight,
                    height: this.height,
                    chestCircum: this.chestCircum,
                    armCircum: this.armCircum,
                    hipCircum: this.hipCircum,
                    waistline: this.waistline,
                }
            })
            .then(res=>{
                console.log('res=>',res.data);
                if(res.data){
                console.log('更改成功');
                this.show=true;
                }
                else{
                console.log('更改失败');
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