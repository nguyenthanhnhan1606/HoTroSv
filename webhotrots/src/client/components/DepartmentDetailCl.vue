<template>
    <div class="container mb-5" v-if="department">
        <h1 class="text-center text-primary">{{ department.tenkhoa }}</h1>
        <h5 class="text-danger">1. Giới thiệu chung</h5>
        <p style="line-height: 1.5;">{{ department.mota }}</p>
        <h5 class="text-danger">2. Chương trình đào tạo</h5>
        <p style="line-height: 1.5;">{{ department.motaCTDT }}</p>
        <h5 class="text-danger">3. Website khoa</h5>
        <p >Website: <span><strong class="text-primary">{{ department.website }}</strong></span></p>
        <h5 class="text-danger">4. Điểm trung bình 5 năm gần nhất</h5>
        <p >Điểm trúng tuyển trung bình: <span><strong class="text-primary">{{ department.trungbinhdiem }}</strong></span></p>
        <h5 class="text-danger">5. Video giới thiệu khoa</h5>
        <h6>  <div v-if="department.video">
              <!-- <i class="fas fa-video"></i> -->
              <video class="w-100" controls>
                <source
                  :src="department.video"
                  type="video/mp4"
                />
              </video>
            </div>
            <div v-else>
              Không có video nào
            </div></h6>
    </div>

</template>
<script>
import Apis, { endpoints } from '@/configs/Apis';
export default {
  name: "DepartmentDetailCl",
  data() {
    return {
      department:null,
    };
  },
  async created() {
    const id = this.$route.params.id;
    try {
      this.news = await this.fetchDataDep(id);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  },

  methods: {
    async fetchDataDep(id) {
      await Apis.get(`${endpoints["Department"]}/${id}`)
        .then((response) => {
          this.department= response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
  },
  watch: {
    '$route.params.id': {
      handler(newid) {
        this.fetchDataDep(newid);
      },
    },
  },
};
</script>