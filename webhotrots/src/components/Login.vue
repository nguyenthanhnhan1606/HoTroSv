<template>
  <div class="container">
    <section class="vh-90">
      <div class="container py-5 h-100">
        <div class="row d-flex align-items-center justify-content-center h-100">
          <div class="col-md-8 col-lg-7 col-xl-6">
            <img
              src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
              class="img-fluid"
              alt="Phone image"
            />
          </div>
          <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
            <form @submit.prevent="login">
              <h1 class="text-center text-info mb-2">Đăng nhập</h1>
              <div v-if="errorMessage" class="alert alert-danger mt-3">
                {{ errorMessage }}
              </div>
              <div class="form-outline mb-4">
                <label class="form-label" for="username">Tên đăng nhập</label>
                <input
                  type="text"
                  id="username"
                  class="form-control form-control-lg"
                  v-model="user.username"
                />
              </div>

              <div class="form-outline mb-4">
                <label class="form-label" for="password">Mật khẩu</label>
                <input
                  type="password"
                  id="password"
                  class="form-control form-control-lg"
                  v-model="user.password"
                />
              </div>

              <div
                class="d-flex justify-content-around align-items-center mb-2"
              >
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    value=""
                    id="form1Example3"
                    checked
                  />
                  <label class="form-check-label" for="form1Example3">
                    Nhớ tài khoản
                  </label>
                </div>
                <a href="#!">Quên mật khuẩu?</a>
              </div>

              <Button type="submit" className="w-100 mt-2 btn btn-primary">
                Đăng nhập
              </Button>
              <div>
                <p class="text-center mt-1 mb-0">OR</p>
              </div>

              <a
                class="btn btn-primary w-100 mt-1"
                style="background-color: #3b5998"
                href="#!"
                role="button"
              >
                <i class="fab fa-facebook-f me-2"></i>Đăng nhập bằng Facebook
              </a>
              <a
                class="btn btn-primary w-100 mt-2"
                style="background-color: #dd4b39"
                href="#!"
                role="button"
              >
                <i class="fab fa-google me-2"></i>Đăng nhập bằng Google</a
              >
              <div className="text-center mt-3">
                <p className="mb-0">
                  Bạn không có tài khoản?
                  <router-link :to="{ name: 'register' }">Đăng ký</router-link>
                </p>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
  
  <script>
import Apis, { authApi } from "@/configs/Apis.js";
import { endpoints } from "@/configs/Apis.js";
import cookie from "vue-cookies";
import { mapState } from "vuex";

export default {
  name: "Login",
  computed: {
    ...mapState(["myState"]),
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      errorMessage: "",
    };
  },
  methods: {
    async login() {
      try {
        const res = await Apis.post(`${endpoints["Login"]}`, {
          username: this.user.username,
          password: this.user.password,
        });
        if (res.data === "error") {
          this.errorMessage =
            "Tài khoản hoặc mật khẩu của bạn không đúng!!";
        } else {
          this.errorMessage = "";
          cookie.set("token", res.data);

          let { data } = await authApi().get(endpoints["Current_user"]);
          cookie.set("user", data);

          await this.$store.dispatch("login", data);

          this.$router.push({ name: "home" });
        }
      } catch (error) {
        throw error;
      }
    },
  },
  watch: {
    "errorMessage": function (newErrorMessage, oldErrorMessage) {
      console.log(`Giá trị mới của user.errorMessage: ${newErrorMessage}`);
      console.log(`Giá trị cũ của user.errorMessage: ${oldErrorMessage}`);

    },
  },
};
</script>
  