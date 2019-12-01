# 短连接架构设计方案说明

## 功能概述

短连接服务系统Url Shorten Service, 简称USS, 主要提供长连接缩短为短连接、还原短连接等功能。

## 数据库设计

```sql

DROP TABLE IF EXISTS `tb_short_url`;
CREATE TABLE `tb_short_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `long_url` varchar(240) NOT NULL COMMENT '长码',
  `short_url` varchar(120) NOT NULL COMMENT '短码',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类型',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL COMMENT '更改时间',
  `deleted` int(11) DEFAULT '0' COMMENT '0-未删除 1已删除',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `long_url` (`long_url`) USING BTREE,
  KEY `short_url` (`short_url`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='短网址（Short URL）映射关系';
SET FOREIGN_KEY_CHECKS = 1;

```

## 短连接算法（参考微博短链接算法方式）

1. 将"原始链接（长链接）+ key(随机字符串,防止算法泄漏)"MD5后得到32位的一个字符串A。
2. 将上面的A字符串分为4段处理， 每段的长度为8 ， 比如四段分别为 M、N、O、P。
3. 将M字符串当作一个16进制格式的数字来处理， 将其转换为一个Long类型。 比如转换为L。
4. 此时L的二进制有效长度为32位， 需要将前面两位去掉，留下30位 ， 可以 & 0x3fffffff 进行位与运算得到想要的结果。（30位才能转换62进制，否则超出）
5. 此时L的二进制有效长度为30位， 分为6段处理， 每段的长度为5位。
6. 依次取出L的每一段（5位），进行位操作 & 0x0000003D 得到一个 <= 61的数字，来当做index。
7. 根据index 去预定义的62进制字符表里面去取一个字符， 最后能取出6个字符，然后拼装这6个字符成为一个6位字符串，作为短链接码拿出第②步剩余的三段，重复3-7 步。
8. 这样总共可以获得 4 个 8位字符串，取里面的任意一个就可作为这个长链接的短网址。
9. 串码添加校验位checksum，用于简单校验。所以总共7位码。

## 系统架构

基于 SpringBoot 框架快速开发，web前端采用 thymeleaf 方式（根据具体情况可以屏蔽），存储方式采用关系型数据库 MySql 作为持久化数据，数据采用 异步方式 写入数据，结合使用 Redis 缓存热点数据，整体架构支持高并发和高性能。

## 基本环境

1. 下载 JDK 8+, Maven 3.2+, Mysql 5.7+, Redis 3.2+
2. 导入数据表: tb_short_url.sql
2. 修改 SpringBoot 配置文件: application.yml

## 生产环境

建议：
1. 建议结合 Nginx 配置反向代理。
2. 建议 mysql、redis 为主从复制模式，将其配置支持高可用性。
3. 关闭web前端入口，让其只支持接口入口请求，并扩展添加接口签名校验。
4. 可在原短连接算法中添加校验位。
5. 结合具体需求，可添加至分布式架构模式。

## 测试

1. 长连接缩短为短连接 APIs: 
```
curl -H "Content-Type:application/json" -X POST --data '{"longUrl": "http://www.baidu/"}' http://localhost:8080/short/compress

```

```json

{
    "code": 200, 
    "msg": "ok", 
    "data": "http://localhost:8080/short/wkUZtF58"
}

```

2. 短链接还原长短连 APIs: 

```
curl http://localhost:8080/short/wkUZtF58

```

## 总结

虽然说这个项目可支持分布式项目，尽管最终只是一个简单的实现，但是在这个过程中还是学到了一些经验。由于我出道不久，初入IT江湖，资历浅雹，如有更好的方式或其他更好的解决方案，欢迎一起交流和讨论，在后续不断的学习中，会积累更多的经验，加油！


## 附加

分布式SpringCloud项目: [spring-cloud](https://github.com/romeoblog/spring-cloud) 

## 联系方式

#### 邮箱：
willluzheng@gmail.com

#### 微信： codelu521

![个人微信](http://cdn.willlu.cn/%E5%BE%AE%E4%BF%A1%E4%B8%AA%E4%BA%BA%E5%90%8D%E7%89%87.jpeg)