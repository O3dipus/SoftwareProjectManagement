<template>
    <v-container>
        <v-row>
            <v-col cols=12>
                <v-card>
                    <v-img
                        src="../assets/fitness_plan.jpg"
                        class="align-end"
                        gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                        height="200px"
                    >
                        <v-card-title
                            class="text-h5"
                        >Fix Plan</v-card-title>

                        <v-card-subtitle>Fix your customized training plan today.</v-card-subtitle>
                        <v-btn plain dense block router :to="router.fix">Fix Training Plan</v-btn>
                    </v-img>
                </v-card>
            </v-col>
            <v-col cols=6>
                <v-card>
                    <v-img
                        src="../assets/personal_data.jpg"
                        class="align-end"
                        gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                        height="200px"
                    >
                        <v-card-title
                            class="text-h5"
                        >My Data</v-card-title>

                        <v-card-subtitle>Modify your personal information.</v-card-subtitle>
                        <v-btn plain dense block>My Data</v-btn>
                    </v-img>
                </v-card>
            </v-col>
            <v-col cols=6>
                <v-card>
                    <v-img
                        src="../assets/history_data.jpg"
                        class="align-end"
                        gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                        height="200px"
                    >
                        <v-card-title
                            class="text-h5"
                        >History</v-card-title>

                        <v-card-subtitle>View your history training routine.</v-card-subtitle>
                        <v-btn plain dense block>History</v-btn>
                    </v-img>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: 'DietHome',

    data: () => ({
        router:{
            fix:'/training/fix',
            mydata:'/training/mydata',
            history:'/training/history'
        }
    }),
}
</script>
