# MovieApp sử dụng để xem các phim đang phổ biến và xem trailer của chúng
  app sử dụng các công nghệ ở phía dưới và sử dụng kiến trúc MVVM kết hợp clean architecture
## Công nghệ sử dụng
- LiveData và ViewModel: Triển khai mô hình MVVM
- Kotlin coroutine
- Dagger Hilt
- Glide
## Cấu trúc dự án
- core: Để chứa các constantes, các module dependency injection
- data: để thực hiện tương tác với lớp dữ liệu lấy từ local và remote, ở đây ta chỉ dùng với api
- domain: repository và các use case
- ui: lớp giao diện người dùng

## Review
- Màn home ở ngoài sẽ chứa các list movie đang hot, đang chiếu
  
    ![Image text](https://github.com/tranxuanbach021202/MovieApp/blob/dev/app/src/assets/detail_review.jpg)

- Màn detail khi người dùng click vào 1 movie trên màn home nó sẽ hiển thị trailer và các thông tin về phim
  
    ![Image text](https://github.com/tranxuanbach021202/MovieApp/blob/dev/app/src/assets/home_review.jpg)
