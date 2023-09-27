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
          <button type="button" class="btn btn-lg" data-mdb-ripple-color="dark">
            <i class="fas fa-comment-alt me-2"></i>Bình luận
          </button>
          <button type="button" class="btn btn-lg" data-mdb-ripple-color="dark">
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
          <div class="col-md-12 col-lg-10 col-xl-8 w-100">
            <div class="card">
              <div class="card-body p-4">
                <div class="row">
                  <div class="col">
                    <div
                      class="d-flex flex-start"
                      v-for="c in comments"
                      :key="c.id"
                    >
                      <img
                        class="rounded-circle shadow-1-strong me-3"
                        :src="c.idUser.avatar"
                        alt="avatar"
                        width="65"
                        height="65"
                      />
                      <div class="flex-grow-1 flex-shrink-1">
                        <div>
                          <div
                            class="d-flex justify-content-between align-items-center"
                          >
                            <p class="mb-1">
                              <strong
                                >{{ c.idUser.name }}
                                <span class="small text-success">
                                  - {{ c.ngaybinhluan }}</span
                                >
                                <span class="small text-danger">
                                  -
                                  {{
                                    c.idUser.userRole === "ROLE_USER"
                                      ? "Người dùng"
                                      : "Người tư vấn"
                                  }}</span
                                >
                              </strong>
                            </p>
                            <p class="btn btn-info" @click="toggleReplyForm(c)">
                              <i class="fas fa-reply fa-xs"></i
                              ><span class="small"> Phản hồi</span>
                            </p>
                          </div>
                          <p class="small mb-0">
                            {{ c.content }}
                          </p>
                          <div class="mt-2" v-if="c.show">
                            <div v-if="getUser">
                              <img
                                class="rounded-circle shadow-1-strong me-3 mt-2 mb-1"
                                :src="getUser.avatar"
                                alt="avatar"
                                width="40"
                                height="40"
                              />
                              <strong>{{ getUser.name }}</strong>
                            </div>
                            <div class="form-outline w-100">
                              <textarea
                                class="form-control"
                                rows="3"
                                v-model="reply.content"
                                placeholder="Viết phản hồi..."
                              ></textarea>
                            </div>
                            <button
                              class="btn btn-primary btn-sm mt-2"
                              @click="Reply(c.id)"
                            >
                              Đăng
                            </button>
                          </div>
                        </div>
                        <hr />
                        <div
                          class="d-flex flex-start mt-4"
                          style="margin-bottom: 20px"
                          v-for="r in c.replySet"
                          :key="r.id"
                        >
                          <a class="me-3" href="#">
                            <img
                              class="rounded-circle shadow-1-strong"
                              :src="r.idAdvisor.avatar"
                              alt="avatar"
                              width="65"
                              height="65"
                            />
                          </a>
                          <div class="flex-grow-1 flex-shrink-1">
                            <div>
                              <div
                                class="d-flex justify-content-between align-items-center"
                              >
                                <p class="mb-1">
                                  <strong
                                    >{{ r.idAdvisor.name }}
                                    <span class="small text-success"
                                      >- {{ r.ngayreply }}</span
                                    >
                                    <span class="small text-danger">
                                      -
                                      {{
                                        r.idAdvisor.userRole === "ROLE_USER"
                                          ? "Người dùng"
                                          : "Người tư vấn"
                                      }}</span
                                    >
                                  </strong>
                                </p>
                                <p
                                  class="btn btn-info"
                                  @click="toggleReplyFormRep(r)"
                                >
                                  <i class="fas fa-reply fa-xs"></i
                                  ><span class="small"> Phản hồi</span>
                                </p>
                              </div>
                              <h6 class="small mb-0">
                                {{ r.content }}
                              </h6>
                              <div class="mt-2" v-if="r.showDep">
                                <div v-if="getUser">
                                  <img
                                    class="rounded-circle shadow-1-strong me-3 mt-2 mb-1"
                                    :src="getUser.avatar"
                                    alt="avatar"
                                    width="40"
                                    height="40"
                                  />
                                  <strong>{{ getUser.name }}</strong>
                                </div>
                                <div class="form-outline w-100">
                                  <textarea
                                    class="form-control"
                                    rows="3"
                                    v-model="reply.content"
                                    placeholder="Viết phản hồi..."
                                  ></textarea>
                                </div>
                                <button
                                  class="btn btn-primary btn-sm mt-2"
                                  @click="Reply(c.id)"
                                >
                                  Đăng
                                </button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
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
        idNews: 0,
        content: "",
      },
      reply: {
        idAdvisor: 0,
        idComment: 0,
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
      this.comments.forEach((c) => {
        c.show = false;
        c.replySet.forEach((r) => {
          r.showRep = false; 
        });
        c.replySet.sort((a, b) => {
          return b.id - a.id; 
        });
      });
      console.log(this.comments);
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
        if (this.comment.content === "")
          this.errorMessage = "Bạn chưa nhập nội dung!!";
        else {
          this.comment.idUser = this.getUser;
          this.comment.idNews = this.id;
          const res = await authApi().post(endpoints["Comment"], this.comment);
          if (res.data === true) {
            alert("Bình luận thành công!");
            this.comments = await this.loadComment(this.id);
            this.comment.content = "";
            this.errorMessage = "";
          }
        }
      }
    },
    async Reply(idC) {
      if (this.getUser === null) {
        const r = window.confirm("Bạn chưa đăng nhập để bình luận!!");
        if (r) {
          this.$router.push("/login");
        }
      } else {
        if (this.reply.content === "")
          alert("Bạn chưa nhập nội dung!!");
        else {
          this.reply.idAdvisor = this.getUser;
          this.reply.idComment = idC;
          const res = await authApi().post(endpoints["Reply"], this.reply);
          if (res.data === "success") {
            alert("Bình luận thành công!");
            this.comments = await this.loadComment(this.id);
            this.reply.content = "";
          }
        }
      }
    },
    toggleReplyForm(c) {
      c.show = !c.show;
    },
    toggleReplyFormRep(r) {
      r.showDep = !r.showDep;
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