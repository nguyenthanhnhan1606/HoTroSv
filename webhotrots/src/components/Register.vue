<template>
  <section class="h-100 bg-dark">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col">
          <div class="card card-registration my-4">
            <div class="row g-0">
              <div class="col-xl-6 d-none d-xl-block">
                <img
                  src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img4.webp"
                  alt="Sample photo"
                  class="img-fluid"
                  style="
                    border-top-left-radius: 0.25rem;
                    border-bottom-left-radius: 0.25rem;
                  "
                />
              </div>

              <div class="col-xl-6">
                <div class="card-body p-md-5 text-black">
                  <h3 class="mb-2 text-uppercase text-center text-info">
                    ĐĂNG KÝ tài khoản
                  </h3>
                  <div v-if="errorMessage" class="alert alert-danger mt-3">
                    {{ errorMessage }}
                  </div>
                  <form @submit.prevent="register">
                    <div class="form-outline mb-4">
                      <label class="form-label" for="username"
                        >Tên đăng nhập
                        <span className="text-danger">*</span></label
                      >
                      <input
                        type="text"
                        id="username"
                        v-model="user.username"
                        class="form-control form-control-lg"
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label class="form-label" for="password"
                        >Mật khẩu <span className="text-danger">*</span></label
                      >
                      <input
                        type="password"
                        id="password"
                        v-model="user.password"
                        class="form-control form-control-lg"
                      />
                    </div>
                    <div class="row">
                      <div class="col-md-6 mb-4">
                        <div class="form-outline">
                          <label class="form-label" for="ten"
                            >Tên <span className="text-danger">*</span></label
                          >
                          <input
                            type="text"
                            id="ten"
                            class="form-control form-control-lg"
                            v-model="user.ten"
                          />
                        </div>
                      </div>
                      <div class="col-md-6 mb-4">
                        <div class="form-outline">
                          <label class="form-label" for="ho"
                            >Họ <span className="text-danger">*</span></label
                          >
                          <input
                            type="text"
                            id="ho"
                            class="form-control form-control-lg"
                            v-model="user.ho"
                          />
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6 mb-4">
                        <div class="form-outline">
                          <label class="form-label" for="ngaysinh"
                            >Ngày sinh
                            <span className="text-danger">*</span></label
                          >
                          <input
                            type="date"
                            id="ngaysinh"
                            v-model="user.ngaysinh"
                            class="form-control form-control-lg"
                          />
                        </div>
                      </div>

                      <div class="col-md-6 mb-4">
                        <div class="form-outline">
                          <label for="gioitinh" class="form-label"
                            >Giới tính</label
                          >
                          <select
                            class="form-select"
                            id="gioitinh"
                            name="gioitinh"
                            v-model="user.gioitinh"
                            style="height: 48px"
                          >
                            <option value="">Chọn giới tính</option>
                            <option value="Nam">Nam</option>
                            <option value="Nữ">Nữ</option>
                            <option value="Khác">Khác</option>
                          </select>
                        </div>
                      </div>
                    </div>

                    <div class="form-outline mb-4">
                      <label class="form-label" for="email"
                        >Email <span className="text-danger">*</span></label
                      >
                      <input
                        type="email"
                        id="email"
                        class="form-control form-control-lg"
                        v-model="user.email"
                      />
                    </div>
                    <div class="form-outline mb-4">
                      <label class="form-label" for="sdt">Số điện thoại </label>
                      <input
                        type="number"
                        id="sdt"
                        class="form-control form-control-lg"
                        v-model="user.sdt"
                      />
                    </div>

                    <div class="form-outline mb-4">
                      <label class="form-label" for="avatar"
                        >Avatar <span className="text-danger">*</span></label
                      >
                      <input
                        type="file"
                        v-on:change="onFileChange"
                        class="form-control"
                        id="image"
                      />
                    </div>

                    <div class="d-flex justify-content-end pt-3">
                      <router-link
                        :to="{ name: 'login' }"
                        class="btn btn-lg btn-success"
                        >Quay lại</router-link
                      >
                      <button type="submit" class="btn btn-info btn-lg ms-2">
                        Đăng ký
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
  
  <script>
import Apis from "@/configs/Apis.js";
import { endpoints } from "@/configs/Apis.js";
export default {
  name: "Register",
  data() {
    return {
      user: {
        ho: "",
        ten: "",
        username: "",
        email: "",
        password: "",
        ngaysinh: "",
        avatar: null,
        gioitinh: "",
        name: "",
        sdt: "",
      },
      errorMessage: "",
    };
  },
  methods: {
    async register() {
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
        }
        const formData = new FormData();
        formData.append("name", this.user.ho + " " + this.user.ten);
        formData.append("username", this.user.username);
        formData.append("password", this.user.password);
        formData.append("email", this.user.username);
        formData.append("ngaysinh", this.user.ngaysinh);
        formData.append("sdt", this.user.sdt);
        formData.append("gioitinh", this.user.gioitinh);
        formData.append("file", this.user.avatar);
        const res = await Apis.post(`${endpoints["Register"]}`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });
        if (res.data === true) {
          alert("Đăng ký thành công");
          this.$router.push({ name: "login" });
        } else {
          alert("Đăng ký thất bại");
        }
      } catch (error) {
        console.error("Error updating banner:", error);
      }
    },
    onFileChange(event) {
      const file = event.target.files[0];

      this.user.avatar = file;
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
  

  