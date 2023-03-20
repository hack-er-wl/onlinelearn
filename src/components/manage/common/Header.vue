<template>
  <div class="header">
    <!-- 右侧区域-->
    <div class="header_left">
      <el-button @click="handleChange()" link :icon="store.state.layoutStore.icon?'Expand':'Fold'"/>
    </div>
    <!-- 左侧区域-->
    <div class="header_right">
      <Theme />
      <Language />
      <UserDropDown />
    </div>
  </div>
</template>

<script setup>
import { useStore } from "vuex";
import Language from "../../user/common/Language.vue";
import UserDropDown from "../../manage/common/UserDropDown.vue";
import Theme from "../../user/common/Theme.vue";
import {useI18n} from "vue-i18n";
const store = useStore();
const { t } = useI18n();
function handleChange(){
  store.state.layoutStore.isCollapse = !store.state.layoutStore.isCollapse;
  store.state.layoutStore.icon = !store.state.layoutStore.icon;
  if(store.state.layoutStore.isCollapse){
    document.getElementById('el-aside').style.width = "4.15%";
  }else{
    document.getElementById('el-aside').style.width = "16.4%";
  }
}
</script>

<style lang="scss" scoped>
@import "../../../style/mixin";

.header {
  display: flex;
  justify-content: space-between;
  width: 100%;
  flex-wrap: wrap;
  position: relative;
  margin: 0 15px 0 15px;
  .header_left{
    .btn{
      cursor: pointer;
    }
  }
  .header_right {
    display: flex;
    align-items: center;
  }
  .el-menu--horizontal{
    border-bottom:none;
  }
}
</style>