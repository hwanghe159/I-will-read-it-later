<template>
  <v-flex>
    <v-text-field
      solo
      label="내용으로까지 검색하려면 엔터치세요!"
      v-model="query"
      append-icon="mdi-magnify"
      @keyup.enter="search"
    >
    </v-text-field>

    <v-col v-for="(article, index) in calData" :key="index" cols="12">
      <post :post="article"></post>
    </v-col>
    <br />
    <v-pagination v-model="curPageNum" :length="numOfPages"> </v-pagination>
  </v-flex>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
import { FETCH_ARTICLES, SEARCH_ARTICLES } from "../store/shared/actionTypes";
import { SHOW_SNACKBAR } from "../store/shared/mutationTypes";
import Post from "../components/Post";

export default {
  name: "Articles",
  components: {
    Post
  },

  data: () => ({
    query: "",
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
          return data.title.toLowerCase().includes(this.query.toLowerCase());
        })
        .slice(0);

      return this.searchData.slice(this.startOffset, this.endOffset);
    }
  },
  methods: {
    ...mapActions([FETCH_ARTICLES, SEARCH_ARTICLES]),
    ...mapMutations([SHOW_SNACKBAR]),
    search() {
      this.searchArticles(this.query);
    }
  }
};
</script>

<style></style>
