<template>
    <v-container>
        <v-row>
            <v-col cols=12>
                <v-card>
                    <v-img
                        src="../assets/Food.png"
                        class="align-end"
                        gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                        max-height="300px"
                    >
                        <v-card-title
                            class="text-h5"
                        >Manage your plan</v-card-title>

                        <v-card-subtitle>Create,change and delete your diet plan</v-card-subtitle>
                        <v-row
                        align="center"
                        justify="center">
                            <v-btn-toggle
                            v-model="toggle_exclusive"
                            plain
                            dense 
                            block
                            >
                            <v-btn router :to="router.createPlan">
                                Create diet plan
                            </v-btn>
                            <v-divider
                            vertical
                            ></v-divider>
                            <v-btn router :to="router.changePlan">
                                Change diet plan
                            </v-btn>
                            <v-divider
                            vertical
                            ></v-divider>
                            <v-btn router :to="router.deletePlan">
                                Delete diet plan
                            </v-btn>
                            </v-btn-toggle>
                        </v-row>
                    </v-img>
                </v-card>
            </v-col>

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
                        >Food Query</v-card-title>

                        <v-card-subtitle>Search for Food Datan</v-card-subtitle>
                        <v-btn plain dense block router :to="router.foodQuery">Food Query</v-btn>
                    </v-img>
                </v-card>
            </v-col>

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
                        >My plan</v-card-title>

                        <v-card-subtitle>Check your daily plan</v-card-subtitle>
                        <v-btn plain dense block router :to="router.myPlan">My plan</v-btn>
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
            myPlan:'/diet/myPlan',
            changePlan:'/diet/changeplan',
            createPlan:'/diet/createplan',
            deletePlan:'/diet/deleteplan',
            foodQuery:'/diet/FoodQuery',
        },
        toggle_exclusive: undefined,
    }),
}
</script>
