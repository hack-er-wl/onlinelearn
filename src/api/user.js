//用户相关的网络请求
import {get, post} from "../network/request";

/**
 * @description: 用户登录
 */
export function login(data) {
    return get("user/login",data);
}
/**
 * @description: 获取验证码
 */
export function code(data) {
    return get("user/email",data);
}
/**
 * @description: 用户注册
 */
export function regist(data) {
    return post("user/regist",data);
}
/**
 * @description: 获取用户信息
 */
export function getUser(){
    return  JSON.parse(localStorage.getItem('user'));
}
/**
 * @description: 获取轮播图
 */
export function getSliders(data) {
    return get("user/query/slider", data);
}
export function addField(data) {
    return post("admin/post/cfield", data);
}
export function queryFields(data){
    return get("user/query/cfield")
}
export function queryClasses(data){
    return get("user/query/cclass",data)
}
export function queryCourseByStatus(data){
    return get("user/query/course/bystatus",data)
}
export function queryCourseByCourseId(data){
    return get("user/query/course/bycourseid",data)
}
export function queryCourseByClassId(data){
    return get("user/query/course/byclassid",data)
}
export function queryTeacherByCourseId(data){
    return get("user/query/teacher",data)
}
export function queryCollect(data){
    return get("user/query/collect",data)
}
export function addCollect(data){
    return post("user/collect",data)
}
export function cancelCollect(data){
    return post("user/cancel/collect",data)
}
export function subscribeCourse(data){
    return post("user/subscribe",data)
}
export function cancelSubscribeCourse(data){
    return post("user/cancel/subscribe",data)
}
export function querySubscribeCourse(data){
    return get("user/query/subscribe",data)
}
export function postAssess(data){
    return post("user/assess",data)
}
export function queryAssess(data){
    return get("user/query/assess",data)
}
export function postReply(data){
    return post("user/reply",data)
}
export function queryReply(data){
    return get("user/query/reply",data)
}
export function queryRecommend(data){
    return get("user/query/course/recommend",data)
}