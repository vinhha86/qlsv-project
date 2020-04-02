<template>
  <div id="app">
    <app-nav-bar></app-nav-bar>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
import Navbar from './components/common/Navbar';

export default {
  components: {
    'appNavBar': Navbar
  }
}
</script>

<style>
</style>
