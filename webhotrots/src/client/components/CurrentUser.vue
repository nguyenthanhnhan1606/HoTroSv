<template>
    <div class="mb-5 mt-3">
      <div class="container mt-2" v-if="getUser">
        <div class="row">
          <div class="col-md-4">
            <div class="card">
              <img
                class="card-img-top"
                :src="getUser.avatar"
                alt="Avatar"
                style="height: 350px; object-fit: cover;"
              />
            </div>
          </div>
          <div class="col-md-8">
            <div class="card">
              <div class="card-body">
                <h1 class="card-title text-center text-info">Thông tin chi tiết của bạn</h1>
                <div class="mb-3">
                  <label class="form-label">Họ và tên: <strong>{{ getUser.name }}</strong></label>
                </div>
                <div class="mb-3">
                  <label class="form-label">Ngày sinh: <strong>{{ getUser.ngaysinh }}</strong></label>
                </div>
                <div class="mb-3">
                  <label class="form-label">Giới tính: <strong>{{ getUser.gioitinh }}</strong></label>
                </div>
                <div class="mb-3">
                  <label class="form-label">Email: <strong>{{ getUser.email }}</strong></label>
                </div>
                <div class="mb-3">
                  <label class="form-label">Số điện thoại: <strong>{{ getUser.sdt }}</strong></label>
                </div>
              </div>
            </div>
          </div>
          
        </div>
        <router-link to="/" class="btn btn-success mt-3 ms-3">
        Quay lại
      </router-link>
      </div>
     
    </div>
  </template>
<script>
import { authApi, endpoints } from "@/configs/Apis";
import { mapGetters } from 'vuex';

export default {
  name: "CurrentUser",
  data() {
    return {
      user: null,
    };
  },
  computed:{
    ...mapGetters(["getUser"])
  },
  // mounted(){
  //   this.fetchData();
  // },
  // methods: {
  //   async fetchData() {
  //     try {
  //       const res = await authApi().get(endpoints["Current_user"]);
  //       this.user = res.data;
  //     } catch (error) {
  //       console.log("Lấy thông tin user thất bại " + error);
  //     }
  //   },
  // },
};
</script>
