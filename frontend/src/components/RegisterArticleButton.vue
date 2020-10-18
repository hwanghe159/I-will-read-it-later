<template>
  <div>
    <v-btn bottom color="pink" dark fab fixed right @click="dialog = !dialog">
      <v-icon>mdi-plus</v-icon>
    </v-btn>

    <v-dialog v-model="dialog" width="800px">
      <register-article-form />
    </v-dialog>
  </div>
</template>

<script>
import RegisterArticleForm from "./RegisterArticleForm";

export default {
  name: "RegisterArticleButton",

  components: { RegisterArticleForm },
  data: () => ({
    dialog: false
  })
};
</script>

<style></style>
