<template>
  <!--Section: Newsfeed-->
  <div class="container mb-5" v-if="live">
    <div
      class="card mt-3"
      style="max-width: 100%; background-color: whitesmoke"
    >
      <h1 class="text-center text-success">Danh sách câu hỏi</h1>
      <div class="card-body">
        <div class="row border-bottom pb-2">
          <div class="col-9">
            <h1 class="mb-2">
              <strong class="text-info">{{ live.title }}</strong>
            </h1>
            <p class="mb-0">{{ live.description }}</p>
            <p class="mb-0 mt-2">Link live: <strong class="text-primary">{{ live.path }}</strong></p>
            <p class="mb-0 mt-2">
              <small class="text-muted">Ngày live: {{ live.ngay }}</small>
            </p>
          </div>
        </div>
      </div>

      <div class="card-body" style="padding-top: 0">
        <h5 class="mb-3 text-danger">Danh sách câu hỏi</h5>
        <div v-if="questions">
          <div class="d-flex mb-3" v-for="q in questions" :key="q.id">
            <a :href="'/user/' + q.idUser.id">
              <img
                :src="q.idUser.avatar"
                class="border rounded-circle me-2"
                alt="Avatar"
                style="height: 40px"
              />
            </a>
            <div>
              <div class="bg-light rounded-3 px-3 py-1">
                <h5 class="text-dark mb-0">
                  <strong>{{ q.idUser.name }}</strong>
                </h5>
                <p class="text-muted mb-0">{{ q.content }}</p>
              </div>
            </div>
          </div>
        </div>
        <div v-if="questions===null||questions.length ===0" class="text-center bg-info h2 pb-2" style="margin: 0">
          Không có câu hỏi nào!!
        </div>
      </div>
      <!-- Interactions -->
    </div>
    <!--Section: Newsfeed-->
  </div>
</template>
  
  
  
<script>
import Apis, { authApi, endpoints } from "@/configs/Apis";
export default {
  name: "ListQuestionLive",
  data() {
    return {
      live: null,
      questions: null,
      id: 0,
    };
  },
  async created() {
    this.id = this.$route.params.id;

    try {
      this.live = await this.fetchData(this.id);
      this.questions = await this.fetchQuestion(this.id);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  },
  methods: {
    async fetchData(id) {
      try {
        const res = await Apis.get(`${endpoints["Live"]}/${id}`);
        return res.data;
      } catch (error) {
        console.log("Lỗi k lấy được danh sách câu hỏi!!");
      }
    },
    async fetchQuestion(id) {
      try {
        const res = await Apis.get(`${endpoints["ListQuestion"]}/${id}`);
        return res.data;
      } catch (error) {
        console.log("Lỗi k lấy được danh sách câu hỏi!!");
      }
    },
  },
};
</script>