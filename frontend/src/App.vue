<template>
  <v-app id="inspire">
    <v-app id="inspire">
      <navigation-drawer />
      <v-main>
        <v-container class="fill-height">
          <v-row justify="center" align="center">
            <v-col class="shrink"> </v-col>
          </v-row>
        </v-container>
      </v-main>
      <register-article-button />
    </v-app>
  </v-app>
</template>

<script>
import NavigationDrawer from "./components/NavigationDrawer";
import RegisterArticleButton from "./components/RegisterArticleButton";

export default {
  name: "App",

  components: { NavigationDrawer, RegisterArticleButton },

  data: () => ({}),
  created() {
    this.$vuetify.theme.dark = true;
  }
};
</script>
