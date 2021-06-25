<template>
  <v-app id="inspire">
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="8">
            <v-card class="elevation-12">
              <v-window v-model="step">

                <v-window-item :value="1">
                  <v-row>
                    <v-col cols="12" md="8">
                      <v-card-text class="mt-12">
                        <h1
                          class="text-center display-2 teal--#424242 text--accent-3 "
                        >Sign in to Geen</h1>
                        <h4 class="text-center mt-4">Ensure your email for registration</h4>
                        <v-form>
                          <v-text-field
                            label="Email"
                            name="Email"
                            v-model="user.accountName"
                            :rules="Rules"
                            input=""
                            prepend-icon="mdi-email"
                            type="text"
                            color="teal--#424242"
                          />

                          <v-text-field
                            id="password"
                            label="Password"
                            name="password"
                            v-model="user.password"
                            :rules="Rules"
                            prepend-icon="mdi-login"
                            type="password"
                            color="teal--#424242"
                          />
                        </v-form>
                        <h3 class="text-center mt-4">Forgot your password ?</h3>
                      </v-card-text>
                      <div class="text-center mt-3">
                        <v-btn rounded color="teal--#424242 grey darken-3" dark @click="Login()">SIGN IN</v-btn>
                      </div>
                    </v-col>
                    <v-col cols="12" md="4" class="teal--#424242 grey darken-3" >
                      <v-card-text class="white--text mt-12" >
                        <h1 class="text-center display-1">Hello, Friend!</h1>
                        <h5
                          class="text-center"
                        >Enter your personal details and start journay with us</h5>
                      </v-card-text>
                      <div class="text-center">
                        <v-btn rounded outlined dark @click="step++" >SIGN UP</v-btn>
                      </div>
                    </v-col>
                  </v-row>
                </v-window-item>

                <v-window-item :value="2">
                  <v-row class="fill-height">
                    <v-col cols="12" md="4" class="teal--#424242 grey darken-3">
                      <v-card-text class="white--text mt-12">
                        <h1 class="text-center display-1">Welcome Back!</h1>
                        <h5
                          class="text-center"
                        >To Keep connected with us please login with your personnel info</h5>
                      </v-card-text>
                      <div class="text-center">
                        <v-btn rounded outlined dark @click="step--">Sign in</v-btn>
                      </div>
                    </v-col>

                    <v-col cols="12" md="8">
                      <v-card-text class="mt-12">
                        <h1 class="text-center display-2 teal--#424242 text--accent-3">Create Account</h1>
                        <h4 class="text-center mt-4">Ensure your email for registration</h4>
                        <v-form>
                          <v-text-field
                            label="Account Name"
                            name="Account Name"
                            v-model="register.accountName"
                            :rules="Rules"
                            type="Account Name"
                            color="teal--#424242"
                          />
                          <v-text-field
                            id="password"
                            label="Password"
                            v-model="register.password"
                            :rules="Rules"
                            name="password"
                            type="password"
                            color="teal--#424242"
                          />
                          <v-text-field
                            id="CheckPassword"
                            label="CheckPassword"
                            v-model="register.checkPassword"
                            :rules="Rules"
                            name="CheckPassword"
                            type="CheckPassword"
                            color="teal--#424242"
                          />
                        </v-form>
                      </v-card-text>
                      <div class="text-center mt-n5">
                        <v-btn rounded color="teal--#424242 grey darken-3" dark @click="Register">SIGN UP</v-btn>
                      </div>
                    </v-col>
                  </v-row>
                </v-window-item>
              </v-window>
            </v-card>
          </v-col>
          <v-col cols="8">
            <v-alert
              v-model="alert"
              dismissible
              color="teal--#424242"
              border="left"
              elevation="2"
              colored-border
            >
              {{this.errorMessage}}
            </v-alert>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
export default {
  data: () => ({
    step: 1,
    errorMessage:'',
    alert: false,
    user: {
      accountName:'',
      password:''
    },
    register: {
      accountName:'',
      password:'',
      checkPassword:'',
    },
    Rules: [
      v => !!v || 'NULL is invalid',
      v => (v && v.length <= 16) || 'It must be less than 16 characters',
    ],
  }),
  props: {
    source: String
  },
  methods: {
    Login(){
      var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
      if(this.user.accountName == ''){
        console.log("accountName mustn' be null");
        this.errorMessage='accountName mustn\' be null';
        this.alert = true;
      }
      else if (!regEmail.test(this.user.accountName)){
        console.log("Email format error");
        this.errorMessage='Email format error';
        this.alert = true;
      }
      else if (this.user.password == ''){
        console.log("password mustn' be null");
        this.errorMessage='password mustn\' be null';
        this.alert = true;
      }else{
        console.log(this.user);
        this.$axios.get(
          'http://124.70.23.6:8080/api/v1/login',
          {
            params: {
              accountName: this.user.accountName,
              password: this.user.password
            }
        })
        .then(res=>{
          console.log('res=>',res.data);
          if(res.data != 'invalid mail!' && res.data != 'wrong password!'){
            console.log('登陆成功');
            //登录成功后跳转到指定页面
            sessionStorage.clear();
            sessionStorage.setItem('accountName',JSON.stringify(this.user.accountName));
            console.log('session');
            this.$router.push("/");
          }
          else{
            console.log("password isn't correct");
            this.errorMessage='password isn\'t correct';
            this.alert = true;
          }
        })
        .catch(error =>console.log(error.data));
      }
    },
    Register(){
      var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
      if(this.register.accountName == ''){
        console.log("accountName mustn' be null");
        this.errorMessage='accountName mustn\' be null';
        this.alert = true;
      }
      else if (!regEmail.test(this.register.accountName)){
        console.log("Email format error");
        this.errorMessage='Email format error';
        this.alert = true;
      }
      else if(this.register.password == ''){
        console.log("password mustn' be null");
        this.errorMessage='password mustn\' be null';
        this.alert = true;
      }
      else if(this.register.password != this.register.checkPassword){
        console.log("checkPassword must be same as password");
        this.errorMessage='password mustn\' be null';
        this.alert = true;
      }
      else{
        console.log("Good");
        this.$axios.get(
        'http://124.70.23.6:8080/api/v1/register',
        {
          params: {
            accountName: this.register.accountName,
            password: this.register.password
          }
      })
      .then(res=>{
        console.log('res=>',res.data);
        if(res.data != 'invalid mail!'){
          console.log('注册成功');
          //登录成功后跳转到指定页面
          sessionStorage.clear();
          sessionStorage.setItem('accountName',JSON.stringify(this.register.accountName));
          console.log('session');
          this.$router.push("/");
        }
        else{
          console.log('注册失败');
          this.alert = true;
        }
      })
      .catch(error =>console.log(error.data));
      }
    }
  }
};
</script>