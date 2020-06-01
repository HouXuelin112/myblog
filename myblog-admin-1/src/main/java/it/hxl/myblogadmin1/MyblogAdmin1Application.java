package it.hxl.myblogadmin1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("it.hxl.myblogadmin1.mapper")
public class MyblogAdmin1Application {

    public static void main(String[] args) {
        SpringApplication.run(MyblogAdmin1Application.class, args);
    }

}
