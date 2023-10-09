<template>
  <div>
    <h1 class="text-center text-info">Thêm câu hỏi thường gặp</h1>
    <div class="container">
      <form v-if="faquestion" @submit.prevent="addFAQ">
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
          <button type="submit" class="btn btn-primary">Thêm</button>
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
import { authApi, endpoints } from "@/configs/Apis";

export default {
  name: "AddFAQ",
  data() {
    return {
      faquestion: {
        question: "",
        answers: "",
      },
    };
  },
  methods: {
    async addFAQ() {
      try {
        const res = await authApi().post(
          endpoints["FaQuestion"],
          this.faquestion
        );
        if (res.data === true) {
          alert("Thêm thành công");
          this.$router.push({ name: "faquestionAdmin" });
        } else alert("Thêm thất bại!!");
      } catch (error) {
        console.log("Lỗi không thể thêm!! " + error);
      }
    },
  },
};
</script>
