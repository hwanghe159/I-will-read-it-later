<template>
  <v-flex>
    <v-text-field
      solo
      label="내용으로까지 검색하려면 엔터치세요!"
      v-model="search"
      append-icon="mdi-magnify"
    >
    </v-text-field>

    <v-col v-for="(listItem, index) in calData" :key="index" cols="12">
      <v-card @click="goTo(listItem.url)">
        <div class="d-flex flex-no-wrap">
          <v-img
            :src="listItem.imageSource"
            :aspect-ratio="1 / 1"
            max-width="150px"
          ></v-img>
          <div>
            <v-card-title
              class="headline"
              v-text="listItem.title"
            ></v-card-title>
            <v-card-subtitle v-text="listItem.author"></v-card-subtitle>
          </div>
        </div>
      </v-card>
    </v-col>
    <br />
    <v-pagination v-model="curPageNum" :length="numOfPages"> </v-pagination>
  </v-flex>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import { FETCH_ARTICLES } from "../store/shared/actionTypes";
import { SHOW_SNACKBAR } from "../store/shared/mutationTypes";

export default {
  name: "Articles",

  data: () => ({
    search: "",
    searchData: [],
    dataPerPage: 8,
    curPageNum: 1,
    curSelectIndex: 0
  }),
  async created() {
    try {
      await this.fetchArticles();
    } catch (e) {
      this.showSnackbar("게시글을 불러오는 중에 오류가 발생하였습니다.");
    }
  },
  computed: {
    ...mapGetters(["allArticles"]),
    startOffset() {
      return (this.curPageNum - 1) * this.dataPerPage;
    },
    endOffset() {
      return this.startOffset + this.dataPerPage;
    },
    numOfPages() {
      return Math.ceil(this.searchData.length / this.dataPerPage);
    },
    calData() {
      // eslint-disable-next-line vue/no-side-effects-in-computed-properties
      this.searchData = this.allArticles
        .filter(data => {
          return data.title.toLowerCase().includes(this.search.toLowerCase());
        })
        .slice(0);

      return this.searchData.slice(this.startOffset, this.endOffset);
    }
  },
  methods: {
    ...mapActions([FETCH_ARTICLES]),
    ...mapMutations([SHOW_SNACKBAR]),
    goTo(url) {
      window.open(url, "_blank");
    }
  }
};
</script>

<style></style>
