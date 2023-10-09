<template>
  <div>
    <h1 class="text-center text-info">Quản lý danh sách các thông tin</h1>

    <table class="table table-bordered">
      <thead class="thead-dark">
        <tr>
          <th scope="col" class="text-center">id</th>
          <th scope="col" class="text-center">Hình ảnh</th>
          <th scope="col" class="text-center">Tiêu đề</th>
          <th scope="col" class="text-center">Nội dung</th>
          <th scope="col" class="text-center">Loại</th>
          <th scope="col" class="text-center">Ngày tạo</th>
          <th scope="col" class="text-center">Ngày cập nhật</th>
          <th scope="col" class="text-center">Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="news in responseData" :key="news.id">
          <td class="text-center">{{ news.id }}</td>
          <td style="width: 100px">
            <img :src="news.image" :alt="news.title" class="w-100" />
          </td>
          <td class="text-center">{{ news.title }}</td>
          <td class="text-center">{{ news.content }}</td>
          <td class="text-center">{{ news.type }}</td>
          <td class="text-center">{{ news.ngaytao }}</td>
          <td class="text-center">{{ news.ngaycapnhat }}</td>

          <td class="text-center">
            <router-link :to="{ name: 'newsDetail', params: { id: news.id } }">
              <button class="btn btn-success" title="Cập nhật">
                <i class="fa fa-edit"></i> Cập nhật
              </button>
            </router-link>

            <button
              class="btn btn-danger"
              style="margin-left: 10px"
              title="Xóa"
              @click="deleteNews(news.id)"
            >
              <i class="fa fa-trash"></i> Xóa
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link :to="{ name: 'addNews' }">
      <button class="btn btn-info mb-3" title="Thêm">
        <i class="fa-solid fa-plus"></i> Thêm thông tin
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
  name: "News",
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
        let location = endpoints["News"];
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
    async deleteNews(id) {
      try {
        const result = window.confirm("Bạn có chắc muốn xóa ko?");
        if (result) {
          // Gọi API RecycleBin với id của banner
          const response = await authApi().put(
            `${endpoints["RecycleBinNews"]}/${id}`
          );
          if (response.data === true) {
            alert("Xóa thành công");
            this.fetchData(1);
            console.log("News deleted successfully");
          } else {
            console.error("Error deleting news");
          }
        }
      } catch (error) {
        console.error("Error deleting news:", error);
      }
    },
    async fetchPage() {
      try {
        const response = await Apis.get(`${endpoints["News"]}/page`);
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
    
    
    