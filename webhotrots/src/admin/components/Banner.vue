<template>
  <div>
    <h1 class="text-center text-info">Quản lý danh sách banner</h1>

    <table class="table table-bordered">
      <thead class="thead-dark">
        <tr>
          <th scope="col" class="text-center">id</th>
          <th scope="col" class="text-center">Hình ảnh</th>
          <th scope="col" class="text-center">Tên banner</th>
          <th scope="col" class="text-center">Mô tả</th>
          <th scope="col" class="text-center">Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="banner in responseData" :key="banner.id">
          <td class="text-center">{{ banner.id }}</td>
          <td style="width: 150px">
            <img :src="banner.image" :alt="banner.name" class="w-100" />
          </td>
          <td class="text-center">{{ banner.name }}</td>
          <td class="text-center">{{ banner.description }}</td>
          <td class="text-center">
            <router-link
              :to="{ name: 'bannerDetail', params: { id: banner.id } }"
            >
              <button class="btn btn-success" title="Cập nhật">
                <i class="fa fa-edit"></i> Cập nhật
              </button>
            </router-link>

            <button
              class="btn btn-danger"
              style="margin-left: 10px"
              title="Xóa"
              @click="deleteBanner(banner.id)"
            >
              <i class="fa fa-trash"></i> Xóa
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link :to="{ name: 'addBanner' }">
      <button class="btn btn-info" title="Thêm">
        <i class="fa-solid fa-plus"></i> Thêm banner
      </button>
    </router-link>
  </div>
</template>
  
  <script>
import Apis from "@/configs/Apis.js";
import { endpoints } from "@/configs/Apis.js";
export default {
  name: "Banner",
  data() {
    return {
      responseData: null,
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      Apis.get(endpoints["Banner"])
        .then((response) => {
          this.responseData = response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
    async deleteBanner(id) {
      try {
        // Gọi API RecycleBin với id của banner
        const response = await Apis.put(`${endpoints['RecycleBinBanner']}/${id}`);
        if (response.data) {
          alert("Xóa thành công")
          this.fetchData();
          console.log("Banner deleted successfully");
        } else {
          console.error("Error deleting banner");
        }
      } catch (error) {
        console.error("Error deleting banner:", error);
      }
    },
  },
};
</script>
  
  
  