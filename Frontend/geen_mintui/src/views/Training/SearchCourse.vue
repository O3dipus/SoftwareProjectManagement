<template>
    <div class="pa-6">
        <v-row>
            <v-col cols=10>
                <v-text-field
                label="请输入锻炼部位或课程名"
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
                    @click="IdentifyCurId1(item.id)">
                    <v-icon>mdi-comment-text</v-icon>
                </v-btn>

                <v-btn 
                icon
                @click="IdentifyCurId2(item.id)">
                    <v-icon>mdi-comment-plus</v-icon>
                </v-btn>

                <v-btn 
                icon
                @click="IdentifyCurId3(item.id,item.name)">
                    <v-icon>mdi-playlist-plus</v-icon>
                </v-btn>

                
                <v-btn 
                    icon
                    @click="IdentifyCurId4(item.name)">
                    <v-icon>mdi-play</v-icon>
                </v-btn>

                </v-card-actions>
            </v-card>

            </v-col>
            <v-col>

            <v-dialog
            v-model="showAddComment"
            >
            <v-card>
                <v-card-title class="text-h5">
                添加评论
                </v-card-title>

                <v-card-text>
                    <v-text-field
                    label="请输入评论"
                    hide-details="auto"
                    v-model="commentText"
                    ></v-text-field>
                </v-card-text>

                <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn
                    color="green darken-1"
                    text
                    @click="showAddComment = false"
                >
                    返回
                </v-btn>

                <v-btn
                    color="green darken-1"
                    text
                    @click="addComment"
                >
                    添加评论
                </v-btn>
                </v-card-actions>
            </v-card>
            </v-dialog>
            </v-col>

            <v-col>

            <v-dialog
            v-model="showViewComment"
            >
            <v-card>
                <v-card-title class="text-h5">
                评论
                </v-card-title>

                <v-card-text>
                    <v-card
                        class="mx-auto"
                        outlined
                        v-for="item in commentText"
                        :key="item.id">
                        <v-list-item three-line>
                        <v-list-item-content>
                            <div class="text-overline mb-4">
                            评论者：{{item.accountName}}
                            </div>
                            <v-list-item-title class="text-h5 mb-1">
                            {{item.content}}
                            </v-list-item-title>
                            <v-list-item-subtitle>时间：{{item.time}}</v-list-item-subtitle>
                        </v-list-item-content>
                        </v-list-item>
                    </v-card>
                </v-card-text>

                <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn
                    color="green darken-1"
                    text
                    @click="showViewComment = false"
                >
                    返回
                </v-btn>
                </v-card-actions>
            </v-card>
            </v-dialog>
            </v-col>

            <v-col>
            <v-dialog
            v-model="showToAddPlan"
            >
            <v-card>
                <v-card-title class="text-h5">
                添加至我的训练计划
                </v-card-title>

                <v-card-text>
                    <v-card
                        class="mx-auto"
                        outlined>
                        <v-list-item three-line>
                        <v-list-item-content>
                            <v-text-field
                            label="请输入组数"
                            hide-details="auto"
                            v-model="groupNum"
                            ></v-text-field>
                            <v-text-field
                            label="请输入次数"
                            hide-details="auto"
                            v-model="times"
                            ></v-text-field>
                            <v-text-field
                            label="请输入负重（KG）"
                            hide-details="auto"
                            v-model="weight"
                            ></v-text-field>
                        </v-list-item-content>
                        </v-list-item>
                    </v-card>
                </v-card-text>

                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="green darken-1"
                    text
                    @click="addToplan "
                >
                    创建
                </v-btn>
                <v-btn
                    color="green darken-1"
                    text
                    @click="showToAddPlan = false"
                >
                    返回
                </v-btn>
                </v-card-actions>
            </v-card>
            </v-dialog>
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
        showViewComment:false,
        showAddComment:false,
        showToAddPlan:false,
        commentText:[],
        currentCourseId:'',
        accountName:'',
        workoutName:'',
        groupNum:'',
        times:'',
        weight:'',
        namelist:[],
    }),
    mounted() {
        this.accountName=JSON.parse(sessionStorage.getItem('accountName'));
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
        for(var i in this.returnlists){
            this.namelist.push(this.returnlists[i].name);
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
        IdentifyCurId1(id){
            this.showViewComment=true;
            console.log('current Id:',id);
            this.currentCourseId=id;

            console.log(id);
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/course/comment',
                {
                params: {
                    courseId: id,
                    page:'0',
                }
            }).then(res=>{
            console.log(res.data);
            this.commentText=res.data;
            console.log(this.commentText);
            })
            .catch(error =>console.log(error.data));
        },
        IdentifyCurId2(id){
            this.showAddComment=true;
            console.log('current Id:',id);
            this.currentCourseId=id;
        },
        IdentifyCurId3(id,workoutname){
            this.showToAddPlan=true;
            console.log('current Id:',id);
            this.currentCourseId=id;
            this.workoutName=workoutname;
        },
        IdentifyCurId4(name){
            console.log(name);
            sessionStorage.setItem('videoSrc',JSON.stringify("http://121.4.185.108/images/"+name+".mp4"));
            this.$router.push("/about");
        },
        addComment(){
            console.log(this.commentText);
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/course/addComment',
                {
                params: {
                    accountName: this.accountName,
                    courseId: this.currentCourseId,
                    content: this.commentText,
                }
            }).then(res=>{
            this.returnlists=res.data;
            this.showAddComment=false;
            this.currentCourseId= '';
            console.log(this.returnlists);
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
            //sessionStorage.setItem('videoSrc','../assets/barbellFullSquat.mp4');
            //console.log(sessionStorage.getItem('videoSrc'));
            this.show=true;
            console.log(this.returnlists);
            for(var i in this.returnlists){
                //console.log(this.returnlists[i].name);
                this.namelist.push(this.returnlists[i].name);
                //console.log(this.namelist);
            }
                //sessionStorage.setItem('namelist',JSON.stringify(this.namelist));
            //sessionStorage.setItem('accountName',JSON.stringify(this.user.accountName));
            if(res.data){
                console.log('成功');
            }
            else{
                console.log('失败');
            }
            })
            .catch(error =>console.log(error.data));
        },
        addToplan(){
            console.log(this.currentCourseId,this.groupNum,this.times,this.weight);
            this.$axios.get(
                'http://124.70.23.6:8080/api/v1/createTrainingPlan',
                {
                params: {
                    accountName: this.accountName,
                    workoutName: this.workoutName,
                    groupNum: this.groupNum,
                    times: this.times,
                    weight: this.weight,
                }
            })
            .then(res=>{
                console.log('res=>',res.data);
                if(res.data){
                console.log('创建训练成功');
                this.showToAddPlan = false;
                }
                else{
                console.log('创建训练失败');
                this.showToAddPlan = false;
                }
            })
            .catch(error =>console.log(error.data));
        }
    }
}
</script>