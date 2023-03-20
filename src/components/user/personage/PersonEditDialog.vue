<template>
  <el-dialog title="编辑信息">
    <el-form>
      <el-form-item label="昵称">
        <el-input prefix-icon="User" v-model="nick" placeholder="请输入昵称"/>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input prefix-icon="Promotion" v-model="email" placeholder="请输入邮箱"/>
      </el-form-item>
      <el-form-item label="兴趣">
        <el-select style="width: 100%" suffix-icon="Calendar" v-model="like" placeholder="请选择兴趣">
          <el-option
              v-for="item in likes"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="postAssessCancel()">取消</el-button>
        <el-button type="primary" @click="postAssessConfirm()">发布</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup>
import {ref} from "vue";
import { useI18n } from "vue-i18n";
import useNotification from "@/hooks/useNotification";
const { t } = useI18n();
const email = ref('');
const like = ref('');
const likes = [{value:"数据结构",label:"数据结构"},{value:"Java程序设计",label:"Java程序设计"}];
const nick = ref('');

function postAssessConfirm(){
  useNotification('success','系统通知',t("editSuccess"));
}
function postAssessCancel(){
  useNotification('success','系统通知',t("editCancel"));
}
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
