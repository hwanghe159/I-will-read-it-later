import ApiService from "@/api";

const MetadataService = {
  get(url) {
    return ApiService.get("/metadata?url=" + url);
  }
};

export default MetadataService;
