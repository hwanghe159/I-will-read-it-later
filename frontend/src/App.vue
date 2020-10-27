<template>
  <v-app id="inspire">
    <v-app id="inspire">
      <navigation-drawer />
      <v-main>
        <v-container>
          <v-row justify="center" align="center">
            <articles />
          </v-row>
        </v-container>
      </v-main>
      <register-article-button />
    </v-app>
    <snackbar />
  </v-app>
</template>

<script>
import NavigationDrawer from "./components/NavigationDrawer";
import RegisterArticleButton from "./components/RegisterArticleButton";
import Articles from "./components/Articles";
import Snackbar from "./components/Snackbar";

export default {
  name: "App",

  components: { NavigationDrawer, RegisterArticleButton, Articles, Snackbar },

  data: () => ({}),
  created() {
    this.$vuetify.theme.dark = true;
  }
};
</script>
