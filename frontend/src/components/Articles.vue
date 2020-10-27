<template>
  <v-flex>
    <v-text-field label="내용으로까지 검색하려면 엔터치세요!" v-model="search">
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
import ApiService from "../api/index";

export default {
  name: "Articles",

  data: () => ({
    search: "",
    listData: [],
    searchData: [],
    dataPerPage: 8,
    curPageNum: 1,
    curSelectIndex: 0
  }),
  created() {
    ApiService.getArticles()
      .then(({ data }) => {
        this.listData = data;
      })
      .catch(() => {
        alert("알 수 없는 에러가 발생했습니다. 다시 시도해주세요.");
      });
  },
  computed: {
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
      this.searchData = this.listData
        .filter(data => {
          return data.title.includes(this.search);
        })
        .slice(0);

      return this.searchData.slice(this.startOffset, this.endOffset);
    }
  },
  methods: {
    goTo(url) {
      window.open(url, "_blank");
    }
  }
};
</script>

<style></style>
