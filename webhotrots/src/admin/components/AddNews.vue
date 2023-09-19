<template>
  <div>
    <h1 class="text-center text-info">Thêm thông tin tuyển sinh</h1>
    <div class="container">
      <form
        @submit.prevent="addNews"
        method="post"
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
          <label for="type">Loại</label>
          <select
            name="type"
            v-model.trim="news.type"
            class="form-control custom-select"
          >
            <option value="Tuyển sinh đại học">Tuyển sinh đại học</option>
            <option value="Tuyển sinh sau đại học">
              Tuyển sinh sau đại học
            </option>
            <option value="Tuyển sinh trực tuyến">Tuyển sinh trực tuyến</option>
            <option value="Tuyển sinh đào tạo từ xa">
              Tuyển sinh đào tạo từ xa
            </option>
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
          <button class="btn btn-info" title="Xóa">
            <i class="fa-solid fa-plus"></i> Thêm thông tin
          </button>
          <router-link
            :to="{ name: 'news' }"
            class="btn btn-success"
            style="margin-left: 3px"
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
  name: "AddDetail",
  data() {
    return {
      news: {
        title: "",
        content: "",
        type: "",
        image: null,
      },
    };
  },
  methods: {
    async addNews() {
      if (
        this.news.title === "" ||
        this.news.content === "" ||
        this.news.type === "" ||
        this.news.image === null
      )
        alert("Hãy nhập đủ thông tin!!");
      else {
        try {
          const formData = new FormData();
          formData.append("title", this.news.title);
          formData.append("content", this.news.content);
          formData.append("type", this.news.type);
          formData.append("file", this.news.image);

          const res = await authApi().post(`${endpoints["News"]}`, formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          });
          if (res.data === true) {
            alert("Thêm thành công");
            this.$router.push({ name: "news" });
          } else alert("Thêm thất bại");
        } catch (error) {
          console.error("Error updating banner:", error);
        }
      }
    },
    onFileChange(event) {
      const file = event.target.files[0];
      this.news.image = file;
    },
  },
};
</script>