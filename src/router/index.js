import { createRouter, createWebHistory } from "vue-router";
import store from "../store/index";
import useNProgress from "../hooks/useNProgress";

const NProgress = useNProgress();

const routes = [
  {
    path: "",
    redirect: "/home",
    component: ()=>import("../views/user/Home.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/user/Login.vue"),
  },
  {
    path: "/register",
    name: "register",
    component: () => import("../views/user/Register.vue"),
  },
  {
    path: "/home",
    name: "home",
    component: () => import("../views/user/Home.vue"),
  },
  {
    path: "/course",
    name: "course",
    component: () => import("../views/user/Course.vue"),
  },
  {
    path: "/test",
    name: "test",
    component: () => import("../views/user/Test.vue"),
  },
  {
    path: "/personage",
    name: "personage",
    component: () => import("../views/user/Personage.vue"),
  },
  {
    path: "/socket",
    name: "socket",
    component:() => import("../views/manage/Socket.vue")
  },
  {
    path: "/apply",
    name: "apply",
    component:() => import("../views/teacher/Apply.vue"),
    children:[
        {
          path: "",
          name:"tdeal",
          component:() => import("../components/teacher/apply/TDeal.vue")
        },
        {
          path: "tdetail",
          name:"tdetail",
          component:() => import("../components/teacher/apply/TDetail.vue")
        }
    ]
  },{
    path: "/thome",
    name: "thome",
    component:() => import("../views/teacher/THome.vue")
  },{
    path: "/result",
    name: "result",
    component:() => import("../views/user/Result.vue")
  },
  {
    path: "/notice",
    name: "message",
    component:() => import("../views/user/Notice.vue")
  },
  // 匹配404路由
  {
    path: "/notfind",
    name: "notfind",
    component: () => import("../views/user/Notfind.vue"),
  },
  {
    path: "/mlogin",
    name: "mlogin",
    component: () => import("../views/manage/Login.vue"),
  },
  {
    path: "/mhome",
    name: "mhome",
    redirect: "/mhome/userdata",
    component: () => import("../views/manage/Home.vue"),
    children:[
      {
        path: "userdata",
        name:"userdata",
        component:() => import("../components/manage/backdata/UserData.vue")
      },
      {
        path: "coursedata",
        name:"coursedata",
        component:() => import("../components/manage/backdata/CourseData.vue")
      },
      {
        path: "manageuser",
        name:"manageuser",
        component:() => import("../components/manage/user/ManageUser.vue")
      },
      {
        path: "managecourse",
        name:"managecourse",
        component:() => import("../components/manage/course/ManageCourse.vue")
      },
      {
        path: "managefield",
        name:"managefield",
        component:() => import("../components/manage/course/ManageField.vue")
      },
      {
        path: "manageclass",
        name:"manageclass",
        component:() => import("../components/manage/course/ManageClass.vue")
      },
      {
        path: "managetest",
        name:"managetest",
        component:() => import("../components/manage/test/ManageTest.vue")
      },
      {
        path: "manageteacher",
        name:"manageteacher",
        component:() => import("../components/manage/teacher/ManageTeacher.vue")
      },
      {
        path: "manageresource",
        name:"manageresource",
        component:() => import("../components/manage/resource/ManageResource.vue")
      },
      {
        path: "managemessage",
        name:'managemessage',
        component:() => import("../components/manage/message/ManageMessage.vue")
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  // 开启进度条动画
  NProgress.start();

  const token = store.state.userStore.token;
  if (to.name !== "login") {
    if (!token) {
      if(to.name == "home" || to.name == "course" || to.name == "test" || to.name == "personage" ||
          to.name == "register" || to.name == "socket" || to.name == "apply"  || to.name == "tdeal" ||
          to.name == "tdetail" || to.name == "notfind" || to.name == "thome" || to.name == "result" ||
          to.name == "message" || to.name == "mlogin" || to.name == "mhome" || to.name == "userdata" ||
          to.name == "coursedata" || to.name == "manageuser" || to.name == "managecourse" || to.name == "managetest" ||
          to.name == "manageteacher" || to.name == "manageresource" || to.name == "managemessage" ||
          to.name == "managefield" || to.name == "manageclass"){
        next();
      }else{
        // 返回到登录页
        next({ name: "login" });
      }
    } else {
      // 页面刷新，重新加载权限路由
      if (!store.state.userStore.isDynamicAddedRoute) {
        store.dispatch("loadAsyncRoute").then(() => {
          router.push(to.fullPath).then(r => {});
        });} else {
        next();
      }
    }
  } else {
    next();
  }
});

router.afterEach(() => {
  // 关闭进度条动画
  NProgress.done();
});

export default router;
