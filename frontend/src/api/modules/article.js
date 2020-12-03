import ApiService from "@/api";

const ArticleService = {
  getArticles() {
    return ApiService.get("/articles");
  },
  searchArticles(query) {
    return ApiService.get("/articles?query=" + query);
  },
  addArticle(params) {
    return ApiService.post("/articles", params);
  }
};

export default ArticleService;
