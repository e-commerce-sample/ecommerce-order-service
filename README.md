# 项目简介
本项目是ecommerce系统的订单子系统，用于向用户展示产品并接受用户订单。

# 技术选型
Spring Boot、Gradle、MySQL、Junit 5、Rest Assured、Docker

# 本地构建
|功能|命令|备注|
| --- | --- | --- |
|生成IntelliJ工程|`./idea.sh`|自动打开IntelliJ|
|本地运行|`./run.sh`|监听5005调试端口|
|本地构建|`./local-build.sh`|运行所有类型的自动化测试|
|停止数据库|`./gradlew composeDown`|将清空所有数据|
|手动启动数据库|`./gradlew composeUp`||

# 领域对象
|领域对象|中文名|业务功能|
| --- | --- | --- |
|Product|产品|包含名称和价格|
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