import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";

const ApiService = {
  init() {
    Vue.use(VueAxios, axios);
  },
  getMetadata(uri) {
    return Vue.axios.get("/metadata?url=" + uri);
  },
  addArticle(params) {
    return Vue.axios.post("/articles", params);
  }
};

ApiService.init();
export default ApiService;
