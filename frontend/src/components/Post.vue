<template>
  <v-card @click="goTo(post.url)" height="150px">
    <div class="d-flex flex-no-wrap">
      <v-img
        :src="post.imageSource"
        :aspect-ratio="1 / 1"
        max-width="150px"
      ></v-img>
      <div>
        <v-card-title class="headline" v-text="post.title"></v-card-title>
        <v-card-subtitle v-text="post.author"></v-card-subtitle>
      </div>
    </div>
  </v-card>
</template>

<script>
export default {
  name: "Post",
  props: {
    post: {
      type: Object,
      required: true
    }
  },

  methods: {
    goTo(url) {
      window.open(url, "_blank");
    }
  }
};
</script>

<style></style>
