<template>
  <v-flex>
    <v-text-field
      solo
      label="내용으로까지 검색하려면 엔터치세요!"
      v-model="search"
      append-icon="mdi-magnify"
    >
    </v-text-field>
    <v-list v-for="(listItem, index) in calData" :key="index">
      <v-list-item @click="goTo(listItem.url)">
        <v-list-item-content>
          <v-list-item-title class="text--primary">
            {{ listItem.title }}
          </v-list-item-title>
          <v-list-item-subtitle>
            {{ listItem.content.substr(0, 100) }}...
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-divider></v-divider>
    </v-list>
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
