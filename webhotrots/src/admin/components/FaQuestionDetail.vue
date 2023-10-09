<template>
  <div>
    <h1 class="text-center text-info">Chỉnh sửa câu hỏi thường gặp</h1>
    <div class="container">
      <form v-if="faquestion" @submit.prevent="updateFAQ">
        <div class="form-group">
          <label for="mota">Câu hỏi </label>
          <textarea
            class="form-control"
            id="mota"
            v-model="faquestion.question"
            required
          ></textarea>
        </div>
        <div class="form-group">
          <label for="motaCTDT">Câu trả lời</label>
          <textarea
            class="form-control"
            id="motaCTDT"
            v-model="faquestion.answers"
            required
          ></textarea>
        </div>

        <div class="d-flex mt-2 mb-2">
          <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
          <router-link
            :to="{ name: 'faquestionAdmin' }"
            class="btn btn-success"
            style="margin-left: 2px"
            >Quay lại</router-link
          >
        </div>
      </form>
    </div>
  </div>
</template>
  <script>
import Apis, { authApi, endpoints } from "@/configs/Apis";

export default {
  name: "FaQuestionDetail",
  data() {
    return {
      faquestion: null,
      id:0,
    };
  },
  async created() {
     this.id = this.$route.params.id;
    try {
      this.faquestion = await this.fetchData(this.id);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  },
  methods: {
    async fetchData(id) {
      try {
        const res = await Apis.get(`${endpoints["FaQuestion"]}/${id}`);
        return res.data;
      } catch (error) {
        console.log("Lỗi không thể lấy faq!! " + error);
      }
    },
    async updateFAQ() {
      try {
        const res = await authApi().put(
          `${endpoints["FaQuestion"]}/${this.id}`,
          this.faquestion
        );
        if (res.data === true) {
          alert("update thành công!!");
          this.$router.push({ name: "faquestionAdmin" });
        } else {
          alert("update thất bại!!!");
        }
      } catch (error) {
        console.log("Lỗi không thể lấy faq!! " + error);
      }
    },
  },
};
</script>
  