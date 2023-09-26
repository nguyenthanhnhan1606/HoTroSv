<template>
  <nav
    class="sb-topnav navbar navbar-expand navbar-dark text-bg-primary bg-dark sb-topnav-fixed sb-topnav-menu-dark sb-topnav-search-dark sb-topnav-avatar-dark"
  >
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="#">QLGH-Admin</a>
    <!-- Sidebar Toggle Button -->
    <button
      class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0"
      id="sidebarToggle"
    >
      <i class="fas fa-bars"></i>
    </button>

    <!-- Navbar Search -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8">
          <div class="navbar-nav align-items-center">
            <form class="form-inline my-2 my-lg-0" id="searchForm">
              <div class="nav-item d-flex align-items-center">
                <i class="bx bx-search fs-4 lh-0"></i>
                <input
                  style="min-width: 300px"
                  type="text"
                  class="form-control border-0 shadow-none search-bar"
                  name="search"
                  placeholder="Search..."
                  aria-label="Search..."
                  @keydown.enter="search"
                />
                <button
                  class="btn btn-primary"
                  style="margin-left: -5px"
                  @submit.prevent
                >
                  Tìm
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- User Avatar -->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4 me-auto">
      <li class="nav-item">
        <a class="nav-link" href="#">
          <div class="d-flex align-items-center">
            <span class="text-info me-2" style="white-space: nowrap"
              >Chào, {{ getUser ? getUser.name:null }}
            </span>
            <!-- Replace "#" with the actual image URL -->
            <img :src="getUser ?getUser.avatar:null" width="40" class="rounded-circle" alt="Avatar" />
          </div>
        </a>
      </li>
    </ul>
  </nav>
</template>
  <script>
import { mapGetters } from 'vuex';


export default {
  name: "HeaderAdmin",
  computed:{
    ...mapGetters(["getUser"]),
  }
};
</script>
  