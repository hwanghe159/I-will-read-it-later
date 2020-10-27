const state = {
  isShow: false,
  message: ""
};

const getters = {
  isShow(state) {
    return state.isShow;
  },
  message(state) {
    return state.message;
  }
};

const mutations = {
  showSnackbar(state, message) {
    state.isShow = !state.isShow;
    state.message = message;
  },
  hideSnackbar(state) {
    state.isShow = !state.isShow;
  }
};

export default {
  state,
  getters,
  mutations
};
