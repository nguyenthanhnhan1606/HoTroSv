<template>
  <MyHeader />
  <router-view />
  <div v-if="$route.path === '/'">
    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button
          v-for="(banner, index) in banners"
          :key="index"
          type="button"
          :data-bs-target="'#myCarousel'"
          :data-bs-slide-to="index"
          :class="{ active: index === 0 }"
          aria-current="true"
          :aria-label="'Slide ' + (index + 1)"
        ></button>
      </div>
      <div class="carousel-inner">
        <div
          v-for="banner in banners"
          :key="banner.id"
          :class="['carousel-item', banner.id === 1 ? 'active' : '']"
        >
          <img
            :src="banner.image"
            class="d-block w-100"
            :alt="'Slide ' + (banner.id + 1)"
          />
          <!-- <div class="carousel-caption d-none d-md-block text-info">
            <h3>{{ banner.description }}</h3>
          </div> -->
        </div>
      </div>
      <a
        class="carousel-control-prev"
        href="#myCarousel"
        role="button"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </a>
      <a
        class="carousel-control-next"
        href="#myCarousel"
        role="button"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </a>
    </div>
    <!--Main layout-->
    <div class="container mt-5">
      <!--Section: News of the day-->
      <section class="border-bottom pb-4 mb-5">
        <div class="row gx-5">
          <div class="col-md-6 mb-4">
            <div
              class="bg-image hover-overlay ripple shadow-2-strong rounded-5"
              data-mdb-ripple-color="light"
            >
              <img
                src="https://mdbcdn.b-cdn.net/img/new/slides/080.webp"
                class="img-fluid"
              />
              <a href="#!">
                <div
                  class="mask"
                  style="background-color: rgba(251, 251, 251, 0.15)"
                ></div>
              </a>
            </div>
          </div>

          <div class="col-md-6 mb-4">
            <span class="badge bg-danger px-2 py-1 shadow-1-strong mb-3"
              >News of the day</span
            >
            <h4><strong>Facilis consequatur eligendi</strong></h4>
            <p class="text-muted">
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Facilis
              consequatur eligendi quisquam doloremque vero ex debitis veritatis
              placeat unde animi laborum sapiente illo possimus, commodi
              dignissimos obcaecati illum maiores corporis.
            </p>
            <button type="button" class="btn btn-primary">Read more</button>
          </div>
        </div>
      </section>

      <section>
        <div class="row gx-lg-5">
          <div class="col-lg-3 col-md-6 mb-4">
            <!-- News block -->
            <div>
              <!-- Featured image -->
              <div
                class="bg-image hover-overlay shadow-1-strong ripple rounded-5 mb-4"
                data-mdb-ripple-color="light"
              >
                <router-link :to="{ name: 'newsdh' }">
                  <img
                    src="@/assets/tsdh.png"
                    class="img-fluid"
                    alt="Brooklyn Bridge"
                  />
                </router-link>
              </div>

              <!-- Article data -->
              <div class="row mb-3">
                <div class="col-9">
                  <a href="" class="text-info nav-link">
                    <i class="fa fa-graduation-cap"></i>
                    Đại học
                  </a>
                </div>

                <div class="col-3 text-end">
                  <u> 15.07.2020</u>
                </div>
              </div>

              <!-- Article title and description -->
              <a href="" class="text-dark nav-link">
                <h6 class="text-center">Tuyển sinh đại học chính quy và CLC</h6>
              </a>

              <div class="mb-2 mt-3">
                <strong class="text-bg-success">Các tin tức mới nhất</strong>
              </div>

              <div v-for="(newsdh, index) in newsdhs" :key="newsdh.id">
                <a href="" class="nav-link text-dark" v-if="index < 5">
                  <div class="row mb-4 border-bottom pb-2">
                    <div class="col-3">
                      <img
                        :src="newsdh.image"
                        class="img-fluid shadow-1-strong rounded"
                        alt="Hollywood Sign on The Hill"
                      />
                    </div>

                    <div class="col-9">
                      <p class="mb-2">
                        <strong>{{ newsdh.title }}</strong>
                      </p>
                      <p>
                        <span>Ngày cập nhật: </span> {{ newsdh.ngaycapnhat }}
                      </p>

                      /2`11*/
                    </div>
                  </div>
                </a>
              </div>

              <hr />
            </div>
            <!-- News block -->
          </div>

          <div class="col-lg-3 col-md-6 mb-4">
            <!-- News block -->
            <div>
              <!-- Featured image -->
              <div
                class="bg-image hover-overlay shadow-1-strong rounded-5 ripple mb-4"
                data-mdb-ripple-color="light"
              >
                <router-link :to="{ name: 'newssdh' }">
                  <img
                    src="@/assets/tssdh.png"
                    class="img-fluid"
                    alt="Brooklyn Bridge"
                  />
                </router-link>
              </div>

              <!-- Article data -->
              <div class="row mb-3">
                <div class="col-6 text-danger">
                  <i class="fa fa-graduation-cap"></i>
                  Sau đại học
                </div>

                <div class="col-6 text-end">
                  <u> 15.07.2020</u>
                </div>
              </div>

              <!-- Article title and description -->
              <a href="" class="text-dark text-center nav-link">
                <h6>Tuyển sinh cao học (thạc sĩ)</h6>
              </a>

              <div class="mb-2 mt-3">
                <strong class="text-bg-success">Các tin tức mới nhất</strong>
              </div>

              <div v-for="(newssdh, index) in newssdhs" :key="newssdh.id">
                <router-link
                  :to="{ name: 'newsDetailCl', params: { id: newssdh.id } }"
                  class="nav-link text-dark"
                  v-if="index < 5"
                >
                  <div class="row mb-4 border-bottom pb-2">
                    <div class="col-3">
                      <img
                        :src="newssdh.image"
                        class="img-fluid shadow-1-strong rounded"
                        alt="Hollywood Sign on The Hill"
                      />
                    </div>

                    <div class="col-9">
                      <p class="mb-2">
                        <strong>{{ newssdh.title }}</strong>
                      </p>
                      <p>
                        <span>Ngày cập nhật: </span> {{ newssdh.ngaycapnhat }}
                      </p>
                    </div>
                  </div>
                </router-link>
              </div>
            </div>
            <!-- News block -->
          </div>

          <div class="col-lg-3 col-md-6 mb-4">
            <!-- News block -->
            <div>
              <!-- Featured image -->
              <div
                class="bg-image hover-overlay shadow-1-strong rounded-5 ripple mb-4"
                data-mdb-ripple-color="light"
              >
                <img
                  src="@/assets/tsdttt.png"
                  class="img-fluid"
                  alt="Golden Gate National Recreation Area"
                />
                <a href="#!">
                  <div
                    class="mask"
                    style="background-color: rgba(251, 251, 251, 0.15)"
                  ></div>
                </a>
              </div>

              <!-- Article data -->
              <div class="row mb-3">
                <div class="col-6 text-success">
                  <i class="fa fa-graduation-cap"></i>
                  Trực tuyến
                </div>

                <div class="col-6 text-end">
                  <u> 15.07.2020</u>
                </div>
              </div>

              <!-- Article title and description -->
              <a href="" class="text-center nav-link text-dark">
                <h6>Tuyển sinh đào tạo trực tuyến</h6>
              </a>
              <div class="mb-2 mt-3">
                <strong class="text-bg-success">Các tin tức mới nhất</strong>
              </div>

              <div v-for="(newstt, index) in newstts" :key="newstt.id">
                <router-link
                  :to="{ name: 'newsDetailCl', params: { id: newstt.id } }"
                  class="nav-link text-dark"
                  v-if="index < 5"
                >
                  <div class="row mb-4 border-bottom pb-2">
                    <div class="col-3">
                      <img
                        :src="newstt.image"
                        class="img-fluid shadow-1-strong rounded"
                        alt="Hollywood Sign on The Hill"
                      />
                    </div>

                    <div class="col-9">
                      <p class="mb-2">
                        <strong>{{ newstt.title }}</strong>
                      </p>
                      <p>
                        <span>Ngày cập nhật: </span> {{ newstt.ngaycapnhat }}
                      </p>
                    </div>
                  </div>
                </router-link>
              </div>

              <hr />
            </div>
            <!-- News block -->
          </div>
          <div class="col-lg-3 col-md-6 mb-4">
            <!-- News block -->
            <div>
              <!-- Featured image -->
              <div
                class="bg-image hover-overlay shadow-1-strong rounded-5 ripple mb-4"
                data-mdb-ripple-color="light"
              >
                <img
                  src="@/assets/tsdttx.png"
                  class="img-fluid"
                  alt="Golden Gate National Recreation Area"
                />
                <a href="#!">
                  <div
                    class="mask"
                    style="background-color: rgba(251, 251, 251, 0.15)"
                  ></div>
                </a>
              </div>

              <!-- Article data -->
              <div class="row mb-3">
                <div class="col-6 text-warning">
                  <i class="fa fa-graduation-cap"></i>
                  Đào tạo từ xa
                </div>

                <div class="col-6 text-end">
                  <u> 15.07.2020</u>
                </div>
              </div>

              <!-- Article title and description -->
              <a href="" class="nav-link text-center text-dark">
                <h6>Tuyển sinh đào tạo từ xa</h6>
              </a>

              <div class="mb-2 mt-3">
                <strong class="text-bg-success">Các tin tức mới nhất</strong>
              </div>

              <div v-for="(newstx, index) in newstxs" :key="newstx.id">
                <a href="" class="nav-link text-dark" v-if="index < 5">
                  <div class="row mb-4 border-bottom pb-2">
                    <div class="col-3">
                      <img
                        :src="newstx.image"
                        class="img-fluid shadow-1-strong rounded"
                        alt="Hollywood Sign on The Hill"
                      />
                    </div>

                    <div class="col-9">
                      <p class="mb-2">
                        <strong>{{ newstx.title }}</strong>
                      </p>
                      <p>
                        <span>Ngày cập nhật: </span> {{ newstx.ngaycapnhat }}
                      </p>
                    </div>
                  </div>
                </a>
              </div>

              <hr />
            </div>
            <!-- News block -->
          </div>
        </div>
      </section>
      <!--Section: Content-->
    </div>
  </div>
</template>

<script>
import Apis from "@/configs/Apis.js";
import { endpoints } from "@/configs/Apis.js";
import MyHeader from "@/client/components/Header.vue";

export default {
  name: "Home",
  data() {
    return {
      banners: null,
      newsdhs: null,
      newssdhs: null,
      newstts: null,
      newstxs: null,
    };
  },
  components: {
    MyHeader,
  },
  mounted() {
    this.fetchData();
    this.fetchDataDH();
    this.fetchDataSDH();
    this.fetchDataTT();
    this.fetchDataTX();
  },
  methods: {
    async fetchData() {
      await Apis.get(endpoints["Banner"])
        .then((response) => {
          this.banners = response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
    async fetchDataDH() {
      await Apis.get(endpoints["NewsDH"])
        .then((response) => {
          this.newsdhs = response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
    async fetchDataSDH() {
      await Apis.get(endpoints["NewsSDH"])
        .then((response) => {
          this.newssdhs = response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
    async fetchDataTT() {
      await Apis.get(endpoints["NewsTT"])
        .then((response) => {
          this.newstts = response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
    async fetchDataTX() {
      await Apis.get(endpoints["NewsTX"])
        .then((response) => {
          this.newstxs = response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
  },
};
</script>
