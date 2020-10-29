import ArticleService from "../../api/modules/article";
import { SET_ARTICLES, ADD_VALID_ARTICLE } from "../shared/mutationTypes";
import { FETCH_ARTICLES } from "../shared/actionTypes";

const state = {
  allArticles: []
};

const getters = {
  allArticles(state) {
    return state.allArticles;
  }
};

const mutations = {
  [SET_ARTICLES](state, articles) {
    state.allArticles = articles;
  },
  [ADD_VALID_ARTICLE](state, payload) {
    state.allArticles.push(payload);
  }
};

const actions = {
  async [FETCH_ARTICLES]({ commit }) {
    return ArticleService.getArticles().then(({ data }) => {
      commit(SET_ARTICLES, data);
    });
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
