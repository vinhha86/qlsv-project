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
  .form-control:focus {
    box-shadow: none;
  }
  .form-control.is-invalid:focus {
    box-shadow: none;
  }
  .page-link:focus {
    box-shadow: none;
  }
  .btn.focus, .btn:focus {
    box-shadow: none;
  }
  .btn-outline-primary.focus, .btn-outline-primary:focus {
    box-shadow: none;
  }
  .btn-outline-secondary.focus, .btn-outline-secondary:focus {
    box-shadow: none;
  }
</style>
