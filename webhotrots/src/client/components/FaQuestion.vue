<template>
  <div v-if="this.questions">
    <div class="container mt-2">
      <h1 class="text-center text-danger">Câu hỏi thường gặp</h1>
      <div style="min-height: 600px">
        <ul class="list-group">
          <li
            class="list-group-item d-flex flex-column"
            style="background-color: whitesmoke"
            v-for="(question, index) in questions"
            :key="question.id"
          >
            <div class="d-flex justify-content-between align-items-center mb-2">
              <h6 class="mb-0">
                Câu hỏi {{ index + 1 }}: {{ question.question }}
              </h6>
              <button
                @click="toggleAnswer(question)"
                class="btn btn-primary btn-sm"
              >
                {{
                  question.showAnswer
                    ? "Ẩn câu trả lời"
                    : "Hiển thị câu trả lời"
                }}
              </button>
            </div>
            <h6 v-if="question.showAnswer">
              Trả lời:
              <strong class="text-success">{{ question.answers }}</strong>
            </h6>
          </li>
        </ul>
        <!--Bottom Table UI-->
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
    </div>
  </div>
  <div v-else class="text-center bg-info h2 pb-2" style="margin: 0">
    Không có câu hỏi nào!!
  </div>
</template>

  <script>
import Apis, { endpoints } from "@/configs/Apis";

export default {
  name: "FaQuestion",
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
        this.questions = res.data.map((question) => ({
          ...question,
          showAnswer: false,
        }));
      } catch (error) {
        console.error("Error fetching data:", error);
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
    toggleAnswer(question) {
      question.showAnswer = !question.showAnswer;
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
  