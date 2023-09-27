<template>
  <div>
    <h1 class="text-center text-info">Quản lý danh sách người dùng</h1>

    <table class="table table-bordered">
      <thead class="thead-dark">
        <tr>
          <th scope="col" class="text-center">id</th>
          <th scope="col" class="text-center">Hình đại diện</th>
          <th scope="col" class="text-center">Tên</th>
          <th scope="col" class="text-center">Giới tính</th>
          <th scope="col" class="text-center">Ngày sinh</th>
          <th scope="col" class="text-center">Email</th>
          <th scope="col" class="text-center">SDT</th>
          <th scope="col" class="text-center">Vai trò</th>
          <th scope="col" class="text-center">Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="u in responseData" :key="u.id">
          <td class="text-center">{{ u.id }}</td>
          <td style="width: 100px">
            <img
              :src="u.avatar"
              :alt="u.name"
              class="w-100"
              style="height: 50px"
            />
          </td>
          <td class="text-center">{{ u.name }}</td>
          <td class="text-center">{{ u.gioitinh }}</td>
          <td class="text-center">{{ u.ngaysinh }}</td>
          <td class="text-center">{{ u.email }}</td>
          <td class="text-center">{{ u.sdt }}</td>
          <td class="text-center" v-if="u.userRole === 'ROLE_USER'">
            Người dùng
          </td>
          <td class="text-center" v-else>Người cố vấn</td>

          <td class="text-center">
            <router-link :to="{ name: 'userDetail', params: { id: u.id } }">
              <button class="btn btn-success" title="Cập nhật">
                <i class="fa fa-edit"></i> Cập nhật
              </button>
            </router-link>

            <button
              class="btn btn-danger"
              style="margin-left: 10px"
              title="Xóa"
              @click="deleteUser(u.id)"
            >
              <i class="fa fa-trash"></i> Xóa
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link :to="{ name: 'addUserAdviser' }">
      <button class="btn btn-info" title="Thêm">
        <i class="fa-solid fa-plus"></i> Thêm tài khoản tư vấn
      </button>
    </router-link>
    <div class="d-flex justify-content-center">
      <!--Pagination -->
      <nav class="my-2 pt-2">
        <ul class="pagination pagination-circle pg-info mb-0">
          <!--First-->
          <li class="page-item clearfix d-none d-md-block">
            <a
              href="#"
              class="page-link waves-effect"
              @click="setCurrentPage(1)"
            >
              First
            </a>
          </li>
          <!--Arrow left-->
          <li class="page-item">
            <a
              href="#"
              class="page-link waves-effect"
              aria-label="Previous"
              @click="setCurrentPage(currentPage - 1)"
            >
              <span aria-hidden="true">«</span>
              <span class="sr-only"> Previous </span>
            </a>
          </li>
          <!--Numbers-->
          <li v-for="page in itemsPerPage" :key="page" class="page-item">
            <a
              href="#"
              class="page-link waves-effect"
              @click="setCurrentPage(page)"
              >{{ page }}</a
            >
          </li>
          <li class="page-item">
            <a
              href="#"
              class="page-link waves-effect"
              aria-label="Next"
              @click="setCurrentPage(currentPage + 1)"
            >
              <span aria-hidden="true">»</span>
              <span class="sr-only">Next</span>
            </a>
          </li>
          <li class="page-item clearfix d-none d-md-block">
            <a
              href="#"
              class="page-link waves-effect"
              @click="setCurrentPage(3)"
            >
              Last
            </a>
          </li>
        </ul>
      </nav>
      <!--/Pagination -->
    </div>
  </div>
</template>
    
    <script>
import Apis, { authApi } from "@/configs/Apis.js";
import { endpoints } from "@/configs/Apis.js";
export default {
  name: "ListUser",
  data() {
    return {
      responseData: null,
      currentPage: 0,
      itemsPerPage: 0,
      searchQuery: "",
    };
  },
  mounted() {
    this.fetchData(1);
    this.fetchPage();
  },
  methods: {
    async fetchData(page) {
      if (page < 1) page = 1;
      try {
        let location = endpoints["ListUser"];
        if (this.searchQuery !== "") {
          location = `${location}?search=${this.searchQuery}`;
        } else if (this.searchQuery === "") {
          location = `${location}?page=${page}`;
        }
        let res = await Apis.get(location);
        this.responseData = res.data;
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    },
    async deleteUser(id) {
      try {
        // Gọi API RecycleBin với id của u
        const response = await authApi().put(
          `${endpoints["RecycleBinUser"]}/${id}`
        );
        if (response.data) {
          alert("Xóa thành công");
          this.fetchData(1);
          console.log("user deleted successfully");
        } else {
          console.error("Error deleting user");
        }
      } catch (error) {
        console.error("Error deleting user:", error);
      }
    },
    async fetchPage() {
      try {
        const response = await Apis.get(`${endpoints["ListUser"]}/page`);
        this.itemsPerPage = response.data;
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    },
    setCurrentPage(page) {
      this.currentPage = page;
      this.searchQuery = "";
      this.fetchData(page);
    },
  },
  watch: {
    "$route.query.search": {
      handler(newSearchQuery) {
        this.searchQuery = newSearchQuery || "";
        this.fetchData(1);
      },
      // immediate: true, // Thực hiện ngay khi component được khởi tạo
    },
  },
};
</script>
    
    
    