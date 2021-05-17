<template>
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
                        <v-select outlined label="Movement" :items="movement"></v-select>
                    </v-col>
                    <v-col cols="5">
                        <v-text-field label="Group Number" v-model="group_number" outlined>
                        </v-text-field>
                    </v-col>
                    <v-col cols="6" offset="1">
                        <v-slider
                            v-model="group_number"
                            step="1"
                            max=100
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
            <v-btn bottom block>Submmit</v-btn>
        </v-form>
    </v-card>
</template>

<script>
    export default {
        name: 'FixTrainingPlan',

        components: {

        },

        data: () => ({
            router:{
                
            },
            movement:['Movement 1','Movement 2','Movement 3',],
            group_number:0,
            weight_each_group:0,
            times_each_group:0
        }),
    }
</script>