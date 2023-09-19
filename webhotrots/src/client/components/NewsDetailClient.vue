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
            class="btn btn-link btn-lg"
            data-mdb-ripple-color="dark"
          >
            <i class="fas fa-comment-alt me-2"></i>Bình luận
          </button>
          <button
            type="button"
            class="btn btn-link btn-lg"
            data-mdb-ripple-color="dark"
          >
            <i class="fas fa-share me-2"></i>Chia sẻ
          </button>
        </div>
        <!-- Buttons -->

        <!-- Comments -->

        <!-- Input -->
        <div class="d-flex mb-3">
          <div style="width: 100%">
            <div>
              <img
                class="rounded-circle shadow-1-strong me-3"
                src="https://mdbcdn.b-cdn.net/img/new/avatars/18.webp"
                alt="avatar"
                width="40"
                height="40"
              />
              <strong>Nguyễn văn A</strong>
            </div>

            <div class="form-outline w-100 mt-2">
              <textarea
                class="form-control"
                id="textAreaExample"
                rows="4"
                style="background: #fff"
              ></textarea>
            </div>
            <div class="float-end mt-2 pt-1">
              <button type="button" class="btn btn-primary btn-sm">
                Đăng
              </button>
            </div>
          </div>
        </div>
        <!-- Input -->

        <!-- Answers -->

        <!-- Single answer -->
        <div class="d-flex mb-3">
          <a href="">
            <img
              src="https://mdbcdn.b-cdn.net/img/new/avatars/8.webp"
              class="border rounded-circle me-2"
              alt="Avatar"
              style="height: 40px"
            />
          </a>
          <div>
            <div class="bg-light rounded-3 px-3 py-1">
              <a href="" class="text-dark mb-0">
                <strong>Malcolm Dosh</strong>
              </a>
              <a href="" class="text-muted d-block">
                <small
                  >Lorem ipsum dolor sit amet consectetur, adipisicing elit.
                  Natus, aspernatur!</small
                >
              </a>
            </div>
            <a href="" class="text-muted small ms-3 me-2"
              ><strong>Like</strong></a
            >
            <a href="" class="text-muted small me-2"><strong>Reply</strong></a>
          </div>
        </div>

        <!-- Single answer -->
        <div class="d-flex mb-3">
          <a href="">
            <img
              src="https://mdbcdn.b-cdn.net/img/new/avatars/5.webp"
              class="border rounded-circle me-2"
              alt="Avatar"
              style="height: 40px"
            />
          </a>
          <div>
            <div class="bg-light rounded-3 px-3 py-1">
              <a href="" class="text-dark mb-0">
                <strong>Rhia Wallis</strong>
              </a>
              <a href="" class="text-muted d-block">
                <small
                  >Et tempora ad natus autem enim a distinctio quaerat
                  asperiores necessitatibus commodi dolorum nam perferendis
                  labore delectus, aliquid placeat quia nisi magnam.</small
                >
              </a>
            </div>
            <a href="" class="text-muted small ms-3 me-2"
              ><strong>Like</strong></a
            >
            <a href="" class="text-muted small me-2"><strong>Reply</strong></a>
          </div>
        </div>

        <!-- Single answer -->
        <div class="d-flex mb-3">
          <a href="">
            <img
              src="https://mdbcdn.b-cdn.net/img/new/avatars/6.webp"
              class="border rounded-circle me-2"
              alt="Avatar"
              style="height: 40px"
            />
          </a>
          <div>
            <div class="bg-light rounded-3 px-3 py-1">
              <a href="" class="text-dark mb-0">
                <strong>Marcie Mcgee</strong>
              </a>
              <a href="" class="text-muted d-block">
                <small>
                  Officia asperiores autem sit rerum architecto a deserunt
                  doloribus obcaecati, velit ab at, ad delectus sapiente!
                  Voluptatibus quaerat suscipit in nostrum necessitatibus illum
                  nemo quo beatae obcaecati quidem optio fugit ipsam distinctio,
                  illo repellendus porro sequi alias perferendis ea soluta
                  maiores nisi eligendi? Mollitia debitis quam ex, voluptates
                  cupiditate magnam fugiat.</small
                >
              </a>
            </div>
            <a href="" class="text-muted small ms-3 me-2"
              ><strong>Like</strong></a
            >
            <a href="" class="text-muted small me-2"><strong>Reply</strong></a>
          </div>
        </div>

        <!-- Single answer -->
        <div class="d-flex mb-3">
          <a href="">
            <img
              src="https://mdbcdn.b-cdn.net/img/new/avatars/10.webp"
              class="border rounded-circle me-2"
              alt="Avatar"
              style="height: 40px"
            />
          </a>
          <div>
            <div class="bg-light rounded-3 px-3 py-1">
              <a href="" class="text-dark mb-0">
                <strong>Hollie James</strong>
              </a>
              <a href="" class="text-muted d-block">
                <small
                  >Voluptatibus quaerat suscipit in nostrum
                  necessitatibus</small
                >
              </a>
            </div>
            <a href="" class="text-muted small ms-3 me-2"
              ><strong>Like</strong></a
            >
            <a href="" class="text-muted small me-2"><strong>Reply</strong></a>
          </div>
        </div>

        <!-- Answers -->

        <!-- Comments -->
      </div>
      <!-- Interactions -->
    </div>
    <!--Section: Newsfeed-->
  </div>
</template>
<script>
import Apis, { endpoints } from "@/configs/Apis";
export default {
  name: "NewsDetailClient",
  data() {
    return {
      news: null,
    };
  },
  async created() {
    const id = this.$route.params.id;

    try {
      this.news = await this.loadBanner(id);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  },
  methods: {
    async loadBanner(id) {
      try {
        const response = await Apis.get(`${endpoints["News"]}/${id}`);
        return response.data;
      } catch (error) {
        throw error;
      }
    },
  },
};
</script>