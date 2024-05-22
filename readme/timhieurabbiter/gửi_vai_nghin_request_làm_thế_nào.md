# Nếu gửi 10000 email trong Rabbiter

## Trong trường hợp custom nhận được 10000

### Thiết kế Logic cho Hệ thống Gửi và Nhận Email Trong trường hợp custom nhận được 10000

### 1. Thiết kế cấu trúc dữ liệu:

- Xác định các thông tin cần thiết cho mỗi email, bao gồm địa chỉ người nhận, tiêu đề, nội dung, v.v.

### 2. Xây dựng Producer:

- Thiết lập producer để gửi các yêu cầu gửi email vào queue.
- Đảm bảo rằng producer gửi thông điệp một cách đồng bộ và tin cậy.

### 3. Xây dựng Consumer:

- Thiết lập consumer để nhận và xử lý các yêu cầu gửi email từ queue.
- Xác định cách xử lý các yêu cầu gửi email, bao gồm việc gửi email thực sự, xử lý lỗi và gửi lại email nếu cần.

### 4. Xử lý lỗi và Retry Logic:

- Triển khai cơ chế retry logic để xử lý các email không gửi được do lỗi mạng hoặc lỗi hệ thống khác.
- Đảm bảo rằng hệ thống có thể xử lý các email không gửi được một cách hiệu quả và đảm bảo rằng chúng không bị mất.

### 5. Xây dựng Dead Letter Exchange (DLX):

- Sử dụng DLX để xử lý các email không gửi được sau một số lần retry.
- Chuyển các email không gửi được vào DLX để xử lý sau.

### 6. Giám sát và Tối ưu hóa:

- Sử dụng công cụ giám sát để theo dõi hiệu suất và trạng thái của hệ thống gửi và nhận email.
- Điều chỉnh cấu hình và tối ưu hóa logic để đảm bảo hệ thống hoạt động ổn định và hiệu quả.

## Trong trường nhận 10000 nghìn mà hệ thống gửi 20000

10000 request sẽ dk rabbiter00 sử lí trong 13h
sau 13h sẽ sử lí 10000 request còn lại
Nó tồn động cung dk
10000 requets giờ cao điểm cao đi thì sẽ hồi lại.
Hết h cao điểm sẽ giải quyết lại các vấn đề cao điểm

## Trong trường nhận 10000 nghìn mà hệ thống bi treo die

Trong phần lớn các trường hợp, dữ liệu trong hàng đợi sẽ được giữ lại và chờ đợi để được xử lý khi hệ thống hoạt động trở lại.
