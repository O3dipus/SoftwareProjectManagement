<template>
  <v-container>
    <v-row>
      <v-col>
        <v-card img="../assets/fitness.jpg">
          <v-img
            src="../assets/fitness.jpg"
            class="align-end"
            gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
            height="200px"
          >
            <v-card-title
                class="text-h5"
              >Training Plan</v-card-title>

              <v-card-subtitle>Fix your customized training plan.</v-card-subtitle>
              <v-btn plain dense block router :to="router.fitness">Fix Training Plan</v-btn>
          </v-img>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card img="../assets/fitness.jpg">
          <v-img
            src="../assets/diet.jpg"
            class="align-end"
            gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
            height="200px"
          >
            <v-card-title
                class="text-h5"
              >Diet Plan</v-card-title>

              <v-card-subtitle>Fix your customized diet plan.</v-card-subtitle>
              <v-btn plain dense block>Fix Diet Plan</v-btn>
          </v-img>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card img="../assets/forum.jpg">
          <v-img
            src="../assets/forum.jpg"
            class="align-end"
            gradient="to bottom, rgba(255,255,255,0), rgba(255,255,255.1)"
            height="200px"
          >
            <v-card-title
                class="text-h5"
              >Forum</v-card-title>

              <v-card-subtitle>Discuss and make progress.</v-card-subtitle>
              <v-btn plain dense block router :to="router.about">Enter forum</v-btn>
          </v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    name: 'HelloWorld',

    data: () => ({
      router:{
        fitness:'/training/home',
        about:'/about'
      }
    }),
  }
</script>
