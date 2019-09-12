package kr.devdogs.swot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@MapperScan("kr.devdogs.swot.user.mapper.UserMapper")
public class SwotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwotApplication.class, args);
	}

}
