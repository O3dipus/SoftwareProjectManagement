import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
import vuetify from './plugins/vuetify'

Vue.use(MintUI)

new Vue({
    router,
    vuetify,
    render: h => h(App)
}).$mount('#app')