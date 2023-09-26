<template>
  <div>
    <h1 class="text-center text-info">Quản lý lịch live</h1>
    <table class="table table-bordered">
      <thead class="thead-dark">
        <tr>
          <th scope="col" class="text-center">id</th>
          <th scope="col" class="text-center">Tiêu đề</th>
          <th scope="col" class="text-center">Mô tả</th>
          <th scope="col" class="text-center">Ngày bất đầu live</th>
          <th scope="col" class="text-center">
            Thời gian được phép đặc câu hỏi
          </th>
          <th scope="col" class="text-center">Link live</th>
          <th scope="col" class="text-center">Thao tác</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="l in lives" :key="l.id">
          <td class="text-center">{{ l.id }}</td>
          <td class="text-center">{{ l.title }}</td>
          <td class="text-center">{{ l.description }}</td>
          <td class="text-center">{{ l.ngay }}</td>
          <td class="text-center">{{ l.thoigian }}</td>
          <td class="text-center">{{ l.path }}</td>

          <td class="text-center">
            <router-link :to="{ name: 'liveDetail', params: { id: l.id } }">
              <button class="btn btn-success" title="Cập nhật">
                <i class="fa fa-edit"></i> Cập nhật
              </button>
            </router-link>

            <button
              class="btn btn-danger"
              style="margin-left: 10px"
              title="Xóa"
              @click="deleteLive(l.id)"
            >
              <i class="fa fa-trash"></i> Xóa
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link :to="{ name: 'addLive' }">
      <button class="btn btn-info" title="Thêm">
        <i class="fa-solid fa-plus"></i> Thêm
      </button>
    </router-link>
  </div>
</template>
<script>
import Apis, { authApi, endpoints } from "@/configs/Apis";
export default {
  name: "Live",
  data() {
    return {
      lives: null,
    };
  },
  mounted() {
    this.fecthData();
  },
  methods: {
    async fecthData() {
      try {
        const response = await Apis.get(endpoints["Live"]);
        this.lives = response.data;
      } catch (error) {
        console.error("Error get live:", error);
      }
    },
    async deleteLive(id) {
      try {
       const result= window.confirm("Bạn có chắc muốn xóa ko?");
        if (result) {
          const response = await authApi().put(
            `${endpoints["RecycleBinLive"]}/${id}`
          );
          if (response.data === true) {
            alert("Xóa thành công");
            this.fecthData();
            console.log("Live deleted successfully");
          } else {
            console.error("Error deleting live");
          }
        }
      } catch (error) {
        console.error("Error deleting live:", error);
      }
    },
  },
};
</script>
