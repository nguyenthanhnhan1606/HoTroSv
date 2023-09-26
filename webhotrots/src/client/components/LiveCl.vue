<template>
  <div class="container mt-3">
    <h1 class="text-center text-primary">LỊCH LIVESTREAM</h1>
    <div class="list-group" v-for="live in lives" :key="live.id">
      <div
        class="list-group-item list-group-item-action"
        style="background-color: whitesmoke"
      >
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-2 text-info">{{ live.title }}</h5>
          <div v-if="getUser === null || getUser.userRole !== 'ROLE_ADVISER'">
            <div v-if="isWithinTimeframe(live)">
              <small
                class="btn btn-info"
                @click="checkLogin(true, live.title, live.id)"
                >Đặt câu hỏi</small
              >
            </div>
            <div v-else>
              <small class="btn btn-secondary" :disable="true"
                >Quá thời gian</small
              >
            </div>
          </div>
          <div v-else>
            <router-link :to="{name:'liveclDetail', params:{id:live.id}}">
              <small class="btn btn-info"
                >Danh sách câu hỏi</small
              >
            </router-link>
          </div>
        </div>
        <p class="text-muted">
          Nội dung buổi live: <strong>{{ live.description }}</strong>
        </p>
        <p class="text-muted">
          Ngày live: <strong class="text-danger">{{ live.ngay }}</strong>
        </p>
        <p class="text-muted">
          Thời gian đặt câu hỏi cho trước buổi live stream:
          <strong class="text-danger">
            {{ live.thoigian }} đến {{ live.ngay }}</strong
          >
        </p>
        <p class="text-muted">
          Link live: <strong class="text-primary"> {{ live.path }}</strong>
        </p>
      </div>
    </div>
  </div>
  <div class="d-flex justify-content-center mb-5">
    <!--Pagination -->
    <nav class="my-2 pt-2">
      <ul class="pagination pagination-circle pg-info mb-0">
        <!--First-->
        <li class="page-item clearfix d-none d-md-block">
          <a href="#" class="page-link waves-effect" @click="setCurrentPage(1)">
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
          <a href="#" class="page-link waves-effect" @click="setCurrentPage(3)">
            Last
          </a>
        </li>
      </ul>
    </nav>
    <!--/Pagination -->
  </div>
  <div class="overlay" v-if="isQuestionFormVisible">
    <div class="question-form">
      <form @submit.prevent="submitQuestion">
        <div class="mb-3">
          <label for="exampleInputEmail1" class="form-label"
            ><strong class="text-success"
              >Hãy nhập câu hỏi cho buổi live:
              <strong class="text-primary">{{ myTitle }}</strong></strong
            ></label
          >
          <div v-if="errorMessage" class="alert alert-danger mt-3">
            {{ errorMessage }}
          </div>
          <textarea
            type="email"
            class="form-control"
            id="exampleInputEmail1"
            aria-describedby="emailHelp"
            v-model="question.content"
          ></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Gửi</button>
        <button
          class="btn btn-danger"
          style="margin-left: 20px"
          @click="showQuestionForm(flase, '', 0)"
        >
          Hủy
        </button>
      </form>
    </div>
  </div>
</template>
<script>
import Apis, { authApi, endpoints } from "@/configs/Apis";
import { mapGetters } from "vuex";
export default {
  nanme: "LiveClient",
  data() {
    return {
      lives: null,
      currentPage: 0,
      itemsPerPage: 0,
      searchQuery: "",
      isQuestionFormVisible: false,
      myTitle: "",
      question: {
        idUser: 0,
        content: "",
        idLive: 0,
      },
      errorMessage: "",
    };
  },
  computed: {
    ...mapGetters(["isAuth", "getUser"]),
  },
  mounted() {
    this.fetchData(1);
    this.fetchPage();
  },
  methods: {
    async fetchData(page) {
      if (page < 1) page = 1;
      try {
        let location = endpoints["Live"];
        if (this.searchQuery !== "") {
          location = `${location}?search=${this.searchQuery}`;
        } else if (this.searchQuery === "") {
          location = `${location}?page=${page}`;
        }
        let res = await Apis.get(location);
        this.lives = res.data;
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    },
    async fetchPage() {
      try {
        const response = await Apis.get(endpoints["PageLive"]);
        this.itemsPerPage = response.data;
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    },
    isWithinTimeframe(live) {
      const currentDate = new Date();
      const liveNgay = new Date(live.ngay);
      const liveThoigian = new Date(live.thoigian);
      return currentDate < liveNgay && currentDate > liveThoigian;
    },
    async submitQuestion() {
      try {
        if (this.errorMessage === "")
          this.errorMessage = "Bạn chưa nhập nội dung cho câu hỏi!!";
        else {
          const res = await authApi().post(
            endpoints["Question"],
            this.question
          );
          if (res.data === true) {
            alert("Bạn đã đặt câu hỏi thành công!!");
            this.isQuestionFormVisible = false;
            this.question.content = "";
            this.errorMessage = "";
            const formData = new FormData();
            formData.append("to", this.getUser.email);
            formData.append(
              "subject",
              "Bạn đã gửi câu hỏi cho livestream " + this.myTitle
            );
            formData.append("body", this.question.content);
            await authApi().post(endpoints["SendMail"], formData);
          } else alert("Bạn đặt câu hỏi thất bại!!");
        }
      } catch (error) {
        console.log("Lỗi ko thêm được câu hỏi " + error);
      }
    },
    setCurrentPage(page) {
      this.currentPage = page;
      this.searchQuery = "";
      this.fetchData(page);
    },
    showQuestionForm(flag, newTitle, id) {
      this.errorMessage = "";
      this.isQuestionFormVisible = flag;
      this.myTitle = newTitle;
      this.question.idLive = id;
      this.question.idUser = this.getUser.id;
      console.log(this.question.idLive);
    },
    checkLogin(flag, newTitle, id) {
      if (this.isAuth) {
        this.showQuestionForm(flag, newTitle, id);
      } else {
        const result = window.confirm(
          "Bạn chưa đăng nhập. Hãy đăng nhập để đặt câu hỏi!!"
        );
        if (result) {
          this.$router.push({ name: "login" });
        }
      }
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
<style scoped>
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Màu nền với độ mờ */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
}
.question-form {
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  max-width: 1000px; /* Điều chỉnh chiều rộng tối đa của form */
  width: 100%;
  text-align: center;
}

.question-form textarea {
  width: 100%;
  padding: 10px;
  margin: 5px 0;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}
</style>