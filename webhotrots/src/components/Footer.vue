<template>
  <!-- Remove the container if you want to extend the Footer to full width. -->
  <!-- Footer -->
  <footer
    class="text-center text-lg-start text-white"
    style="background-color: #45526e"
  >
    <!-- Grid container -->
    <div class="container p-4 pb-0">
      <!-- Section: Links -->
      <section class="">
        <!--Grid row-->
        <div class="row">
          <!-- Grid column -->
          <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
            <h6 class="text-uppercase mb-4 font-weight-bold">DO AN NGANH</h6>
            <p>Đây là đồ án ngành</p>
          </div>
          <!-- Grid column -->

          <hr class="w-100 clearfix d-md-none" />

          <!-- Grid column -->
          <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
            <h6 class="text-uppercase mb-4 font-weight-bold">Công nghệ</h6>
            <p>
              <a class="text-white">Spring Boot</a>
            </p>
            <p>
              <a class="text-white">VUEJS</a>
            </p>
          </div>
          <!-- Grid column -->

          <!-- Grid column -->
          <hr class="w-100 clearfix d-md-none" />

          <!-- Grid column -->
          <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
            <h6 class="text-uppercase mb-4 font-weight-bold">Liên hệ</h6>
            <p><i class="fas fa-home mr-3"></i> TP. HỒ CHÍ MINH</p>
            <p><i class="fas fa-envelope mr-3"></i> nhan.nt166@gmail.com</p>
            <p><i class="fas fa-phone mr-3"></i> +84 362768613</p>
          </div>
          <!-- Grid column -->
        </div>
        <!--Grid row-->
      </section>
      <!-- Section: Links -->

      <hr class="my-3" />

      <!-- Section: Copyright -->
      <section class="p-3 pt-0">
        <div class="row d-flex align-items-center">
          <!-- Grid column -->
          <div class="col-md-7 col-lg-8 text-center text-md-start">
            <!-- Copyright -->
            <div class="p-3">
              © 2023 Copyright:
              <a class="text-white" href="#">NGUYEN THANH NHAN</a>
            </div>
          </div>
          <!-- Grid column -->

          <!-- Grid column -->
          <div class="col-md-5 col-lg-4 ml-lg-0 text-center text-md-end">
            <!-- Facebook -->
            <a
              href="https://www.facebook.com/profile.php?id=100025011245018"
              target="_blank"
              class="btn btn-outline-light btn-floating m-1"
              role="button"
              ><i class="fab fa-facebook-f"></i
            ></a>

            <!-- Twitter -->
            <a
              href="https://github.com/nguyenthanhnhan1606"
              target="_blank"
              class="btn btn-outline-light btn-floating m-1"
              role="button"
              ><i class="fab fa-github"></i
            ></a>

            <!-- Google -->
            <a
              href="https://mail.google.com/mail/u/0/#inbox"
              target="_blank"
              class="btn btn-outline-light btn-floating m-1"
              role="button"
              ><i class="fab fa-google"></i
            ></a>
          </div>
          <!-- Grid column -->
        </div>
      </section>
      <!-- Section: Copyright -->
    </div>
    <!-- Grid container -->
  </footer>
  <!-- Footer -->
  <!-- End of .container -->
</template>
<script>
export default {
  name: "MyFooter",
};
</script>
