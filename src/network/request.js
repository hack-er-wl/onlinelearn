import axios from 'axios';
import qs from "qs";
import useNotification from "@/hooks/useNotification";
const service = axios.create({
    baseURL:'api',
    // 请求超时时间
    timeout: 1000 * 60 * 5,
});

// 添加请求拦截器
service.interceptors.request.use(
    function (config) {
        // 在发送请求之前给 header 设置 token
        if (!config.url.includes("/login")) {
            config.headers[process.env.VUE_APP_AJAX_HEADER_AUTH_NAME] =
                localStorage.getItem(process.env.VUE_APP_TOKEN_NAME);
        }
        return config;
    },
    function (error) {
        // 对请求错误做些什么
        return Promise.reject(error);
    }
);

// 添加响应拦截器
service.interceptors.response.use(
    function (response) {
        if (response.data.code !== 200) {
            useNotification('error','系统通知',response.data.result);
        }
        // 对响应数据做点什么
        return response.data;
    },
    function (error) {
        // 对响应错误做点什么
        return Promise.reject(error);
    }
);

export function get(url,params){
    return service.get(url,{params});
}

export function post(url,params){
    return service.post(url,qs.stringify(params));
}
