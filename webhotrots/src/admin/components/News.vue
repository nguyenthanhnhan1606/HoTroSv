<template>
    <div>
      <h1 class="text-center text-info">Quản lý danh sách các thông tin</h1>
  
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
            <td style="width: 100px">
              <img :src="news.image" :alt="news.title" class="w-100" />
            </td>
            <td class="text-center">{{ news.title }}</td>
            <td class="text-center">{{ news.content }}</td>
            <td class="text-center">{{ news.type }}</td>
            <td class="text-center">{{ news.ngaytao }}</td>
            <td class="text-center">{{ news.ngaycapnhat }}</td>


            <td class="text-center">
              <router-link
                :to="{ name: 'newsDetail', params: { id: news.id } }"
              >
                <button class="btn btn-success" title="Cập nhật">
                  <i class="fa fa-edit"></i> Cập nhật
                </button>
              </router-link>
  
              <button
                class="btn btn-danger"
                style="margin-left: 10px"
                title="Xóa"
                @click="deleteNews(news.id)"
              >
                <i class="fa fa-trash"></i> Xóa
              </button>
            </td>
          </tr>
        </tbody>
      </table>
      <router-link :to="{ name: 'addNews' }">
      <button class="btn btn-info" title="Thêm">
        <i class="fa-solid fa-plus"></i> Thêm thông tin
      </button>
    </router-link>
    </div>
  </template>
    
    <script>
  import Apis from "@/configs/Apis.js";
  import { endpoints } from "@/configs/Apis.js";
  export default {
    name: "News",
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
        Apis.get(endpoints["News"])
          .then((response) => {
            this.responseData = response.data;
          })
          .catch((error) => {
            console.error("Error fetching data:", error);
          });
      },
      async deleteNews(id) {
        try {
          // Gọi API RecycleBin với id của banner
          const response = await Apis.put(`${endpoints['RecycleBinNews']}/${id}`);
          if (response.data===true) {
            alert("Xóa thành công")
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
    
    
    