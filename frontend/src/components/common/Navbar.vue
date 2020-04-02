<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light p-0 pl-3 pr-3 mb-2">
    <router-link class="navbar-brand" to="/">NIIT</router-link>
    <button
      class="navbar-toggler"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent">
      <ul class="navbar-nav">
          <router-link class="nav-item" tag="li" active-class="active" to="/" exact>Trang chủ</router-link>
          <router-link class="nav-item" tag="li" active-class="active" to="/student">Hồ sơ sinh viên</router-link>
          <router-link class="nav-item" tag="li" active-class="active" to="/class">Quản lý lớp</router-link>
          <router-link class="nav-item" tag="li" active-class="active" to="/schedule">Lịch học</router-link>
          <router-link class="nav-item" tag="li" active-class="active" to="/course">Khóa học</router-link>
      </ul>
    </div>

    <div>
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarDropdown"
            role="button"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >Admin</a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="#">Thông tin</a>
            <a class="dropdown-item" href="#">Đổi mật khẩu</a>
            <a class="dropdown-item" href="#">Đăng xuất</a>
          </div>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {};
</script>

<style scoped>
  li.nav-item {
    padding: 8px 20px 8px 20px;
    cursor: pointer;
  }

  li.nav-item.active {
    position: relative;
    border-top: solid 2px blue;
  }

  li.nav-item.active::after {
    content: '';
    position: absolute;
    left: 44%;
    top: 0%;
    width: 0;
    height: 0;
    border-left: 6px solid transparent;
    border-right: 6px solid transparent;
    border-top: 6px solid blue;
    clear: both;
  }

  .dropdown-toggle::after {
    margin-left: .4em;
    vertical-align: .155em
  }
</style>