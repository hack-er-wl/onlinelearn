export default {
    state: {
        isExpand: true,
        isCollapse:false,
        icon:false,
        isReg:false,
        Hide:false,
        isHide:false,
        perHide:false,//用户编辑
        pasHide:false,//修改密码
        tpreHide:false,//讲师编辑
        addCouHide:false,
        addTeHide:false,
        callHide:false,
        isDot:false,
        learn_num:0,
        show:[false,true,true],
        slider:[],
        fields:[],
        hotCourse:[],
        newCourse:[],
        results:[],
        teacher:{},
        course:{},
        recCourses:[],
        assesses:[],
        assessid:'',
        acceptid:'',
        isDark:
            localStorage.getItem("vueuse-color-scheme") == "dark" ? true : false || false,},
    mutations: {
        updateIsExpand(state, flag) {
            state.isExpand = flag;
        },
        setIsDark(state, theme) {
            state.isDark = theme;
        },
    },
};