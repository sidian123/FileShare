import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axiosInit from './config/AxiosConfig'

Vue.config.productionTip = false

Vue.use(ElementUI);

let vue = new Vue({
  render: h => h(App),
  created(){
    this.axiosInit();
  },
  methods:{
    axiosInit
  }
}).$mount('#app')