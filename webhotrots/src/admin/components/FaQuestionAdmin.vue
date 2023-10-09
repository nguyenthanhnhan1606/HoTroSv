<template>
  <div>
    <h1 class="text-center text-info">Danh sách câu hỏi thường gặp</h1>

    <table class="table table-bordered">
      <thead class="thead-dark">
        <tr>
          <th scope="col" class="text-center">id</th>
          <th scope="col" class="text-center">Câu hỏi</th>
          <th scope="col" class="text-center">Câu trả lời</th>
          <th scope="col" class="text-center">Thao tác</th>

        </tr>
      </thead>
      <tbody>
        <tr v-for="q in questions" :key="q.id">
          <td class="text-center">{{ q.id }}</td>

          <td class="text-center">{{ q.question }}</td>
          <td class="text-center">{{ q.answers }}</td>
          <td class="text-center">
            <router-link :to="{ name: 'faqDetail', params: { id: q.id } }">
              <button class="btn btn-success" title="Cập nhật">
                <i class="fa fa-edit"></i> Cập nhật
              </button>
            </router-link>

            <button
              class="btn btn-danger"
              style="margin-left: 10px"
              title="Xóa"
              @click="deleteFAQ(q.id)"
            >
              <i class="fa fa-trash"></i> Xóa
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link :to="{ name: 'addFaq' }">
      <button class="btn btn-info" title="Thêm">
        <i class="fa-solid fa-plus"></i> Thêm
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
import Apis, { authApi, endpoints } from "@/configs/Apis";
export default {
  name: "FaQuestionAdmin",
  data() {
    return {
      questions: null,
      currentPage: 0,
      itemsPerPage: 0,
      searchQuery: "",
    };
  },
  mounted() {
    this.fetchData(1);
    this.fetchPageFAQ();
  },
  methods: {
    async fetchData(page) {
      if (page < 1) page = 1;
      try {
        let location = endpoints["FaQuestion"];
        if (this.searchQuery !== "") {
          location = `${location}?search=${this.searchQuery}`;
        } else if (this.searchQuery === "") {
          location = `${location}?page=${page}`;
        }
        let res = await Apis.get(location);
        this.questions = res.data;
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    },
    async deleteFAQ(id) {
      try {
        const result = window.confirm("Bạn có chắc muốn xóa ko?");
        if (result) {
          const response = await authApi().delete(
            `${endpoints["FaQuestion"]}/${id}`
          );
          if (response.status === 204) {
            alert("Xóa thành công");
            this.fetchData(1);
            console.log("Live deleted successfully");
          } else {
            console.error("Error deleting live");
          }
        }
      } catch (error) {
        console.error("Error deleting live:", error);
      }
    },
    async fetchPageFAQ() {
      try {
        const response = await Apis.get(endpoints["PageFAQ"]);
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
