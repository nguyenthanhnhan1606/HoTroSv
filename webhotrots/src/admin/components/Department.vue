<template>
  <div>
    <h1 class="text-center text-info">Quản lý danh sách các thông tin</h1>

    <table class="table table-bordered">
      <thead class="thead-dark">
        <tr>
          <th scope="col" class="text-center">ID</th>
          <th scope="col" class="text-center">Tên khoa</th>
          <th scope="col" class="text-center">Giới thiệu</th>
          <th scope="col" class="text-center">Chương trình đào tạo</th>
          <th scope="col" class="text-center">Trung bình điểm 5 năm</th>
          <th scope="col" class="text-center">Website</th>
          <th scope="col" class="text-center">Video</th>
          <th scope="col" class="text-center">Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="k in responseData" :key="k.id">
          <td class="text-center">{{ k.id }}</td>
          <td class="text-center">{{ k.tenkhoa }}</td>
          <td class="text-center">
            <span v-b-tooltip.hover :title="k.mota">
              {{ k.mota.substring(0, 20) }}{{ k.mota.length > 50 ? "..." : "" }}
            </span>
          </td>
          <td class="text-center">
            <span v-b-tooltip.hover :title="k.motaCTDT">
              {{ k.motaCTDT.substring(0, 20)
              }}{{ k.motaCTDT.length > 50 ? "..." : "" }}
            </span>
          </td>
          <td class="text-center">{{ k.trungbinhdiem }}</td>
          <td class="text-center">
            <a :href=" k.website " target="_blank" class="nav-link">{{ k.website }}</a>
          </td>
          <td class="text-center">
            <div v-if="k.video">
              <!-- <i class="fas fa-video"></i> -->
              <video width="80" height="40" controls>
                <source
                  :src="k.video"
                  type="video/mp4"
                />
              </video>
            </div>
            <div v-else>
              Không có video nào
            </div>
          </td>
          <td class="text-center">
            <router-link :to="{ name: 'departmentDetail', params: { id: k.id } }">
              <button class="btn btn-success" title="Cập nhật">
                <i class="fa fa-edit"></i> Cập nhật
              </button>
            </router-link>
            <button
              class="btn btn-danger"
              style="margin-left: 10px"
              title="Xóa"
              @click="confirmDelete(k.id)"
            >
              <i class="fa fa-trash"></i> Xóa
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link :to="{ name: 'addDep' }">
      <button class="btn btn-info" title="Thêm">
        <i class="fa-solid fa-plus"></i> Thêm khoa
      </button>
    </router-link>
  </div>
</template>
  
  
  <script>
import Apis, { authApi } from "@/configs/Apis.js";
import { endpoints } from "@/configs/Apis.js";

export default {
  name: "Department",
  data() {
    return {
      responseData: null,
      deletingId: null, // Để lưu trữ ID của thông tin đang được xác nhận xóa
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      Apis.get(endpoints["Department"])
        .then((response) => {
          this.responseData = response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
    async confirmDelete(id) {
      // Hiển thị cửa sổ xác nhận xóa thông tin với ID tương ứng
      if (confirm("Bạn có chắc chắn muốn xóa thông tin này không?")) {
        this.deleteNews(id);
      }
    },
    async deleteNews(id) {
      try {
        // Gọi API RecycleBin với id của thông tin
        const response = await authApi().put(`${endpoints["RecycleBinNews"]}/${id}`);
        if (response.data === true) {
          alert("Xóa thành công");
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
  