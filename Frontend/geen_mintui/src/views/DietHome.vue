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
                        >My plan</v-card-title>

                        <v-card-subtitle>Get your daily diet plan</v-card-subtitle>
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
        }
    }),
}
</script>
