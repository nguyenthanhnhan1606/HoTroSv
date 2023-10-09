<template>
    <div>
      <h1 class="text-center text-info">Thêm buổi livestream</h1>
      <div class="container">
        <form
          @submit.prevent="addLive"
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
              required
            />
          </div>
          <div class="form-group">
            <label for="description">Nội dung</label>
            <textarea
              v-model="live.description"
              class="form-control"
              id="description"
              required
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
                required
              />
            </div>
            <div class="form-group">
            <label for="name">Ngày live</label>
            <input
              type="datetime-local"
              v-model="live.ngay"
              class="form-control"
              id="name"
              required
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
              required
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
    name: "AddLive",
    data() {
      return {
        live:{
            title:"",
            description:"",
            ngay:"",
            thoigian:"",
            path:"",
        },
      };
    },
    methods: {
      async addLive() {
        try {
            this.live.ngay=moment(this.ngay).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DD HH:mm');
           this.live.thoigian=moment(this.thoigian).tz('Asia/Ho_Chi_Minh').format('YYYY-MM-DD HH:mm');          
            const res = await authApi().post(endpoints["Live"],this.live);
            if (res.data === true) {
                alert("Thêm thành công");
                this.$router.push({ name: "live" });
            } else alert("Thêm thất bại");
        } catch (error) {
          console.error("Error updating banner:", error);
        }
      }
    },
  };
  </script>