<template>
  <div class="about">
    <h1>This is an about page</h1>
    <v-btn
      elevation="2"
      @click="test"
    >fuck me{{this.$store.state.count}}</v-btn>
  </div>
</template>

<script>

export default {
  methods: {
    test(){
      this.$axios.get(
        'http://124.70.23.6:8080/api/v1/login',
        {
          params: {
            accountName: '761447951@qq.com',
            password: '123456'
          }
      })
      .then(response =>console.log(response.data))
      .catch(error =>console.log(error.data));
    }
  }
}
</script>

