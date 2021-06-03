<template>
    <div class= "pa-3, ma-2">
        <v-form
            ref="form"
            v-model="valid"
            lazy-validation
        >
            <v-text-field
            v-model="id"
            :rules="idRules"
            label="训练计划id"
            required
            ></v-text-field>

            <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="validate"
            >
            Validate
            </v-btn>

            <v-btn
            color="error"
            class="mr-4"
            @click="reset"
            >
            Reset Form
            </v-btn>
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

        id: '',
        idRules: [
        v => !!v || 'id is required',
        v => (v && v.length <= 10) || 'id must be less than 10 characters',
        ],
    }),

    methods: {
        validate () {
            this.$refs.form.validate();
            console.log(this.id);
            this.$axios.get(
                'http://124.70.23.6:8080//api/v1/deleteTrainingPlan',
                {
                params: {
                    id: this.id,
                }
            })
            .then(res=>{
                console.log('res=>',res.data);
                if(res.data){
                console.log('删除成功');
                this.show=true;
                }
                else{
                console.log('删除失败');
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