<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <router-link to="/" class="navbar-brand">Hỗ trợ tuyển sinh</router-link>

      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link to="/" class="nav-link active">Trang chủ</router-link>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Danh sách khoa
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown" >
              <li v-for="d in departments" :key="d.id"> 
                <router-link :to="{name:'depDetailCl',params:{id:d.id}}" class="dropdown-item">
                    {{ d.tenkhoa }}
                </router-link>
              </li>
              <!-- <li><a class="dropdown-item" href="#">Another action</a></li>
              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item" href="#">Something else here</a></li> -->
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown1"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Tuyển sinh
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown1">
              <li>
                <router-link :to="{name:'newsdh'}" class="dropdown-item"
                  >Đại học
                </router-link>
              </li>
              <li><hr class="dropdown-divider" /></li>
              <li><router-link :to="{name:'newssdh'}" class="dropdown-item"
                  >Sau đại học
                </router-link>
              </li>
              <li><hr class="dropdown-divider" /></li>
              <li><router-link :to="{name:'newstx'}" class="dropdown-item"
                  >Đào tạo từ xa
                </router-link></li>
              <li><hr class="dropdown-divider" /></li>
              <li><router-link :to="{name:'newstt'}" class="dropdown-item"
                  >Đào tạo trực tuyến
                </router-link></li>
            </ul>
          </li>
            <li class="nav-item">
            <a
              class="nav-link active"
              href="#"
              >
              Lịch Live <i class="fa-regular fa-circle-dot" style="color: #f00f0f;"></i></a
            >
          </li>
          <li class="nav-item">
            <router-link :to="{name:'faquestion'}" class="nav-link active">Câu hỏi thường gặp</router-link>
          </li>
          <!-- <li class="nav-item">
            <a
              class="nav-link disabled"
              href="#"
              tabindex="-1"
              aria-disabled="true"
              >Disabled</a
            >
          </li> -->
          <!-- <li class="nav-item " v-if="isAuth === false">
            <div class="d-flex align-items-center">
              <router-link to="/login" class="nav-link active">Đăng nhập</router-link>
              <router-link to="/register" class="nav-link active">Đăng ký</router-link>
              
            </div>
            
          </li>
          <li class="nav-item" v-else>
            <div class="d-flex align-items-center">
              <span class="ml-2">Chào, {{ getUser.name }}</span>
              <router-link to="/login" @click="logout()" class="nav-link active"
                >Logout</router-link
              >
              
            </div>
          </li> -->
        </ul>
        <form class="d-flex" @submit.prevent="search">
          <input
            class="form-control me-2"
            type="search"
            name="search"
            placeholder="Search"
            aria-label="Search"
            v-model="searchQuery"
          />
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>

        <ul class="navbar-nav" style="margin-right: 5px; margin-left: 5px">
          <!-- Sử dụng lớp ms-auto để đẩy sang bên phải -->
          <li class="nav-item" v-if="isAuth === false">
            <router-link to="/login" class="nav-link active"
              >Đăng nhập</router-link
            >
          </li>
          <li class="nav-item" v-if="isAuth === false">
            <router-link to="/register" class="nav-link active"
              >Đăng ký</router-link
            >
          </li>
          <li class="nav-item" v-else>
            <div class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown2"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Chào, {{ getUser.name }}
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown2">
                <router-link to="/login" class="dropdown-item">
                  <img :src="getUser.avatar" width="40" alt="Avatar" />
                  <span className="text-info">Thông tin user</span></router-link
                >
                <li><hr class="dropdown-divider" /></li>
                <router-link
                  to="/login"
                  @click="logout()"
                  class="dropdown-item text-center"
                  >Logout</router-link
                >
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
  
  <script>
import Apis, { endpoints } from '@/configs/Apis';
import { mapGetters } from "vuex";
export default {
  name: "MyHeader",
  data() {
    return {
      searchQuery: "", // Thêm dữ liệu tìm kiếm
      departments:null,

    };
  },
  mounted() {
    this.fetchDataDep();
  },
  computed: {
    ...mapGetters(["isAuth", "getUser"]),
  },
  methods: {
    logout() {
      this.$store.dispatch("logout");
    },
    search() {
      this.$router.replace({ query: { search: this.searchQuery } });
    },
    async fetchDataDep() {
      await Apis.get(endpoints["Department"])
        .then((response) => {
          this.departments = response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
  },
  watch: {
    searchQuery: {
      handler(newSearchQuery) {
        this.searchQuery.replace({ query: { search: newSearchQuery } });
      },
    },
  },
};
</script>
  