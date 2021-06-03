<template>
    <div>
        <v-card>
                <v-img src="../assets/avatar.jpeg"
                    gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                    >
                    <v-card-title
                        class="text-h5 white--text"
                    >
                        User Profile
                    </v-card-title>
                    <v-container>
                        <v-row>
                            <v-col>
                                <div><h3>Name:</h3>{{user_name}}</div>
                                <div><h3>Age:</h3>{{age}}</div>
                                <div><h3>Gender:</h3>{{gender}}</div>
                                <div><h3>Weight:</h3>{{weight}}</div>
                                <div><h3>Height:</h3>{{height}}</div>
                                <div><h3>ChestCircum:</h3>{{chestCircum}}</div>
                                <div><h3>ArmCircum:</h3>{{armCircum}}</div>
                                <div><h3>HipCircum:</h3>{{hipCircum}}</div>
                                <div><h3>Waistline:</h3>{{waistline}}</div>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-img>
                
                <v-container>
                    <v-row>
                        <v-col>
                            <div><h3>Routine:</h3></div>
                        </v-col>
                        <v-col cols="8" offset="2">
                            <v-sparkline
                                :value="value"
                                :gradient="gradient"
                                :smooth="radius || false"
                                :padding="padding"
                                :line-width="width"
                                :stroke-linecap="lineCap"
                                :gradient-direction="gradientDirection"
                                :fill="fill"
                                :type="type"
                                :auto-line-width="autoLineWidth"
                                auto-draw
                            ></v-sparkline>
                        </v-col>
                        <v-col>
                            <div><h3>Accumulated Training Hours:</h3>{{accumulated_training_hour}}</div>
                        </v-col>
                        <v-col>
                            <div><h3>Diet Duration:</h3>{{diet_duration}}</div>
                        </v-col>
                        <v-btn plain dense block router :to="router.changeData">Change Your Data</v-btn>
                    </v-row>
                </v-container>
        </v-card>

        <v-container class="grey lighten-5">
        <v-row
        class="mb-6"
        no-gutters
        >
        <v-col 
        align="center"
                justify="center"
                col=6>
            <v-btn
            depressed
            elevation="2"
            outlined
            @click="turnToLogin">Login</v-btn>
        </v-col>
        <v-col 
        align="center"
                justify="center"
                col=6>
            <v-btn
            depressed
            elevation="2"
            outlined
            class="pa-2"
            >Logout</v-btn>
        </v-col>
        </v-row>
        
    </v-container>
    </div>
</template>

<script>
const gradients = [
    ['#222'],
    ['#42b3f4'],
    ['red', 'orange', 'yellow'],
    ['purple', 'violet'],
    ['#00c6ff', '#F0F', '#FF0'],
    ['#f72047', '#ffd200', '#1feaea'],
]

export default {
    name: 'UserLogin',

    data: () => ({
        router:{
            changeData:'/changeuserdata',
        },
        user :{
            accountName:'761447951@qq.com',
        },
        user_name : '',
        gender:'',
        age:'',
        weight:'',
        height :'',
        chestCircum :'',
        armCircum :'',
        hipCircum :'',
        waistline :'',

        width: 2,
        radius: 10,
        padding: 8,
        lineCap: 'round',
        gradient: gradients[5],
        value: [0, 2, 5, 9, 5, 10, 3, 5, 0, 0, 1, 8, 2, 9, 0],
        gradientDirection: 'top',
        gradients,
        fill: false,
        type: 'trend',
        autoLineWidth: false,

        accumulated_training_hour:100,
        diet_duration:50
    }),
    mounted() {
        this.getData()
    },
    methods: {
        getData(){
            console.log('yes');
            let url='http://124.70.23.6:8080/api/v1/getInfo/'+this.user.accountName;
            this.$axios.get(url)
            .then(response=>{
                console.log('res=>',response.data);
                let res = response.data;
                this.user_name = res.userName;
                this.gender = res.sex;
                this.age = res.age;
                this.weight = res.weight;
                this.height = res.height;
                this.chestCircum = res.chestCircum;
                this.armCircum = res.armCircum;
                this.hipCircum = res.hipCircum;
                this.waistline = res.waistline;
            })
            .catch(error =>console.log(error.data));
        },
        turnToLogin(){
            this.$router.push("/Login");
        }
    }
}
</script>