import "../style/color.scss"


export default function useNotification(type,title,message){
    ElNotification({
        type:type,
        title:title,
        message: message,
        showClose: false,
    });
}