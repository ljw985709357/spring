package cn.tedu.easymall_User;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.netflix.discovery.shared.Application;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.tedu.mapper")
public class StartUser {
     public static void main(String[] args) {
		SpringApplication.run(StartUser.class, args);
	}
}
