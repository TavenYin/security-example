1. 首先导入并编译maven工程

2. 导入SQL脚本

3. 启动项目，访问`http://localhost:8080/`

4. 登录后，访问`/hello`

当出现如下信息时，代表无权限访问
```
{"code":403,"mes":"access denied, u don't have permission"}
```

将下图数据is_delete修改为0，即可访问
![](https://gitee.com/yintianwen7/img-rep/raw/master/test/1568556720.jpg)