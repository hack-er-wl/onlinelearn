<template>
  <div class="theme" @click="changeTheme()">
    <el-tooltip
        effect="dark"
        :content="isDark ? '默认主题' : '暗黑主题'"
        placement="bottom">
      <el-icon>
        <component :is="isDark ? 'Sunset' : 'MoonNight'"></component>
      </el-icon>
    </el-tooltip>
  </div>
</template>

<script setup>
import { useDark, useToggle } from "@vueuse/core";
import { useStore } from "vuex";

const store = useStore();

const isDark = useDark({
  selector: "html",
  attribute: "class",
  valueDark: "dark",
  valueLight: "light",
});
const toggleDark = useToggle(isDark);

function changeTheme() {
  const isDark = toggleDark();
  store.commit('setIsDark', isDark);
  if(document.getElementById('login_id') != null) {
    document.getElementById('login_id').style.backgroundColor = isDark ? "rgb(20,20,20)" : "rgb(247,247,247)";
  }
  if(document.getElementById('register_id') != null) {
    document.getElementById('register_id').style.backgroundColor = isDark ? "rgb(20,20,20)" : "rgb(247,247,247)";
  }
  if(document.getElementById('el-main') != null) {
    document.getElementById('el-main').style.backgroundColor = isDark ? "rgb(20,20,20)" : "rgb(247,247,247)";
  }
  for(let i=0;i<=5;i++){
    if(document.getElementById('replay'+i) != null) {
      document.getElementById('replay'+i).style.backgroundColor = isDark ? "rgb(20,20,20)" : "rgb(247,247,247)";
    }
  }
}
</script>

<style lang="scss" scoped>
.theme {
  cursor: pointer;
  height: 100%;
  padding: 0 5px;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;

  &:hover {
    background-color: #f6f6f6;
  }
}

[class="dark"] .theme {
  &:hover {
    background-color: #242424;
  }
}
</style>