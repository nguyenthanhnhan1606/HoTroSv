<template>
    <h1 class="text-center text-info mt-2">Thông tin tài khoản</h1>
    <div v-if="user" class="container" >
        <form @submit.prevent="updateUser">
    <div class="mb-3">
      <label for="ten" class="form-label">Tên:</label>
      <input
        type="text"
        class="form-control"
        id="ten"
        v-model="user.name"
        required
      />
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">Email:</label>
      <input
        type="email"
        class="form-control"
        id="email"
        v-model="user.email"
        required
      />
    </div>  
    <div class="mb-3">
      <label for="ngaysinh" class="form-label">Ngày sinh:</label>
      <input
        type="date"
        class="form-control"
        id="ngaysinh"
        v-model="user.ngaysinh"
        required
      />
    </div>
    <div class="mb-3">
      <label for="gioitinh" class="form-label">Giới tính:</label>
      <select
        class="form-select"
        id="gioitinh"
        v-model="user.gioitinh"
      >
        <option value="">Chọn giới tính</option>
        <option value="Nam">Nam</option>
        <option value="Nữ">Nữ</option>
        <option value="Khác">Khác</option>
      </select>
    </div>
    <div class="mb-3">
      <label for="sdt" class="form-label">Số điện thoại:</label>
      <input type="text" class="form-control" id="sdt" v-model="user.sdt" />
    </div>
    <div class="mb-3">
      <label for="avatar" class="form-label">Avatar:</label>
      <input
        type="file"
        class="form-control"
        id="avatar"
        v-on:change="onFileChange"
      />
    </div>
    <div class="mb-3">
      <label for="userRole" class="form-label">Vai trò người dùng:</label>
      <select
        class="form-select"
        id="userRole"
        v-model="user.userRole"
        required
      >
        <option value="ROLE_USER">Người dùng</option>
        <option value="ROLE_ADVISER">Người tư vấn</option>
      </select>
    </div>
    <div class="d-flex mb-5">
          <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
          <router-link
            :to="{ name: 'listUser' }"
            class="btn btn-success"
            style="margin-left: 2px"
            >Quay lại</router-link
          >
        </div>
  </form>
    </div>
  
</template>

<script>
import { authApi, endpoints } from "@/configs/Apis";

export default {
  name: "UserDetail",
  data() {
    return {
      user: null,
    };
  },
  async created() {
   const id = this.$route.params.id;
   try {
     this.user = await this.fetchData(id); 
   } catch (error) {
     console.error("Error fetching data:", error);
   }
 },
  methods: {
    async fetchData(id) {
      try {
        const res = await authApi().get(`${endpoints["ListUser"]}/${id}`);
        return res.data;
      } catch (error) {
        console.log("Lấy thông tin user thất bại " + error);
      }
    },
    async updateUser(){
        try{
            const id = this.$route.params.id;
            const res = await authApi().put(`${endpoints["ListUser"]}/${id}`,this.user)
            if(res.data === true){
                alert("update thành công")
                this.$router.push({name:"listUser"})
            }
        }catch(error){
            console.log("update user thất bại!!")
        }
    }
  },
};
</script>