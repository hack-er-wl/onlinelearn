<template>
  <el-dialog title="修改密码">
    <el-form>
      <el-form-item label="旧密码">
        <el-input prefix-icon="User" show-password v-model="old_pass" placeholder="请输入旧密码"/>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input prefix-icon="User" show-password v-model="new_pass" placeholder="请输入新密码"/>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input prefix-icon="User" show-password v-model="new_pass_again" placeholder="请输入新密码"/>
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
const old_pass = ref('');
const new_pass = ref('');
const new_pass_again = ref('');
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