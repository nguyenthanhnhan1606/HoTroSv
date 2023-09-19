<template>
  <div>
    <h1 class="text-center text-info">Cập nhật thông tin tuyển sinh</h1>
    <div class="container">
      <form
        v-if="news"
        @submit.prevent="updateNews"
        method="put"
        enctype="multipart/form-data"
      >
        <div class="form-group">
          <label for="name">Tiêu đề</label>
          <input
            type="text"
            v-model="news.title"
            class="form-control"
            id="name"
          />
        </div>
        <div class="form-group">
          <label for="description">Nội dung</label>
          <textarea
            v-model="news.content"
            class="form-control"
            id="description"
          ></textarea>
        </div>
        <div class="form-group">
          <label for="name">Ngày tạo</label>
          <input
            type="date"
            v-model="news.ngaytao"
            class="form-control"
            id="name"
            readonly
          />
        </div>
        <div class="form-group">
          <label for="type">Loại</label>
          <select
            name="type"
            v-model.trim="news.type"
            class="form-control custom-select"
          >
            <option value="Tuyển sinh đại học">Tuyển sinh đại học</option>
            <option value="Tuyển sinh sau đại học">Tuyển sinh sau đại học</option>
            <option value="Tuyển sinh trực tuyến">Tuyển sinh trực tuyến</option>
            <option value="Tuyển sinh đào tạo từ xa">Tuyển sinh đào tạo từ xa</option>
          </select>
        </div>

        <div class="form-group">
          <label for="image">Hình ảnh</label>
          <input
            type="file"
            v-on:change="onFileChange"
            class="form-control"
            id="image"
          />
        </div>
        <div class="d-flex mt-2">
          <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
          <router-link
            :to="{ name: 'news' }"
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
export default {
  name: "NewsDetail",
  data() {
    return {
      news: null,
      image: null
    };
  },
  async created() {
    const id = this.$route.params.id;

    try {
      this.news = await this.loadBanner(id);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  },
  methods: {
    async loadBanner(id) {
      try {
        const response = await Apis.get(`${endpoints["News"]}/${id}`);
        return response.data;
      } catch (error) {
        throw error;
      }
    },
    async updateNews() {
      try {
        const formData = new FormData();
        formData.append("title", this.news.title);
        formData.append("content", this.news.content);
        formData.append("type", this.news.type);
        formData.append("file", this.news.image);

        const res =await authApi().put(`${endpoints['UpdateNews']}/${this.news.id}`, formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          },
        });
        if(res.status ===200)
          alert("update thành công")
        this.$router.push({ name: "news" });
      } catch (error) {
        console.error("Error updating banner:", error);
      }
    },
    onFileChange(event) {
      const file = event.target.files[0];

      this.news.image = file;
    }
  },
};
</script>