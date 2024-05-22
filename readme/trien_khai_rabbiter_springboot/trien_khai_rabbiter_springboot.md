### Cấu hình kết nối Rabbiter cùng Springboot

- **Container RabbitMQ sẽ được tạo và chạy trên máy tính**:docker run -d --name my_rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3.13.2-management

- **Cấu hình springboot theo trang tham khảo:**  [Spring Cloud - Messaging using RabbitMQ in Spring Boot Application](https://www.springcloud.io/post/2022-03/messaging-using-rabbitmq-in-spring-boot-application/#gsc.tab=0)
