# Ứng dụng Quản lý Phần mềm - Software Management App

## Mô tả

Đây là ứng dụng Android quản lý thông tin các phần mềm trong phòng máy tính, sử dụng cơ sở dữ liệu SQLite.

## Cấu trúc Cơ sở dữ liệu

Bảng `softwarelist` gồm các cột:

- `_id` (int): Khóa chính, tự động tăng
- `soft_id` (Text): Mã phần mềm
- `soft_name` (Text): Tên phần mềm
- `version` (Text): Phiên bản phần mềm
- `publish` (Text): Nhà phát hành

## Chức năng chính

### 1. Tải danh sách phần mềm vào ListView (2 điểm)

- Hiển thị tất cả phần mềm trong ListView với adapter tùy chỉnh
- Tự động tải dữ liệu mẫu khi khởi tạo database
- Cập nhật danh sách khi quay về từ các activity khác

### 2. Option Menu (1 điểm)

- Menu với 2 tùy chọn: "Thêm phần mềm" và "Chỉnh sửa phần mềm"
- Icon trực quan và dễ sử dụng

### 3. Chức năng thêm mới phần mềm (2 điểm)

- Activity riêng `AddSoftwareActivity`
- Kiểm tra dữ liệu nhập vào đầy đủ
- Kiểm tra trùng lặp mã phần mềm
- Thông báo thành công/thất bại

### 4. Chức năng chỉnh sửa phần mềm (2 điểm)

- Activity riêng `EditSoftwareActivity`
- Tìm kiếm phần mềm theo `soft_id`
- Thông báo "Không có định danh phần mềm cần sửa trong CSDL" nếu không tìm thấy
- Cập nhật thông tin sau khi tìm thấy

## Cấu trúc File

### Model

- `Software.java`: Class model cho đối tượng phần mềm

### Database

- `DatabaseHelper.java`: Quản lý SQLite database
  - Tạo bảng
  - CRUD operations (Create, Read, Update, Delete)
  - Kiểm tra tồn tại

### View & Adapter

- `SoftwareAdapter.java`: Adapter tùy chỉnh cho ListView
- `software_item.xml`: Layout item cho mỗi phần mềm trong ListView

### Activities

- `MainActivity.java`: Activity chính với ListView và Options Menu
- `AddSoftwareActivity.java`: Activity thêm phần mềm mới
- `EditSoftwareActivity.java`: Activity chỉnh sửa phần mềm

### Layouts

- `activity_main.xml`: Layout chính với ListView
- `activity_add_software.xml`: Layout form thêm phần mềm
- `activity_edit_software.xml`: Layout form chỉnh sửa với tìm kiếm
- `software_item.xml`: Layout item hiển thị thông tin phần mềm

### Menu

- `main_menu.xml`: Định nghĩa Options Menu

## Dữ liệu mẫu

Ứng dụng tự động tạo dữ liệu mẫu bao gồm:

1. Microsoft Office 2021 (SW001)
2. Adobe Photoshop 2023 (SW002)
3. Visual Studio Code 1.85 (SW003)

## Hướng dẫn sử dụng

1. **Xem danh sách**: Mở ứng dụng để xem tất cả phần mềm
2. **Thêm phần mềm**: Chọn menu "Thêm phần mềm" → Nhập thông tin → Lưu
3. **Chỉnh sửa phần mềm**: Chọn menu "Chỉnh sửa phần mềm" → Nhập mã phần mềm → Tìm kiếm → Chỉnh sửa → Cập nhật

## Yêu cầu hệ thống

- Android API Level 24+ (Android 7.0)
- Java 11
- Gradle 8.0+
