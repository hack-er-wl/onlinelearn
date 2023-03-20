<template>
  <el-dialog title="发表评论">
    <el-form>
      <el-form-item label="评分">
        <el-rate
            v-model="AssessForm.rate"
            :colors="colors"
            text-color='#99A9BF'
            show-score
            allow-half
        />
      </el-form-item>
      <el-form-item label="内容">
        <el-input
            v-model="AssessForm.content"
            :rows="2"
            type="textarea"
            placeholder="请输入内容"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="postAssessCancel()">取消</el-button>
        <el-button type="primary" @click="postAssessConfirm()">
          发表
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup>
import {reactive, ref, toRaw} from "vue";
import { useI18n } from "vue-i18n";
import useNotification from "@/hooks/useNotification";
import {useRoute} from "vue-router";
import qs from "qs";
import {useStore} from "vuex";
import {getUser} from "@/api/user";
const store = useStore();
const { t } = useI18n();
const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900']);
const route = useRoute();
const course = qs.parse(route.query.key);

const AssessForm = reactive({
  rate: 0,
  content: "",
});

async function postAssessConfirm(){
  await store.dispatch("handleAddAssess", toRaw({userid:getUser().user_id,courseid:course.course_id,content:AssessForm.content,rate:AssessForm.rate})).then((res)=>{
    console.log(res);
    if(res) {
      useNotification('success', '系统通知', t("postAssessSuccess"));
      store.state.layoutStore.Hide = false;
      if(store.state.layoutStore.assesses.length == 0){
        store.dispatch("handleQueryAssess", toRaw({courseid:course.course_id})).then((assesses)=>{
          console.log(assesses)
          for(let i in assesses){
            store.dispatch("handleQueryReply", toRaw({assessid:assesses[i].assess_id})).then((replies)=>{
              store.state.layoutStore.assesses.push({assess:assesses[i],replies:replies});
            })
          }
        })
      }else{
        store.state.layoutStore.assesses = [];
        store.dispatch("handleQueryAssess", toRaw({courseid:course.course_id})).then((assesses)=>{
          console.log(assesses)
          for(let i in assesses){
            store.dispatch("handleQueryReply", toRaw({assessid:assesses[i].assess_id})).then((replies)=>{
              store.state.layoutStore.assesses.push({assess:assesses[i],replies:replies});
            })
          }
        })
      }
    }else {
      useNotification('error', '系统通知', t("postAssessSuccess"));
      store.state.layoutStore.Hide = true;
    }
  })
}
function postAssessCancel(){
  store.state.layoutStore.Hide = false;
  useNotification('success','系统通知', t("postAssessCancel"),);
}
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
