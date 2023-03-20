<template>
  <el-dialog title="编辑信息">
    <el-form>
      <div style="display: flex">
        <el-form-item style="width: 60%;margin-right: 5px" label="昵称">
          <el-input prefix-icon="User" v-model="nick" placeholder="请输入昵称"/>
        </el-form-item>
        <el-form-item style="width: 40%" label="年龄">
          <el-select style="width: 100%" suffix-icon="Calendar" v-model="age" placeholder="请选择年龄">
            <el-option
                v-for="item in ages"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
      </div>
      <div style="display: flex">
        <el-form-item style="width: 50%;margin-right: 5px" label="手机">
          <el-input prefix-icon="Iphone" v-model="phone" placeholder="请输入手机号"/>
        </el-form-item>
        <el-form-item style="width: 50%" label="邮箱">
          <el-input prefix-icon="Message" v-model="phone" placeholder="请输入邮箱"/>
        </el-form-item>
      </div>
      <div style="display: flex">
        <el-form-item style="width: 60%;margin-right: 5px" label="性别">
          <el-select style="width: 100%" suffix-icon="Male" v-model="gender" placeholder="请选择性别">
            <el-option
                v-for="item in genders"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item style="width: 40%" label="教龄">
          <el-select style="width: 100%" suffix-icon="Calendar" v-model="tage" placeholder="请选择教龄">
            <el-option
                v-for="item in tages"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
      </div>
      <el-form-item label="兴趣">
        <el-input
            v-model="interest"
            :rows="2"
            type="textarea"
            placeholder="请输入兴趣"
        />
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
import {useStore} from "vuex";
import {ref} from "vue";
import { useI18n } from "vue-i18n";
import useNotification from "@/hooks/useNotification";
const { t } = useI18n();
const store = useStore();
const genders = [{value:'0',label:'男'},{value:'1',label:'女'}];
const gender = ref('');
const ages = [{value:23,label:23},{value:24,label:24},{value:25,label:25},{value:26,label:26},{value:27,label:27},{value:28,label:28},
  {value:29,label:29},{value:30,label:30},{value:31,label:31},{value:32,label:32},{value:33,label:33}];
const tages = [{value:3,label:3},{value:4,label:4},{value:5,label:5},{value:6,label:6},{value:7,label:7},{value:8,label:8},
  {value:9,label:9},{value:10,label:10},{value:11,label:11},{value:12,label:12},{value:13,label:13}];
const age = ref('');
const tage = ref('');
const nick = ref('');
const phone = ref('');
const interest = ref('');

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
