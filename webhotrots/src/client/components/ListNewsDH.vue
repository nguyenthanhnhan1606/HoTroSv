<template>
    <div class="d-flex justify-content-center align-items-center" v-if="newsdh">
      <div class="card d-flex mt-1" style="width: 95%; background-color: azure">
        <div class="card-body">
          <!--Table-->
          <table class="table table-hover table-forum text-center">
            <!--Table head-->
            <thead>
              <tr>
                <th></th>
                <th class="text-primary h3">Tuyển sinh sau đại học</th>
                <th></th>
              </tr>
            </thead>
  
            <tbody>
              <tr v-for="newsdh in newsdhs" :key="newsdh.id">
                <td scope="row" class="text-nowrap" style="width: 150px">
                  <img :src="newsdh.image" :alt="newsdh.title" class="w-100" />
                </td>
                <td class="text-start">
                  <div class="h5 text-success" style="margin: 0;">
                    {{ newsdh.title }}
                  </div>
                  <div class="my-2 mt-1">
                      <div style="font-size:large;">
                          <span> {{ newsdh.content }} </span>
                      </div>
                    <span>Ngày cập nhật: {{ newsdh.ngaycapnhat }}</span>
                  </div>
                </td>
                <td>
                  <a href="#" class="text-dark">
                    <i class="fas fa-comments ml-1"></i>
                  </a>
                </td>
              </tr>
            </tbody>
          </table>
  
          <!--Bottom Table UI-->
          <div class="d-flex justify-content-center">
            <!--Pagination -->
            <nav class="my-2 pt-2">
              <ul class="pagination pagination-circle pg-info mb-0">
                <!--First-->
                <li class="page-item clearfix d-none d-md-block">
                  <a href="#" class="page-link waves-effect"> First </a>
                </li>
                <!--Arrow left-->
                <li class="page-item">
                  <a
                    href="#"
                    class="page-link waves-effect"
                    aria-label="Previous"
                  >
                    <span aria-hidden="true">«</span>
                    <span class="sr-only"> Previous </span>
                  </a>
                </li>
                <!--Numbers-->
                <li v-for="i in 5" :key="i" class="page-item">
                  <a href="#" class="page-link waves-effect">{{ i }}</a>
                </li>
                <!--Arrow right-->
                <li class="page-item">
                  <a href="#" class="page-link waves-effect" aria-label="Next">
                    <span aria-hidden="true">»</span>
                    <span class="sr-only">Next</span>
                  </a>
                </li>
                <!--First-->
                <li class="page-item clearfix d-none d-md-block">
                  <a href="#" class="page-link waves-effect"> Last </a>
                </li>
              </ul>
            </nav>
            <!--/Pagination -->
          </div>
        </div>
      </div>
    </div>
    <div v-else class="text-center bg-info h2 pb-2" style="margin: 0;">Không có tin nào!!</div>
  </template>
<script>
import Apis, { endpoints } from '@/configs/Apis';
export default {
  name: "ListNewsDH",
  data(){
    return {
        newsdhs:null,
    }
  },
  mounted() {
    this.fetchDataDH();
  },
  methods: {
    async fetchDataDH() {
      await Apis.get(endpoints["NewsDH"])
        .then((response) => {
          this.newsdhs = response.data;
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
  },
};
</script>