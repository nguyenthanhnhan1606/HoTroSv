<template>
    <div>
      <h1 class="text-center text-info">Quản lý danh sách người dùng</h1>
  
      <table class="table table-bordered">
        <thead class="thead-dark">
          <tr>
            <th scope="col" class="text-center">id</th>
            <th scope="col" class="text-center">Hình đại diện</th>
            <th scope="col" class="text-center">Tên</th>
            <th scope="col" class="text-center">Giới tính</th>
            <th scope="col" class="text-center">Ngày sinh</th>
            <th scope="col" class="text-center">Email</th>
            <th scope="col" class="text-center">SDT</th>
            <th scope="col" class="text-center">Vai trò</th>
            <th scope="col" class="text-center">Thao tác</th>


          </tr>
        </thead>
        <tbody>
          <tr v-for="u in responseData" :key="u.id">
            <td class="text-center">{{ u.id }}</td>
            <td style="width: 100px;">
              <img :src="u.avatar" :alt="u.name" class="w-100" style="height: 50px;" />
            </td>
            <td class="text-center">{{ u.name }}</td>
            <td class="text-center">{{ u.gioitinh }}</td>
            <td class="text-center">{{ u.ngaysinh }}</td>
            <td class="text-center">{{ u.email }}</td>
            <td class="text-center">{{ u.sdt }}</td>
            <td class="text-center" v-if="u.userRole==='ROLE_USER'">Người dùng</td>
            <td class="text-center" v-else>Người cố vấn</td>

            <td class="text-center">
              <router-link
                :to="{ name: 'userDetail', params: { id: u.id } }"
              >
                <button class="btn btn-success" title="Cập nhật">
                  <i class="fa fa-edit"></i> Cập nhật
                </button>
              </router-link>
  
             <button
                class="btn btn-danger"
                style="margin-left: 10px"
                title="Xóa"
                @click="deleteUser(u.id)"
              >
                <i class="fa fa-trash"></i> Xóa
              </button> 
            </td>
          </tr>
        </tbody>
      </table>
      <router-link :to="{ name: 'addUserAdviser' }">
        <button class="btn btn-info" title="Thêm">
          <i class="fa-solid fa-plus"></i> Thêm tài khoản tư vấn
        </button>
      </router-link>
    </div>
  </template>
    
    <script>
  import Apis, { authApi } from "@/configs/Apis.js";
  import { endpoints } from "@/configs/Apis.js";
  export default {
    name: "ListUser",
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
        Apis.get(endpoints["ListUser"])
          .then((response) => {
            this.responseData = response.data;
          })
          .catch((error) => {
            console.error("Error fetching data:", error);
          });
      },
      async deleteUser(id) {
        try {
          // Gọi API RecycleBin với id của u
          const response = await authApi().put(`${endpoints['RecycleBinUser']}/${id}`);
          if (response.data) {
            alert("Xóa thành công")
            this.fetchData();
            console.log("user deleted successfully");
          } else {
            console.error("Error deleting user");
          }
        } catch (error) {
          console.error("Error deleting user:", error);
        }
      },
    },
  };
  </script>
    
    
    