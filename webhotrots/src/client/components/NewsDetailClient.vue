<template>
  <!--Section: Newsfeed-->
  <div class="container" v-if="news">
    <div class="card mt-3" style="max-width: 100%">
      <div class="card-body">
        <div class="row mb-4 border-bottom pb-2">
          <div class="col-3">
            <img
              :src="news.image"
              class="img-fluid shadow-1-strong rounded"
              alt="Hollywood Sign on The Hill"
            />
          </div>

          <div class="col-9">
            <p class="mb-2">
              <strong>{{ news.title }}</strong>
            </p>
            <span>{{ news.content }}</span>
            <p>
              <span>Ngày cập nhật: {{ news.ngaycapnhat }}</span>
            </p>
          </div>
        </div>
      </div>

      <div class="card-body">
        <div
          class="d-flex justify-content-between text-center border-top border-bottom mb-4"
        >
          <button
            type="button"
            class="btn btn-lg"
            data-mdb-ripple-color="dark"
          >
            <i class="fas fa-comment-alt me-2"></i>Bình luận
          </button>
          <button
            type="button"
            class="btn btn-lg"
            data-mdb-ripple-color="dark"
          >
            <i class="fas fa-share me-2"></i>Chia sẻ
          </button>
        </div>
        <!-- Buttons -->

        <!-- Comments -->

        <!-- Input -->
        <div class="d-flex mb-3">
          <form @submit.prevent="Comments" style="width: 100%">
            <div v-if="getUser">
              <img
                class="rounded-circle shadow-1-strong me-3"
                :src="getUser.avatar"
                alt="avatar"
                width="40"
                height="40"
              />
              <strong>{{ getUser.name }}</strong>
            </div>
            <div v-if="errorMessage" class="alert alert-danger mt-3">
              {{ errorMessage }}
            </div>
            <div class="form-outline w-100 mt-2">
              <textarea
                class="form-control"
                id="textAreaExample"
                v-model="comment.content"
                rows="4"
                style="background: #fff"
              ></textarea>
            </div>
            <div class="float-end mt-2 pt-1">
              <button type="submit" class="btn btn-primary btn-sm">Đăng</button>
            </div>
          </form>
        </div>
        <!-- Input -->

        <!-- Answers -->

        <!-- Single answer -->
        <div v-if="comments">
          <div
            class="d-flex mb-3"
            v-for="comment in comments"
            :key="comment.id"
          >
            <a href="">
              <img
                :src="comment.idUser.avatar"
                class="border rounded-circle me-2"
                alt="Avatar"
                style="height: 40px"
              />
            </a>
            <div>
              <div class="bg-light rounded-3 px-3 py-1">
                <a href="" class="text-dark mb-0">
                  <strong>{{ comment.idUser.name }}</strong>
                </a>
                <a href="" class="text-muted d-block">
                  <small>{{ comment.content }}</small>
                </a>
              </div>
              <a href="" class="text-muted small ms-3 me-2"
                ><strong>Like</strong></a
              >
              <a href="" class="text-muted small me-2"
                ><strong>Reply</strong></a
              >
            </div>
          </div>
        </div>
      </div>
      <!-- Interactions -->
    </div>
    <!--Section: Newsfeed-->
  </div>
</template>
<script>
import Apis, { authApi, endpoints } from "@/configs/Apis";
import { mapGetters } from "vuex";
export default {
  name: "NewsDetailClient",
  data() {
    return {
      news: null,
      comments: null,
      id: 0,
      comment: {
        idUser: 0,
        idNews: this.$route.params.id,
        content: "",
      },
      errorMessage: "",
    };
  },
  computed: {
    ...mapGetters(["getUser"]),
  },
  async created() {
    this.id = this.$route.params.id;

    try {
      this.news = await this.loadNews(this.id);
      this.comments = await this.loadComment(this.id);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  },
  methods: {
    async loadNews(id) {
      try {
        const response = await Apis.get(`${endpoints["News"]}/${id}`);
        return response.data;
      } catch (error) {
        throw error;
      }
    },
    async loadComment(id) {
      try {
        const response = await Apis.get(`${endpoints["CommentNews"]}/${id}`);
        return response.data;
      } catch (error) {
        throw error;
      }
    },
    async Comments() {
      if (this.getUser === null) {
        const r = window.confirm("Bạn chưa đăng nhập để bình luận!!");
        if (r) {
          this.$router.push("/login");
        }
      } else {
        if (this.errorMessage === "")
          this.errorMessage = "Bạn chưa nhập nội dung!!";
        else {
          this.comment.idUser = this.getUser;
          const res = await authApi().post(endpoints["Comment"], this.comment);
          if (res.data === true) {
            alert("Bình luận thành công!");
            this.comments = await this.loadComment(this.id);
            this.comment = "";
            this.errorMessage="";
          }
        }
      }
    },
  },
  // watch:{
  //   'comment.content':
  //     function(oldContent, newContent){
  //       console.log(newContent);
  //     }

  // }
};
</script>