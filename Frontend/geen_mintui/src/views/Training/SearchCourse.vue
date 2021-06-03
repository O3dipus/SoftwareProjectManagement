<template>
    <div class="pa-6">
        <v-row>
            <v-col cols=10>
                <v-text-field
                label="请输入锻炼部位或"
                :rules="rules"
                hide-details="auto"
                v-model="param"
                ></v-text-field>
            </v-col>
            <v-col cols=2>
                <v-btn
                elevation="2"
                @click="submmit">submmit</v-btn>
            </v-col>
        </v-row>

        <v-row dense v-show="show">
        <v-col
            v-for="item in returnlists"
            :key="item.id"
            :cols=12
            >
            <v-card>
                <v-card-title 
                v-text="item.name"
                class="pa-5"
                style="font-size:30px">124</v-card-title>
                <v-card-subtitle style="font-size:2em">{{item.category}}</v-card-subtitle>
                <v-card-text>{{item.content}}</v-card-text>

                <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn 
                    icon
                    @click="viewComment(item.id)">
                    <v-icon>mdi-comment-text</v-icon>
                </v-btn>

                <v-btn icon>
                    <v-icon>mdi-playlist-plus</v-icon>
                </v-btn>

                </v-card-actions>
            </v-card>
            </v-col>
        </v-row>
    </div>
</template>

<script>
export default {
    data: () => ({
        rules: [
            value => !!value || 'Required.',
            value => (value && value.length <= 10) || 'Max is 10 characters',
        ],
        param:'',
        returnlists: [{}],
        show:false,
    }),
    methods: {
        viewComment(id){
            console.log(id);
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/course/comment',
                {
                params: {
                    courseId: id,
                    page:'1',
                }
            }).then(res=>{
            console.log(res.data);
            })
            .catch(error =>console.log(error.data));
        },
        submmit(){
            console.log(this.param);
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/course/search',
                {
                params: {
                    param: this.param,
                }
            }).then(res=>{
            this.returnlists=res.data;
            this.show=true;
            console.log(this.returnlists);

            if(res.data){
                console.log('成功');
            }
            else{
                console.log('失败');
            }
            })
            .catch(error =>console.log(error.data));
        }
    }
}
</script>