import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import qs from'qs'
import Store from './store/store'

import VideoPlayer from 'vue-video-player'
require('video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')
Vue.use(VideoPlayer)

Vue.use(MintUI)
Vue.prototype.$axios = axios;
Vue.prototype.$qs=qs

new Vue({
    router,
    vuetify,
    Store,
    render: h => h(App)
}).$mount('#app')
