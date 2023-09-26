<template>
  <h1 class="text-center text-info mt-2">Thêm tài khoản tư vấn</h1>
  <h6 class="container">
    <span className="text-danger">(*)</span> Không được bỏ trống
  </h6>
  <div class="d-flex justify-content-center overlay" v-if="myFlag">
        <div class="spinner-border" role="status"></div>
      </div>
  <div class="container">
    <form @submit.prevent="AddUserAdviser">
        <div v-if="errorMessage" class="alert alert-danger mt-3">
                    {{ errorMessage }}
                  </div>
      <div class="mb-3">
        <label for="ten" class="form-label"
          >Tên <span className="text-danger">*</span></label
        >
        <input type="text" class="form-control" id="ten" v-model="user.name" />
      </div>
      <div class="mb-3">
        <label for="ten" class="form-label"
          >Tên đăng nhập <span className="text-danger">*</span></label
        >
        <input
          type="text"
          class="form-control"
          id="username"
          v-model="user.username"
        />
      </div>
      <div class="mb-3">
        <label for="ten" class="form-label"
          >Mật khẩu <span className="text-danger">*</span></label
        >
        <input
          type="password"
          class="form-control"
          id="password"
          v-model="user.password"
        />
      </div>
      <div class="mb-3">
        <label for="email" class="form-label"
          >Email <span className="text-danger">*</span></label
        >
        <input
          type="email"
          class="form-control"
          id="email"
          v-model="user.email"
        />
      </div>
      <div class="mb-3">
        <label for="ngaysinh" class="form-label"
          >Ngày sinh <span className="text-danger">*</span></label
        >
        <input
          type="date"
          class="form-control"
          id="ngaysinh"
          v-model="user.ngaysinh"
        />
      </div>
      <div class="mb-3">
        <label for="gioitinh" class="form-label">Giới tính</label>
        <select class="form-select" id="gioitinh" v-model="user.gioitinh">
          <option value="">Chọn giới tính</option>
          <option value="Nam">Nam</option>
          <option value="Nữ">Nữ</option>
          <option value="Khác">Khác</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="sdt" class="form-label">Số điện thoại</label>
        <input type="text" class="form-control" id="sdt" v-model="user.sdt" />
      </div>
      <div class="mb-3">
        <label for="avatar" class="form-label"
          >Avatar <span className="text-danger">*</span></label
        >
        <input
          type="file"
          class="form-control"
          id="avatar"
          v-on:change="onFileChange"
        />
      </div>
      <div class="mb-3">
        <label for="userRole" class="form-label">Vai trò người dùng</label>
        <select class="form-select" id="userRole" v-model="user.userRole">
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
  name: "AddUserAdviser",
  data() {
    return {
      user: {
        name: "",
        username: "",
        email: "",
        password: "",
        ngaysinh: "",
        avatar: null,
        gioitinh: "",
        name: "",
        sdt: "",
        userRole: "ROLE_ADVISER",
      },
      errorMessage: "",
      myFlag: false,
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
    async AddUserAdviser() {
      try {
        if (
          this.user.ho === "" ||
          this.user.ten === "" ||
          this.user.email === "" ||
          this.user.username === "" ||
          this.user.password === "" ||
          this.user.ngaysinh === "" ||
          this.user.avatar === null
        ) {
          this.errorMessage = "Không được bỏ trống những ô có dấu *";
        } else {
          this.showSpinner(true);
          const formData = new FormData();
          formData.append("name", this.user.ho + " " + this.user.ten);
          formData.append("username", this.user.username);
          formData.append("password", this.user.password);
          formData.append("email", this.user.email);
          formData.append("ngaysinh", this.user.ngaysinh);
          formData.append("sdt", this.user.sdt);
          formData.append("gioitinh", this.user.gioitinh);
          formData.append("userRole", this.user.userRole);
          formData.append("file", this.user.avatar);
          const res = await authApi().post(
            `${endpoints["Register"]}`,
            formData,
            {
              headers: {
                "Content-Type": "multipart/form-data",
              },
            }
          );
          if (res.data === true) {
            this.showSpinner(false);
            alert("Thêm thành công");
            this.$router.push({ name: "listUser" });
          } else {
            this.showSpinner(false);
            alert("Thêm thất bại");
            this.errorMessage = "Tên đăng nhập đã có người sử dụng";

          }
        }
      } catch (error) {
        console.error("Error updating banner:", error);
      }
    },
    onFileChange(event) {
      const file = event.target.files[0];

      this.user.avatar = file;
    },
    showSpinner(flag) {
      this.myFlag = flag;
    },
  },
  watch: {
    "this.errorMessage": function (newErrorMessage, oldErrorMessage) {
      console.log(newErrorMessage);
      console.log(oldErrorMessage);
    },
  },
};
</script>