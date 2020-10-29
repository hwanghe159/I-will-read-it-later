import ApiService from "@/api";

const ArticleService = {
  getArticles() {
    return ApiService.get("/articles");
  },
  addArticle(params) {
    return ApiService.post("/articles", params);
  }
};

export default ArticleService;
