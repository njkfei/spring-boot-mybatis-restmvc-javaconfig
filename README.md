title: spring boot mybatis restmvc集成--完全基于注解
date: 2016.03.10
categories: java #分类
tags: [java, mybatis, springboot]
toc: true
---
  今天在网上找了一下spring boot mybatis集成，发现baidu的链接里面的demo已经过时，比自己目前做的还落后．因此，自己尝试搭一下环境．
个人的感受为：
* spring boot确实非常爽，比基于java config还轻松，原来以为基于java config配置已经挺爽了，结果spring boot 更进一步解放和生产力．

### 本示例技术要素
* spring boot
* tomcat 作为web容器，已被spring boot集成
* mybatis 数据库访问框架
* java config进行IOC配置

特别说明，本demo没有XML配置文件(logback.xml除外)．

### 使用步骤
* 导入依赖
* 创建model
* 创建mapper DAO
* 创建控制器
* 指定入口
* 启动运行


### 导入依赖
```
<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<springframework.version>4.2.4.RELEASE</springframework.version>
	</properties>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.3.3.RELEASE</version>
	</parent>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<!--支持使用 JDBC 访问数据库 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<!--添加适用于生产环境的功能，如性能指标和监测等功能。 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${springframework.version}</version>
		</dependency>
		<!-- mysql驱动 -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.29</version>
		</dependency>
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>1.0.2</version>
		</dependency>
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.3.0</version>
		</dependency>
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.2.3</version>
			<type>jar</type>
		</dependency>
```

### 创建model
	见model包下面的User.java文件
```
public class User {
	private int user_id;
	private int user_type;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	
}
```
### 创建mapper DAO
	见dao包下面的UserDAO.java
```
public interface UserDAO {

	@Select("select * from ysyy_user where user_id=#{user_id} and user_type=#{user_type}")
	User getUser(@Param("user_id")int user_id,@Param("user_type")int user_type);
}
```

### 创建控制器
	见controller下面的UsrController.java
```
@RestController
@EnableAutoConfiguration
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping("/user")
	@ResponseBody
	User getUser(){
		return userDAO.getUser(971, 2);
	}

/*	public static void main(String[] args){
		SpringApplication.run(SampleController.class,args);
	}*/
}
```

### 指定入口
	见App.java
```
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	 SpringApplication.run(App.class, args);
    }
}
```

### 启动运行
```
mvn clean install
java -jar spring-boot-mybatis-web
```

### 测试
在浏览器中输入
```
localhost:8080/user
```

### 返回结果
```
{"user_id":971,"user_type":2}
```