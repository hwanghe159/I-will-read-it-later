import Vue from "vue";
import Vuex from "vuex";
import Snackbar from "./modules/snackbar";
import Articles from "./modules/articles";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: { Snackbar, Articles }
});
