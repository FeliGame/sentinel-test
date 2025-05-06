# Spring Cloud 微服务架构示例

本项目是一个基于 Spring Cloud 和 Sentinel 的微服务架构示例，包含服务的限流、熔断、降级和流量分隔。

## 项目结构
- `service-provider`：服务提供者，提供 `/hello` 接口。
- `service-consumer`：服务消费者，调用 `service-provider` 的 `/hello` 接口。

## 环境要求
- Java 1.8+
- Maven
- Sentinel Dashboard（Docker 容器，开放dashboard所在的8858端口以供服务注册）
- JMeter 执行并发测试脚本

## 运行步骤
1. **启动 Sentinel Dashboard**：确保你已经启动了 Sentinel Dashboard 的 Docker 容器。
2. **启动 `service-provider`**：
   - 进入 `service-provider` 目录。
   - 运行 `mvn spring-boot:run` 启动服务。
3. **启动 `service-consumer`**：
   - 进入 `service-consumer` 目录。
   - 运行 `mvn spring-boot:run` 启动服务。
4. **测试服务**：
   - 访问 `http://localhost:8081/call-provider` 调用服务，应该能出现`http://localhost:8080/hello` 的页面内容。

## 服务治理
### 限流
可以在 Sentinel Dashboard 中配置限流规则，对服务的访问进行限制。例如，设置每秒最大请求数(QPS)，当请求超过该阈值时，选择快速失败，后续请求将被拒绝。

### 熔断
当 `service-provider` 不可用时，`service-consumer` 会调用 `ProviderClientFallback` 进行熔断降级，可以在熔断期间登录consumer检查是否进入fallback。
