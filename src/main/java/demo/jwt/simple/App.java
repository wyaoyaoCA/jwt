package demo.jwt.simple;


import demo.jwt.simple.utils.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {


    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).web(true).run(args);
    }


    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils();
    }
}


