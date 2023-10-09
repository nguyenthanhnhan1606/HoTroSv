<template>
  <div>
    <h1 class="text-center text-info">Chỉnh sửa thông tin khoa</h1>
    <div class="container">
      <form v-if="department" @submit.prevent="updateDepartment">
        <div class="form-group">
          <label for="tenkhoa">Tên khoa</label>
          <input
            type="text"
            class="form-control"
            id="tenkhoa"
            v-model="department.tenkhoa"
            required
          />
        </div>
        <div class="form-group">
          <label for="mota">Giới thiệu</label>
          <textarea
            class="form-control"
            id="mota"
            v-model="department.mota"
            required
          ></textarea>
        </div>
        <div class="form-group">
          <label for="motaCTDT">Chương trình đào tạo</label>
          <textarea
            class="form-control"
            id="motaCTDT"
            v-model="department.motaCTDT"
            required
          ></textarea>
        </div>
        <div class="form-group">
          <label for="trungbinhdiem">Trung bình điểm 5 năm</label>
          <input
             type="text"
            class="form-control"
            id="trungbinhdiem"
            v-model="department.trungbinhdiem"
            required
          />
        </div>
        <div class="form-group">
          <label for="website">Website</label>
          <input
            type="text"
            class="form-control"
            id="website"
            v-model="department.website"
            required
          />
        </div>
        <div class="form-group">
          <label for="video">Video</label>
          <input type="file" v-on:change="onFileChange" class="form-control" id="video" />
        </div>

        <div class="d-flex mt-2 mb-2">
          <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
          <router-link :to="{ name: 'department' }" class="btn btn-success" style="margin-left: 2px;">Quay lại</router-link>
        </div>
      </form>
    </div>
  </div>
</template>
  
  <script>
import Apis, { authApi } from "@/configs/Apis.js";
import { endpoints } from "@/configs/Apis.js";

export default {
  name: "DepartmentDetail",
  data() {
    return {
      department: null,
    };
  },
  mounted() {
    this.fetchDepartmentDetail();
  },
  methods: {
    fetchDepartmentDetail() {
      const departmentId = this.$route.params.id;

      Apis.get(`${endpoints["Department"]}/${departmentId}`)
        .then((response) => {
          this.department = response.data;
        })
        .catch((error) => {
          console.error("Error fetching department detail:", error);
        });
    },
    async updateDepartment() {
        try {
        const formData = new FormData();
        formData.append("tenkhoa", this.department.tenkhoa);
        formData.append("mota", this.department.mota);
        formData.append("motaCTDT", this.department.motaCTDT);
        formData.append("trungbinhdiem", this.department.trungbinhdiem);
        formData.append("website", this.department.website);
        formData.append("video", this.department.video);
        const res = await authApi().put(`${endpoints['UpdateDepartment']}/${this.department.id}`, formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          },
        });
        if(res.data === true)
          alert("update thành công")
        this.$router.push({ name: "department" });
      } catch (error) {
        console.error("Error updating banner:", error);
      }
    },
    onFileChange(event) {
      const file = event.target.files[0];
      
      this.department.video = file;
    }
  },
};
</script>
  