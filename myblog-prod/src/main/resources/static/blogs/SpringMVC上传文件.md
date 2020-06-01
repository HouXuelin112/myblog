# SpringMVC上传文件（maven）

@[TOC]

### 1.在pom.xml中配置所需依赖

pom.xml

```xml
<!--springmvc-->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>${spring.version}</version>
</dependency>
<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.3.3</version>
</dependency>
<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.4</version>
</dependency>
<!-- servlet api -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>2.5</version>
</dependency>
```



### 2.创建springmvc配置文件

springmvc-config.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="it.hxl.controller"/>
    <!--视图解析器-->
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    <!--
        CommonsMultipartResolver会将request的类型改变（MultipartHttpServletRequest）
        所以它被配置时，parseRequest(request)得到的将是一个空List
    -->
    <bean class="org.springframework.web.multipart.commons.CommonsMultipartResolver" id="multipartResolver">
        <property name="defaultEncoding" value="UTF-8"/>  <!--解决上传文件过程中的中文乱码问题-->
        <property name="maxInMemorySize" value="1048567"/>
    </bean>

    <mvc:default-servlet-handler/>
    <mvc:annotation-driven/>

</beans>
```

### 3.编写web.xml文件

web.xml

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>



<!--  <servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>*.png</url-pattern>
  </servlet-mapping>
  <servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>*.jpg.</url-pattern>
  </servlet-mapping>-->

  <!--编码过滤器-->
  <filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/</url-pattern>
  </filter-mapping>
  <!--配置前端控制器-->
  <servlet>
    <servlet-name>fileupload</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!--sprinmvc配置文件的位置-->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc-config.xml</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>fileupload</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

</web-app>

```

### 4.编写上传文件和成功上传页面



upload.jsp

```jsp
<%--
  Created by IntelliJ IDEA.
  User: hxl112
  Date: 2019/12/5
  Time: 12:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<!--method必须设置为post，必须有enctype属性并设置其值为multipart/form-data-->
传统方式上传：
    <form action="upload1" method="post" enctype="multipart/form-data">
        <input type="text" name="name">
        <input type="file" name="upload"/>
        <input type="submit" value="提交"/>
    </form>
    <br>
	<!--method必须设置为post，必须有enctype属性并设置其值为multipart/form-data-->
 SpringMVC方式上传：  
    <form action="upload2" method="post" enctype="multipart/form-data">
        <input type="text" name="name">
        <input type="file" name="upload"/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>


```

success.jsp

```jsp
<%--
  Created by IntelliJ IDEA.
  User: hxl112
  Date: 2019/12/5
  Time: 12:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>成功界面</title>
    <style type="text/css">
        img{
            width: 200px;
            height: 100px;
    		vertical-align: middle;
        }
    </style>
</head>
<body>
上传成功${name}
<img src="${imgPath}">
</body>
</html>
```



### 5.编写controller

MyController.java

```java
package it.hxl.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class MyController {


    @RequestMapping("/upload")
    public String toTest(){
        return "upload";
    }

    @RequestMapping("/upload1")
    public String uploadFile1(HttpServletRequest request) throws Exception {
        //上传文件放的位置
        String path = request.getSession().getServletContext().getRealPath("/upload/");
        File file = new File(path);
        //判断文件夹是否存在,不存在则创建
        if(!file.exists()){
            file.mkdirs();
        }
        if(ServletFileUpload.isMultipartContent(request)){//如果form表单的enctype=multipart/form-data
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(10 * 1024);
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解析request
            List<FileItem> items= upload.parseRequest(request);
            System.out.println(items);
            for (FileItem item: items){
                if (item.isFormField()){
                    //如果是文本项
                    String name = item.getString("utf-8");//普通表单的内容
                    request.setAttribute("name", name);
                }else{
                    String filename = item.getName();
                    filename = UUID.randomUUID().toString().replace("-", "") + "_" + filename;
                    String imgPath = request.getSession().getServletContext().getContextPath() + "/upload/" +  filename;
                    request.setAttribute("imgPath", imgPath);
                    item.write(new File(path, filename));
                    item.delete();
                }
            }
        }
        return "success";
    }
    @RequestMapping("/upload2")
    public String uploadFile2(HttpSession session, String name, MultipartFile upload) throws IOException {
        //上传文件放的位置
        String path = session.getServletContext().getRealPath("/upload/");
        File file = new File(path);
        //判断文件夹是否存在,不存在则创建
        if(!file.exists()){
            file.mkdirs();
        }
        session.setAttribute("name", name);
        //MultipartFile的getName()返回的是form表单的name属性,相当于FileItem的getFieldName()
        String filename = upload.getOriginalFilename();
        filename = UUID.randomUUID().toString().replace("-", "") + "_" +filename;
        session.setAttribute("imgPath", session.getServletContext().getContextPath() + "/upload/" + filename);
        upload.transferTo(new File(path, filename));
        return "success";
    }
}

```

### 6.测试

这里测试springMVC方式上传：

![image-20191205215057388](/image/blogs/20191205215053.png)

点击提交后的结果：



![image-20191205215411136](/image/blogs/20191205215407.png)

若要测试传统方式上传文件请先将springmvc-config.xml中注入的CommonsMultipartResolver注释掉，否则parseRequest()方法将返回一个空的List，导致不能成功上传





