<template>
  <div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-light" id="sidenavAccordion">
      <!-- Sidebar Content -->
      <div class="sb-sidenav-menu">
        <div class="nav">
          <!-- Core Section -->
          <div class="sb-sidenav-menu-heading">Core</div>
          <router-link :to="{ name: 'Homeadmin' }" class="nav-link">
            <div class="sb-nav-link-icon">
              <i class="fas fa-tachometer-alt"></i>
            </div>
            Trang chủ
          </router-link>

          <router-link :to="{ name: 'banner' }" class="nav-link">
            <div class="sb-nav-link-icon">
              <i class="fa-solid fa-sliders"></i>
            </div>
            Quản lý Banner
          </router-link>

          
          <router-link :to="{ name: 'department' }" class="nav-link">
            <div class="sb-nav-link-icon">
              <i class="fa-solid fa-building"></i>
            </div>
            Quản lý khoa
          </router-link>

          <router-link :to="{ name: 'live' }" class="nav-link">
            <div class="sb-nav-link-icon">
              <i class="fa-solid fa-sliders"></i>
            </div>
            Quản lý lịch Livestream
          </router-link>

          <!-- Shipper Section -->
          <a
            class="nav-link collapsed"
            href="#"
            data-bs-toggle="collapse"
            data-bs-target="#collapseAccount3"
            aria-expanded="false"
            aria-controls="collapseAccount"
          >
            <div class="sb-nav-link-icon">
              <i class="fa-solid fa-circle-info"></i>
            </div>
            Thông tin tuyển sinh
            <div class="sb-sidenav-collapse-arrow">
              <i class="fas fa-angle-down"></i>
            </div>
          </a>
          <div
            class="collapse"
            id="collapseAccount3"
            data-bs-parent="#sidenavAccordion"
          >
            <nav class="sb-sidenav-menu-nested nav">
              <router-link :to="{ name: 'news' }" class="nav-link">
                Quản lý thông tin
              </router-link>
              <router-link :to="{ name: 'newsCxd' }" class="nav-link">
                Duyệt thông tin
              </router-link>
            </nav>
          </div>


          <!-- Đơn hàng Section -->
          <a
            class="nav-link collapsed"
            href="#"
            data-bs-toggle="collapse"
            data-bs-target="#collapseAccount"
            aria-expanded="false"
            aria-controls="collapseAccount"
          >
            <div class="sb-nav-link-icon">
              <i class="fa-solid fa-user"></i>
            </div>
            Tài khoản
            <div class="sb-sidenav-collapse-arrow">
              <i class="fas fa-angle-down"></i>
            </div>
          </a>
          <div
            class="collapse"
            id="collapseAccount"
            data-bs-parent="#sidenavAccordion"
          >
            <nav class="sb-sidenav-menu-nested nav">
              <router-link :to="{ name: 'department' }" class="nav-link">
                Quản lý tài khoản
              </router-link>
              <a class="nav-link" href="#">Cấp tài khoản</a>
            </nav>
          </div>

          <!-- Khuyến mãi Section -->
          <a
            class="nav-link collapsed"
            href="#"
            data-bs-toggle="collapse"
            data-bs-target="#collapseAccount1"
            aria-expanded="false"
          >
            <div class="sb-nav-link-icon">
              <i class="fa-solid fa-question"></i>
            </div>
            Quản lý câu hỏi
            <div class="sb-sidenav-collapse-arrow">
              <i class="fas fa-angle-down"></i>
            </div>
          </a>
          <div
            class="collapse"
            id="collapseAccount1"
            data-bs-parent="#sidenavAccordion"
          >
            <nav class="sb-sidenav-menu-nested nav">
              <a class="nav-link" href="#">Câu hỏi chưa có trả lời</a>
              <a class="nav-link" href="#">Câu hỏi thường gặp</a>
            </nav>
          </div>

          <!-- Báo cáo thống kê Section -->
          <a
            class="nav-link collapsed"
            href="#"
            data-bs-toggle="collapse"
            data-bs-target="#collapseAccount4"
            aria-expanded="false"
          >
            <div class="sb-nav-link-icon">
              <i class="fa-solid fas fa-chart-line"></i>
            </div>
            Báo cáo thống kê
            <div class="sb-sidenav-collapse-arrow">
              <i class="fas fa-angle-down"></i>
            </div>
          </a>
          <div
            class="collapse"
            id="collapseAccount4"
            data-bs-parent="#sidenavAccordion"
          >
            <nav class="sb-sidenav-menu-nested nav">
              <a class="nav-link" href="#">Doanh thu</a>
              <a class="nav-link" href="#">Tần suất giao hàng</a>
            </nav>
          </div>

          <!-- Đăng nhập Section -->
          <a class="nav-link" href="#">
            <div class="sb-nav-link-icon">
              <i class="fa-solid fa-user"></i>
            </div>
            Đăng nhập
          </a>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "SideBar",
};
</script>