<template>
  <div>
    <h1 class="text-center text-info">Quản lý khoa</h1>

    <table class="table table-bordered">
      <thead class="thead-dark">
        <tr>
          <th scope="col" class="text-center">ID</th>
          <th scope="col" class="text-center">Tên khoa</th>
          <th scope="col" class="text-center">Giới thiệu</th>
          <th scope="col" class="text-center">Chương trình đào tạo</th>
          <th scope="col" class="text-center">Trung bình điểm 5 năm</th>
          <th scope="col" class="text-center">Website</th>
          <th scope="col" class="text-center">Video</th>
          <th scope="col" class="text-center">Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="k in responseData" :key="k.id">
          <td class="text-center">{{ k.id }}</td>
          <td class="text-center">{{ k.tenkhoa }}</td>
          <td class="text-center">
            <span v-b-tooltip.hover :title="k.mota">
              {{ k.mota.substring(0, 20) }}{{ k.mota.length > 50 ? "..." : "" }}
            </span>
          </td>
          <td class="text-center">
            <span v-b-tooltip.hover :title="k.motaCTDT">
              {{ k.motaCTDT.substring(0, 20)
              }}{{ k.motaCTDT.length > 50 ? "..." : "" }}
            </span>
          </td>
          <td class="text-center">{{ k.trungbinhdiem }}</td>
          <td class="text-center">
            <a :href="k.website" target="_blank" class="nav-link">{{
              k.website
            }}</a>
          </td>
          <td class="text-center">
            <div v-if="k.video">
              <!-- <i class="fas fa-video"></i> -->
              <video width="80" height="40" controls>
                <source :src="k.video" type="video/mp4" />
              </video>
            </div>
            <div v-else>Không có video nào</div>
          </td>
          <td class="text-center">
            <router-link
              :to="{ name: 'departmentDetail', params: { id: k.id } }"
            >
              <button class="btn btn-success" title="Cập nhật">
                <i class="fa fa-edit"></i> Cập nhật
              </button>
            </router-link>
            <button
              class="btn btn-danger"
              style="margin-left: 10px"
              title="Xóa"
              @click="deleteDep(k.id)"
            >
              <i class="fa fa-trash"></i> Xóa
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link :to="{ name: 'addDep' }">
      <button class="btn btn-info" title="Thêm">
        <i class="fa-solid fa-plus"></i> Thêm khoa
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
  name: "Department",
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
        let location = endpoints["Department"];
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
    async deleteDep(id) {
      try {
        const result = window.confirm("Bạn có chắc muốn xóa ko?");
        if (result) {
          const response = await authApi().put(
            `${endpoints["RecycleBinDep"]}/${id}`
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
        const response = await Apis.get(`${endpoints["Department"]}/page`);
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
  