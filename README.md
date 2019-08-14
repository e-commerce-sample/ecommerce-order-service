# 项目简介
本项目是Ecommerce系统的订单（Order）子系统，用于接受用户订单。

Ecommerce项目包括：

|代码库|用途|地址|
| --- | --- | --- |
|order-backend|Order服务|[https://github.com/e-commerce-sample/order-backend](https://github.com/e-commerce-sample/order-backend)|
|product-backend|Product服务|[https://github.com/e-commerce-sample/product-backend](https://github.com/e-commerce-sample/product-backend)|
|inventory-backend|Inventory服务|[https://github.com/e-commerce-sample/inventory-backend](https://github.com/e-commerce-sample/inventory-backend)|
|common|共享依赖包|[https://github.com/e-commerce-sample/common](https://github.com/e-commerce-sample/common)|
|devops|基础设施|[https://github.com/e-commerce-sample/devops](https://github.com/e-commerce-sample/devops)|

# 技术选型
Spring Boot、Gradle、MySQL、Junit 5、Rest Assured、Docker、RabbitMQ

# 本地构建

在本地构建之前必须完成以下步骤：
- Pull最新[devops](https://github.com/e-commerce-sample/devops)代码
- 命令行进入`devops`项目的`local/rabbitmq`目录
- 运行`./start-rabbitmq.sh`，用于启动RabbitMQ，整个Ecommerce下的所有服务只需启动RabbitMQ一次

|功能|命令|备注|
| --- | --- | --- |
|生成IntelliJ工程|`./idea.sh`|自动打开IntelliJ|
|本地运行|`./run.sh`|自动启动MySQL，开启HTTP 8080端口，监听5005调试端口|
|本地构建|`./local-build.sh`|启动启动MySQL，运行所有类型的自动化测试|
|停止MySQL|`./gradlew composeDown`|将清空所有数据|
|手动启动MySQL|`./gradlew composeUp`||

# 领域对象
|领域对象|中文名|业务功能|
| --- | --- | --- |
|Order|订单|表示用于下的订单，包含多个产品及其数量|

# 测试策略
|测试类型|代码目录|测试内容|
| --- | --- | --- |
|单元测试|`src/test/java`|包含核心领域模型（包含领域对象和Factory类）的测试|
|组件测试|`src/componentTest/java`|用于测试一些核心的组件级对象，比如Repository|
|API测试|`src/apiTest/java`|模拟客户端调用API|

# 技术架构
技术架构图

# 部署架构
部署架构图

# 外部依赖
列出项目所依赖的其他系统，比如订单系统依赖于会员系统。

# 环境信息
列出各个环境的访问方式，数据库连接等。

# 编码实践
列出常用的公共的编码实践方式。

# FAQ
常见问题列表

