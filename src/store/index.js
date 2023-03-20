import { createStore } from "vuex";
// 用户相关的store
import userStore from "../store/modules/user";
import layoutStore from "../store/modules/layout";

export default createStore({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    userStore,
    layoutStore,
  },
});
