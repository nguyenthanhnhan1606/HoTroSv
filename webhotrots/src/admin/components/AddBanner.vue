<template>
  <div>
    <h1 class="text-center text-info">Thêm banner</h1>
    <div class="container">
      <form
        @submit.prevent="addBanner"
        method="post"
        enctype="multipart/form-data"
      >
        <div class="form-group">
          <label for="name">Tên</label>
          <input
            type="text"
            v-model="banner.name"
            class="form-control"
            id="name"
          />
        </div>
        <div class="form-group">
          <label for="description">Mô tả</label>
          <textarea
            v-model="banner.description"
            class="form-control"
            id="description"
          ></textarea>
        </div>
        <div class="form-group">
          <label for="image">Hình ảnh</label>
          <input
            type="file"
            @change="onFileChange"
            class="form-control"
            id="image"
          />
        </div>
        <div class="d-flex mt-2">
          <button class="btn btn-info" title="Xóa">
            <i class="fa-solid fa-plus"></i> Thêm banner
          </button>
          <router-link
            :to="{ name: 'banner' }"
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
import Apis, { authApi } from "@/configs/Apis";
import { endpoints } from "@/configs/Apis";

export default {
  name: "AddBanner",
  data() {
    return {
      banner: {
        name: "",
        description: "",
        image: null,
      },
    };
  },
  methods: {
    async addBanner() {
      if (
        this.banner.name === "" ||
        this.banner.description === "" ||
        this.banner.image === null
      )
        alert("Bạn phải điền đủ thông tin");
      else if (
        this.banner.image.type !== "image/jpeg" &&
        this.banner.image.type !== "image/jpg" &&
        this.banner.image.type !== "image/png"
      )
        alert("Bạn hãy chọn những file có định dạng *.jpg, *.jepg, *.png");
      else {
        try {
          const formData = new FormData();
          formData.append("name", this.banner.name);
          formData.append("description", this.banner.description);
          formData.append("file", this.banner.image);

          await authApi().post(`${endpoints["Banner"]}`, formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          });

          this.$router.push({ name: "banner" });
        } catch (error) {
          console.error("Error adding banner:", error);
        }
      }
    },
    onFileChange(event) {
      const file = event.target.files[0];
      this.banner.image = file;
    },
  },
};
</script>
  