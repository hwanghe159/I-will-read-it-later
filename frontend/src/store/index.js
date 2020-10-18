import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    registerArticleFormPage: 1
  },
  getters: {
    registerArticleFormPage(state) {
      return state.registerArticleFormPage;
    }
  },
  mutations: {
    nextPage(state) {
      state.registerArticleFormPage++;
    },
    initializePage(state) {
      state.registerArticleFormPage = 1;
    }
  },
  actions: {},
  modules: {}
});
