<template>
  <div>
    <v-btn bottom color="pink" dark fab fixed right @click="dialog = !dialog">
      <v-icon>mdi-plus</v-icon>
    </v-btn>
    <v-dialog v-model="dialog" width="800px">
      <v-stepper v-model="registerArticleFormPage" alt-labels>
        <template>
          <v-stepper-header>
            <template>
              <v-stepper-step :step="1">
                추가할 URL을 입력해주세요
              </v-stepper-step>
              <v-divider />
              <v-stepper-step :step="2">
                정보를 확인해주세요
              </v-stepper-step>
            </template>
          </v-stepper-header>

          <v-stepper-items>
            <v-stepper-content :step="1">
              <v-container fluid>
                <v-card class="mb-12">
                  <v-textarea filled label="URL" auto-grow></v-textarea>
                </v-card>
              </v-container>
              <v-btn color="primary" @click="nextPage">
                다음
              </v-btn>

              <v-btn text @click="cancel">취소</v-btn>
            </v-stepper-content>

            <v-stepper-content :step="2">
              <v-card class="mb-12" color="grey lighten-1"></v-card>

              <v-btn color="primary" @click="addArticle">
                추가
              </v-btn>

              <v-btn text @click="cancel">취소</v-btn>
            </v-stepper-content>
          </v-stepper-items>
        </template>
      </v-stepper>
    </v-dialog>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";

export default {
  name: "RegisterArticleButton",
  components: {},
  data: () => ({
    dialog: false,
    url: ""
  }),
  computed: {
    ...mapGetters(["registerArticleFormPage"])
  },
  methods: {
    ...mapMutations(["nextPage", "initializePage"]),
    addArticle() {
      console.log("추가!!");
    },
    cancel() {
      this.dialog = false;
      this.initializePage();
    }
  },
  watch: {
    dialog(value) {
      if (!value) {
        this.initializePage();
      }
    }
  }
};
</script>

<style></style>
