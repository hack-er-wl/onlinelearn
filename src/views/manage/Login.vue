<template>
  <div class="login-container" id="login_id">
    <div class="container">
      <div class="right_box">
        <h6 v-motion
            :initial="{ opacity: 0, y: -100 }"
            :enter="{ opacity: 1, y: 0}"
            :delay="200"
            class="title">
          <img src="../../assets/img/logo.png" style="width: 30px;height: 30px">&nbsp;
          <p style="font-size: 18px;text-align: center">{{$t('mtitle')}}</p>
        </h6>
        <div class="form-container">
          <el-form
              ref="loginFormRef"
              :model="loginForm"
              :rules="loginRules"
              label-width="120px"
              class="demo-ruleForm"
              size="large"
              status-icon>
            <el-form-item
                label=""
                prop="username"
                v-motion
                :initial="{ opacity: 0, y: 100 }"
                :enter="{ opacity: 1, y: 0, transition: { delay: 300 }}">
              <el-input
                  v-model="loginForm.username"
                  :placeholder="$t('userPlaceholder')">
                <template #prefix>
                  <el-icon><Avatar /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item
                label=""
                prop="password"
                v-motion
                :initial="{ opacity: 0, y: 100 }"
                :enter="{ opacity: 1, y: 0, transition: { delay: 400 }}">
              <el-input
                  type="password"
                  show-password
                  :placeholder="$t('passPlaceholder')"
                  v-model="loginForm.password">
                <template #prefix>
                  <el-icon><Lock /></el-icon>
                </template>
              </el-input>
            </el-form-item>
          </el-form>
          <!-- 提交表单 -->
          <div style="width: 100%;display: flex"
               v-motion
               :initial="{ opacity: 0, y: 100 }"
               :enter="{ opacity: 1, y: 0, transition: { delay: 500 } }">
            <el-button
                style="height: 38px"
                type="primary"
                @click="handleLogin()"
                :loading="btnLogLoading">
              {{$t('signIn')}}
            </el-button>
          </div>
          <div style="width: 100%;display: flex;margin-top: 15px;justify-content: center">
            <el-button circle @click="weiboLogin()" size="small" style="height: 30px;width: 30px" class="bi bi-sina-weibo"/>
            <el-button circle @click="qqLogin()" size="small" style="height: 30px;width: 30px" class="bi bi-tencent-qq"/>
            <el-button circle @click="githubLogin()" size="small" style="height: 30px;width: 30px" class="bi bi-github"/>
          </div>
        </div>
        <!-- 国际化 -->
        <Language class="language" />
        <!-- 主题切换 -->
        <Theme class="theme" />
      </div>
    </div>
    <div style="color: gray;text-align: center;font-size: 12px">
      <p>Copyright © 2023 在线学习平台</p>
      <p>作者：Wali</p>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, toRaw } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import Language from "../../components/user/common/Language.vue";
import Theme from "../../components/user/common/Theme.vue";
import {useI18n} from "vue-i18n";
import useNotification from "../../hooks/useNotification";

// 访问 .env配置文件中设置的变量
console.log(process.env.VUE_APP_TOKEN_NAME);

const router = useRouter();
const store = useStore();
const { t } = useI18n();
// 获取到表单元素
const loginFormRef = ref(null);

// 登录的表单数据
const loginForm = reactive({
  username: "",
  password: "",
  code:""
});
// 登录按钮的加载loading
const btnLogLoading = ref(false);

// 登录表单的校验规则
const loginRules = reactive({
  username: [{ required: true, message: t("userError"), trigger: "blur" }],
  password: [
    { required: true, message: t("PWError"), trigger: "blur" },
    { min: 3, max: 8, message: t("PWSubError"), trigger: "blur" },
  ],
  code: [{ required: true, message: t("codeError"), trigger: "blur" }]
});

//注册事件
// 点击登录按钮，处理登录
async function handleLogin() {
  // 1、表单校验
  await loginFormRef.value.validate(async (valid,fields) => {
    if (valid) {
      // 开启loading状态
      btnLogLoading.value = true;
      // 用通过vuex发送网络请求
      const res = await store.dispatch("handleLogin", toRaw(loginForm));
      if (res) {
        useNotification('success','系统通知',t("signUpSuccess"));
        await router.push({path: "/"});
      }
    } else {
      // 校验不通过
      console.log("error submit!", fields);
    }
  });
}
function weiboLogin(){
  useNotification('success','系统通知',"微博登录功能还未实现");
}
function qqLogin(){
  useNotification('success','系统通知',"QQ登录功能还未实现");
}
function githubLogin(){
  useNotification('success','系统通知',"Github登录功能还未实现");
}
</script>

<style lang="scss" scoped>
@import "../../style/mixin";

.login-container {
  height: 100vh;
  width: 100%;
  background-color: rgb(240,242,245);
  overflow: hidden;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;

  .container {
    background-color: var(--el-bg-color);
    height: 350px;
    display: flex;
    border-radius: 6px;
    overflow: hidden;
    box-shadow: 0 5px 20px 2px rgba(255, 255, 255, 0.1);

    .right_box {
      width: 344px;
      padding: 20px;
      position: relative;
      .title {
        display: flex;
        justify-content:center;
        font-size: 26px;
        [class="light"] & {
          color: $dark-bg-color;
        }

        [class="dark"] & {
          color: $light-bg-color;
        }
        margin: 20px auto 30px auto;
        text-align: center;
        font-weight: 700;
      }

      .form-container {
        display: flex;
        justify-content: center;
        flex-wrap: wrap;

        .el-form {
          width: 100%;

          /* vue3中的样式穿透 */
          ::v-deep(.el-form-item__content) {
            margin-left: 0px !important;
          }
        }

        .el-button {
          width: 100%;
        }
      }

      .language {
        width: 38px;
        height: 40px;
        position: absolute;
        right: 0px;
        top: 0px;
      }

      .theme {
        width: 38px;
        height: 40px;
        position: absolute;
        right: 38px;
        top: 0px;
      }
    }
  }
}
</style>