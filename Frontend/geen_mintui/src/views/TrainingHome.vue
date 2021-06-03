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
                        >Search for course</v-card-title>

                        <v-card-subtitle>Search for the course information</v-card-subtitle>
                        <v-btn plain dense block router :to="router.searchCource">Search for course</v-btn>
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

            <v-col cols=4>
                <v-card>
                    <v-img
                        src="../assets/history_data.jpg"
                        class="align-end"
                        gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                        height="200px"
                    >
                        <v-card-title
                            class="text-h7"
                        >Create Plan</v-card-title>

                        <v-card-subtitle>Create Plan</v-card-subtitle>
                        <v-btn plain dense block router :to="router.createPlan">Create Plan</v-btn>
                    </v-img>
                </v-card>
            </v-col>

            <v-col cols=4>
                <v-card>
                    <v-img
                        src="../assets/history_data.jpg"
                        class="align-end"
                        gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                        height="200px"
                    >
                        <v-card-title
                            class="text-h7"
                        >Change Plan</v-card-title>

                        <v-card-subtitle>Change Plan</v-card-subtitle>
                        <v-btn plain dense block router :to="router.changePlan">Change Plan</v-btn>
                    </v-img>
                </v-card>
            </v-col>
            <v-col cols=4>
                <v-card>
                    <v-img
                        src="../assets/history_data.jpg"
                        class="align-end"
                        gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                        height="200px"
                    >
                        <v-card-title
                            class="text-h7"
                        >Delete Plan</v-card-title>

                        <v-card-subtitle>Delete Plan</v-card-subtitle>
                        <v-btn plain dense block router :to="router.deletePlan">Delete Plan</v-btn>
                    </v-img>
                </v-card>
            </v-col>

            
            <v-col cols=12>
                <v-card>
                    <v-img
                        src="../assets/history_data.jpg"
                        class="align-end"
                        gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                        height="200px"
                    >
                        <v-card-title
                            class="text-h7"
                        >My Plan</v-card-title>

                        <v-card-subtitle>My Plan</v-card-subtitle>
                        <v-btn plain dense block router :to="router.myPlan">My Plan</v-btn>
                    </v-img>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: 'TrainingHome',

    data: () => ({
        router:{
            fix:'/training/fix',
            searchCource:'/training/searchCourse',
            history:'/training/history',
            createPlan:'/training/createPlan',
            changePlan:'/training/changePlan',
            deletePlan:'/training/deletePlan',
            myPlan:'/training/myPlan',
        }
    }),
}
</script>
