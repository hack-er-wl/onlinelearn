import {reactive} from "vue";
import useNotification from "../hooks/useNotification";
export const data = reactive({
    notices:[],
    messages:[]
})
export function initWebSocket(){
    const res = JSON.parse(localStorage.getItem('user'));
    const webSocket = new WebSocket("ws://localhost:8080/websocket/"+res.user_id+"/");
//连通之后的回调事件
    webSocket.onopen = function () {
        useNotification('success','系统通知','Websocket已连接!');
    };
//当收到消息时
    webSocket.onmessage = (evt) => {
        const received_msg = evt.data;
        const obj = JSON.parse(received_msg);
        if(obj.fromusername == "系统"){
            useNotification('success','系统通知','收到系统通知!');
            data.notices.push(obj);
        }else{
            useNotification("success","系统消息","收到客服中心消息！");
            data.messages.push(obj);
        }
    }
    //连接关闭的回调事件
    webSocket.onclose = function () {
        useNotification('error','系统通知','webSocket已关闭!');
    }
    return webSocket;
}
