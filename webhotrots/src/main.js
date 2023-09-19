import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import store from '@/store/MyStore.js'; 


import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import "@/assets/css/style.css";


const app = createApp(App);
app.config.globalProperties.$http = axios;
app.use(store);
app.use(router);
app.mount('#app');
