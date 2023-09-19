<template>
  <div>
    <h1 class="text-center text-info">Quản lý danh sách các thông tin chờ duyệt</h1>

    <table class="table table-bordered">
      <thead class="thead-dark">
        <tr>
          <th scope="col" class="text-center">id</th>
          <th scope="col" class="text-center">Hình ảnh</th>
          <th scope="col" class="text-center">Tiêu đề</th>
          <th scope="col" class="text-center">Nội dung</th>
          <th scope="col" class="text-center">Loại</th>
          <th scope="col" class="text-center">Ngày tạo</th>
          <th scope="col" class="text-center">Ngày cập nhật</th>
          <th scope="col" class="text-center">Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="news in responseData" :key="news.id">
          <td class="text-center">{{ news.id }}</td>
          <td style="width: 90px;">
            <img :src="news.image" :alt="news.title" class="w-100" />
          </td>
          <td class="text-center">{{ news.title }}</td>
          <td class="text-center">{{ news.content }}</td>
          <td class="text-center">{{ news.type }}</td>
          <td class="text-center">{{ news.ngaytao }}</td>
          <td class="text-center">{{ news.ngaycapnhat }}</td>

          <td class="text-center">
           
            <button
              class="btn btn-success"
              style="margin-left: 10px"
              title="Duyệt"
              @click="duyetNews(news.id,'Đã duyệt')"
            >
                <i class="fa-solid fa-check"></i> Duyệt
            </button>
            <button
              class="btn btn-danger"
              style="margin-left: 10px"
              title="Từ chối"
              @click="duyetNews(news.id,'Từ chối')"
            >
              <i class="fa-solid fa-x"></i> Từ chối
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
    
    <script>
import Apis, { authApi } from "@/configs/Apis.js";
import { endpoints } from "@/configs/Apis.js";
export default {
  name: "NewsCxd",
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
      Apis.get(endpoints["NewsCxd"])
        .then((response) => {
          this.responseData = response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
    async duyetNews(id,status) {
      try {
        const formData = new FormData();
          formData.append("trangthai", status);
        const response = await authApi().put(`${endpoints["DuyetNews"]}/${id}`,formData);
        if (response.data === true) {
          alert("Duyệt thành công");
          this.fetchData();
          console.log("News deleted successfully");
        } else {
          console.error("Error deleting news");
        }
      } catch (error) {
        console.error("Error deleting news:", error);
      }
    },
  },
};
</script>
    
    
    