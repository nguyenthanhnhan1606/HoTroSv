<template>
  <div>
    <h1 class="text-center text-info">Cập nhật thông tin buổi livestream</h1>
    <div class="container">
      <form
        v-if="live"
        @submit.prevent="updateLive"
        method="put"
        enctype="multipart/form-data"
      >
        <div class="form-group">
          <label for="name">Tiêu đề</label>
          <input
            type="text"
            v-model="live.title"
            class="form-control"
            id="name"
          />
        </div>
        <div class="form-group">
          <label for="description">Nội dung</label>
          <textarea
            v-model="live.description"
            class="form-control"
            id="description"
          ></textarea>
        </div>
        <div class="d-flex">
            <div class="form-group" style="margin-right: 20px">
              <label for="name">Khoảng thời gian được đặt câu hỏi</label>
              <input
                type="datetime-local"
                v-model="live.thoigian"
                class="form-control"
                id="name"
              />
            </div>
            <div class="form-group">
            <label for="name">Ngày live</label>
            <input
              type="datetime-local"
              v-model="live.ngay"
              class="form-control"
              id="name"
            />
          </div>
          </div>

        <div class="form-group">
          <label for="name">Link live</label>
          <input
            type="text"
            v-model="live.path"
            class="form-control"
            id="name"
          />
        </div>

        <div class="d-flex mt-2">
          <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
          <router-link
            :to="{ name: 'live' }"
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
import Apis, { authApi } from "@/configs/Apis.js";
import { endpoints } from "@/configs/Apis.js";
import moment from 'moment-timezone';
export default {
  name: "LiveDetail",
  data() {
    return {
      live: null,
    };
  },
  async created() {
    const id = this.$route.params.id;

    try {
      this.live = await this.loadLive(id);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  },
  methods: {
    async loadLive(id) {
      try {
        const response = await Apis.get(`${endpoints["Live"]}/${id}`);
        return response.data;
      } catch (error) {
        throw error;
      }
    },
    async updateLive() {
      try {
        this.live.ngay = moment(this.live.ngay).tz("Asia/Ho_Chi_Minh").format("YYYY-MM-DD HH:mm");
        this.live.thoigian = moment(this.live.thoigian).tz("Asia/Ho_Chi_Minh").format("YYYY-MM-DD HH:mm");
        const res = await authApi().put(
          `${endpoints["Live"]}/${this.live.id}`,
          this.live
        );
        if (res.data === true) {
          alert("update thành công");
          this.$router.push({ name: "live" });
        } else alert("update thất bại");
      } catch (error) {
        console.error("Error updating banner:", error);
      }
    },
  },
};
</script>