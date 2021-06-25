<template>
    <v-container>
        <v-row>
            <v-col cols=12>
                <v-carousel
                    cycle
                    height="400"
                    hide-delimiter-background
                    show-arrows-on-hover
                >
                    <v-carousel-item
                    v-for="(item,i) in items"
                    :key="i"
                    :src="item.src"
                    >
                    </v-carousel-item>
                </v-carousel>
            </v-col>
            <!--
            <v-col cols=12>
                <v-card>
                    <v-img
                        src="../assets/Food.png"
                        class="align-end"
                        gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                        max-height="400px"
                    >
                        <v-card-title
                            class="text-h5"
                        >Manage your Diet plan</v-card-title>

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
            -->

            <v-col cols=12>
                <v-hover
                v-slot="{ hover }"
                >
                    <v-card
                    :elevation="hover ? 16 : 2"
                    :class="{ 'on-hover': hover }">
                        <v-img
                            src="../assets/diet.jpg"
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
                </v-hover>
            </v-col>

            <v-col cols=12>
                <v-hover
                v-slot="{ hover }"
                >
                    <v-card
                    :elevation="hover ? 16 : 2"
                    :class="{ 'on-hover': hover }">
                        <v-img
                            src="../assets/DietPlan.png"
                            class="align-end"
                            gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
                            height="450px"
                        >
                            <v-card-title
                                class="text-h5"
                            >My diet plan</v-card-title>

                            <v-card-subtitle>Check your daily diet plan</v-card-subtitle>
                            <v-btn plain dense block router :to="router.myPlan">My diet plan</v-btn>
                        </v-img>
                    </v-card>
                </v-hover>
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
        items: [
            {
                src: 'http://121.4.185.108/images/Diet1.png',
            },
            {
                src: 'http://121.4.185.108/images/Diet2.png',
            },
            {
                src: 'http://121.4.185.108/images/Diet3.png',
            },
            {
                src: 'http://121.4.185.108/images/Diet4.png',
            },
        ],
    }),
}
</script>
