<template>
  <el-dialog title="回复评论">
    <el-form>
      <el-form-item label="内容">
        <el-input
            v-model="ReplyForm.content"
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
          回复
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup>
import {reactive,toRaw} from "vue";
import { useI18n } from "vue-i18n";
import useNotification from "@/hooks/useNotification";
import {getUser} from "@/api/user";
import {useStore} from "vuex";
import {useRoute} from "vue-router";
import qs from "qs";
const { t } = useI18n();
const store = useStore();
const route = useRoute();
const course = qs.parse(route.query.key);

const ReplyForm = reactive({
  content: "",
});

async function postAssessConfirm() {
  await store.dispatch("handleAddReply", toRaw({
    userid: getUser().user_id,
    assessid:store.state.layoutStore.assessid,
    acceptid: store.state.layoutStore.acceptid,
    content: ReplyForm.content,
  })).then((res) => {
    console.log(res);
    if (res) {
      useNotification('success', '系统通知', t("replayAssessSuccess"));
      store.state.layoutStore.isHide = false;
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
    } else {
      useNotification('success', '系统通知', t("replayAssessSuccess"));
      store.state.layoutStore.isHide = true;
    }
  })
}
function postAssessCancel(){
  store.state.layoutStore.isHide = false;
  useNotification('success','系统通知',t("replayAssessCancel"));
}
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
