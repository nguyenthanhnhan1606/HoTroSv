<template>
  <div>
    <h1 class="text-center text-info">Cập nhật Banner</h1>
    <div class="container">
      <form
        v-if="banner"
        @submit.prevent="updateBanner"
        method="put"
        enctype="multipart/form-data"
      >
        <div class="form-group">
          <label for="name">Tên</label>
          <input type="text" v-model="banner.name" class="form-control" id="name" />
        </div>
        <div class="form-group">
          <label for="description">Mô tả</label>
          <textarea v-model="banner.description" class="form-control" id="description"></textarea>
        </div>
        <div class="form-group">
          <label for="image">Hình ảnh</label>
          <input type="file" v-on:change="onFileChange" class="form-control" id="image" />
        </div>
        <div class="d-flex mt-2">
          <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
          <router-link :to="{ name: 'banner' }" class="btn btn-success" style="margin-left: 2px;">Quay lại</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

  
  

<script>
import Apis from "@/configs/Apis.js";
import { endpoints } from "@/configs/Apis.js";
export default {
  name: "BannerDetail",
  data() {
    return {
      banner: null,
    };
  },
  async created() {
   
    const id = this.$route.params.id;

  
    try {
      this.banner = await this.loadBanner(id); 
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  },
  methods: {
    async loadBanner(id) {
      try {
        const response = await Apis.get(`${endpoints["Banner"]}/${id}`);
        return response.data;
      } catch (error) {
        throw error; 
      }
    },
    async updateBanner() {
      try {
        const formData = new FormData();
        formData.append("name", this.banner.name);
        formData.append("description", this.banner.description);
        formData.append("file", this.banner.image);

        const res =await Apis.put(`${endpoints['UpdateBanner']}/${this.banner.id}`, formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          },
        });
        if(res.status ===200)
          alert("update thành công")
        this.$router.push({ name: "banner" });
      } catch (error) {
        console.error("Error updating banner:", error);
      }
    },
    onFileChange(event) {
      const file = event.target.files[0];
      
      this.banner.image = file;
    }
  },
};
</script>
