import { createApp } from 'vue'
import router from './router'
import store from './store'
import App from './App.vue'
import * as Icons from '@element-plus/icons'
import 'element-plus/theme-chalk/dark/css-vars.css'
import { MotionPlugin } from "@vueuse/motion"
import i18n from "./language";
import "bootstrap-icons/font/bootstrap-icons.css";
import weather from 'vue-mini-weather';
import 'element-plus/dist/index.css';
import ElementPlus from 'element-plus';


const app = createApp(App)
app.use(router).use(i18n).use(MotionPlugin).use(store).use(weather).use(ElementPlus).mount('#app')
Object.keys(Icons).forEach(key => {
    app.component(key, Icons[key]);
})