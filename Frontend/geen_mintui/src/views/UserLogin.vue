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
                    </v-row>
                </v-container>
        </v-card>

        <v-container class="grey lighten-5">
        <v-row
        class="mb-6"
        no-gutters
        >
        <v-col 
        md="3">
            <v-btn
            depressed
            elevation="2"
            outlined
            @click="turnToLogin">Login</v-btn>
        </v-col>
        <v-col 
        md="3" 
        offset-md="4">
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

    components: {

    },

    data: () => ({
        user_name : 'nyx',
        gender:'male',
        age:16,

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
    methods: {
        turnToLogin(){
            this.$router.push("/Login");
        }
    }
}
</script>