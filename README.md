# HoTroSv
## Đầu tiên hãy import cơ sỡ dữ liệu
1. Sử dụng MySQL
2. Tạo cơ sở dữ liệu có tên httuyensinhdb
3. Click vào server ![Alt text](image.png)
4. Click tiếp vào Data Import Và chọn file database, cơ sở dữ liệu httuyensinh ![Alt text](image-1.png)
5. Start Import
6. Hoàn thành
## Hướng dẫn cài đặt hệ thống HoTroTS Back-end
1. Sử dụng IntelliJ IDE hoặc Eclipse có cài đặt JDK để chạy máy ảo java.
2. Mở project **BE_SPRING_BOOT** và build. có thể sử dụng giao diện hoặc môi trường dòng lệnh terminal như sau:

    mvn clean install

3. Trong file application.properties

- Sửa **spring.datasource.username** thành username đăng nhập mysql của máy đang sử dụng 
- Sửa **spring.datasource.password** thành password đăng nhập mysql của máy đang dùng   
5. Nhấn vào icon run trên giao diện của IntelliJ IDE ▶️ Run 
6. Hoàn tất
## Hướng dẫn cài đặt hệ thống HoTroTS Front-end
1. Sử dụng Visual Studio Code
2. Mở project **FE_VUEJS** 
3. Mở terminal trong Visual Studio Code và gõ lệnh **npm install**
4. Gõ lệnh **npm run serve**
5. Hoàn tất