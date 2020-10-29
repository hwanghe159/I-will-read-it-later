import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";

const ApiService = {
  init() {
    Vue.use(VueAxios, axios);
  },
  get(uri) {
    return Vue.axios.get(`${uri}`);
  },
  post(uri, params) {
    return Vue.axios.post(`${uri}`, params);
  }
};

ApiService.init();
export default ApiService;
