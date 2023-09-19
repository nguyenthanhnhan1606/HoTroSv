<template>
  <HeaderAdmin />

  <div class="container-fluid">
  <div class="row" >
    <div class="col-sm-2" style="padding-left: 5px;">
      <Sidebar class="sidebar" />
    </div>
    <div class="col-sm-10 mt-3" style="padding: 0 ;">
      <router-view/>
    </div>
  </div>
</div>
</template>

<script>
import HeaderAdmin from "@/admin/components/static/HeaderAdmin.vue";
import Sidebar from "../components/static/Sidebar.vue";

export default {
  name: "HomeAdmin",
  components: {
    HeaderAdmin,
    Sidebar,
  },
};
</script>

<style>
.sidebar {
  height: 600px;
}
</style>