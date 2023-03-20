<template>
  <el-dialog title="发布测试">
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
                :label="item.label+10"
                :value="item.value+10"
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
                :label="item.label-10"
                :value="item.value-10"
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
const ages = [{value:13,label:13},{value:14,label:14},{value:15,label:15},{value:16,label:16},{value:17,label:17},{value:18,label:18},
  {value:19,label:19},{value:20,label:20},{value:21,label:21},{value:22,label:22},{value:23,label:23}];
const tages = [{value:13,label:13},{value:14,label:14},{value:15,label:15},{value:16,label:16},{value:17,label:17},{value:18,label:18},
  {value:19,label:19},{value:20,label:20},{value:21,label:21},{value:22,label:22},{value:23,label:23}];
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
