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
                <v-textarea
                  v-model="url"
                  @keydown.enter.prevent="nextPage"
                  filled
                  label="URL"
                  auto-grow
                  rows="1"
                  row-height="15"
                ></v-textarea>
              </v-container>
              <v-btn color="primary" @click="nextPage">
                다음
              </v-btn>

              <v-btn text @click="cancel">취소</v-btn>
            </v-stepper-content>

            <v-stepper-content :step="2">
              <v-textarea
                v-model="url"
                readonly
                filled
                label="URL이 올바른지 확인해주세요."
                rows="1"
                height="100"
              ></v-textarea>
              <v-textarea
                v-model="title"
                @keydown.enter.prevent="addArticle"
                filled
                label="제목이 올바른지 확인해주세요."
                rows="1"
                height="100"
              ></v-textarea>
              <v-textarea
                v-model="content"
                @keydown.enter.prevent="addArticle"
                filled
                label="내용이 올바른지 확인해주세요. (검색할 때 사용됩니다.)"
                rows="1"
                height="300"
              ></v-textarea>

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
import ApiService from "../api/index";
import { mapMutations } from "vuex";

export default {
  name: "RegisterArticleButton",
  components: {},
  data: () => ({
    dialog: false,
    registerArticleFormPage: 1,
    url: "",
    title: "",
    content: ""
  }),
  computed: {},
  methods: {
    ...mapMutations(["showSnackbar"]),
    nextPage() {
      this.showSnackbar("🔎 URL을 확인하는 중입니다. 잠시만 기다려주세요.");
      ApiService.getMetadata(this.url)
        .then(({ data }) => {
          this.title = data.title;
          this.content = data.content;
          this.registerArticleFormPage++;
        })
        .catch(e => {
          this.showSnackbar("🙅‍♂️ " + e.response.data.message);
        });
    },
    addArticle() {
      const articleCreateRequest = {
        url: this.url,
        title: this.title,
        content: this.content
      };
      ApiService.addArticle(articleCreateRequest)
        .then(() => {
          alert("게시글이 추가되었습니다.");
          this.cancel();
        })
        .catch(e => {
          this.showSnackbar("🙅‍♂️ " + e.response.data.message);
        });
    },
    cancel() {
      this.dialog = false;
      this.url = "";
      this.title = "";
      this.content = "";
      this.registerArticleFormPage = 1;
    }
  },
  watch: {
    dialog(value) {
      if (!value) {
        this.url = "";
        this.title = "";
        this.content = "";
        this.registerArticleFormPage = 1;
      }
    }
  }
};
</script>

<style></style>
