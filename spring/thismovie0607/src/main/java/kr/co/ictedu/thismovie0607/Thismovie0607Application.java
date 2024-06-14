package kr.co.ictedu.thismovie0607;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Thismovie0607Application {

	public static void main(String[] args) {
		SpringApplication.run(Thismovie0607Application.class, args);
	}
	 @Bean
	   public WebMvcConfigurer crosConfigurer() {
	      return new WebMvcConfigurer() {
	         // 마우스 우클릭 소스작업.. 클릭
	         // 오버라이딩 있음
	         @Override
	         public void addCorsMappings(CorsRegistry registry) {
	            System.out.println("Test=========");
	            registry.addMapping("/**")
	                  .allowedOrigins("http://192.168.0.39:3000","http://192.168.0.129:3000", "http://192.168.0.222:3000/", "http://localhost:3000/")
	                  .allowedHeaders("*")
	                  .allowedMethods("*").maxAge(9999);
	         }
	      };
	   }
}
