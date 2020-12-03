import ArticleService from "../../api/modules/article";
import { ADD_VALID_ARTICLE, SET_ARTICLES } from "../shared/mutationTypes";
import { ADD_ARTICLE, FETCH_ARTICLES } from "../shared/actionTypes";

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
  },
  async [ADD_ARTICLE]({ commit }, article) {
    return ArticleService.addArticle(article).then(() => {
      commit(ADD_VALID_ARTICLE, article);
    });
  }
};

export default {
  state,
  getters,
  mutations,
  actions
};
