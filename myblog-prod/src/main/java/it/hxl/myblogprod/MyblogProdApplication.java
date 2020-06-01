package it.hxl.myblogprod;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("it.hxl.myblogprod.mapper")
@SpringBootApplication
public class MyblogProdApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyblogProdApplication.class, args);
    }

}
