本目录用于本地启动测试用的Docker MySQL容器。

### 用法
- *不建议直接使用以下命令，而是优先使用Gradle的docker-compose插件*

|功能|命令|备注|
| --- | --- | --- |
|启动MySQL|`./start.sh`|关闭已有MySQL,启动全新|
|关闭MySQL|`./stop.sh`|同时删除network和volume|
|登录MySQL|`./login.sh`||
|清空数据库|`./clean.sh`|重建ecommerce_order_mysql表|
