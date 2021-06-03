<template>
  <v-app>
    <v-app-bar app>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    </v-app-bar>
    
    <v-navigation-drawer app 
      v-model="drawer"
      
      temporary
      bottom>
        <v-list>
          <v-list-item v-for="(item,i) in list_item" :key="i" router :to="item.router">
            <v-list-item-icon>
              <v-icon v-text="item.icon"></v-icon>
            </v-list-item-icon>
            {{item.title}}
          </v-list-item>
        </v-list>
    </v-navigation-drawer>

    <v-main>
      <router-view />
    </v-main>

    <v-footer fixed app padless>
      <v-container fluid>
        <v-row no-gutters class="pa-0 ma=0">
          <v-col v-for="(item,i) in btn_item" :key="i" cols=3>
            <v-btn dense text tile block router :to="item.router" class="mx-2">
              <v-container>
                <v-row dense align="center">
                  <v-col>
                    <v-icon v-text="item.icon" large></v-icon>
                    <div class="font-weight-light">{{item.title}}</div>
                  </v-col>
                </v-row>
              </v-container>
            </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-footer>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    drawer:false,
    list_item:[
      {title:'home',icon:'mdi-home',router:'/'},
      {title:'training plan',icon:'mdi-human-handsup',router:'/training/home'},
      {title:'diet plan',icon:'mdi-food-fork-drink',router:'/diet/home'},
      {title:'my account',icon:'mdi-account',router:'/acccount'},
      {title:'login',icon:'mdi-login',router:'/login'},
    ],
    btn_item:[
      {title:'home',icon:'mdi-home',router:'/'},
      {title:'train',icon:'mdi-human-handsup',router:'/training/home'},
      {title:'diet',icon:'mdi-food-fork-drink',router:'/diet/home'},
      {title:'user',icon:'mdi-account',router:'/user'},
    ],
    footer_selected:''
  }),
};
</script>
