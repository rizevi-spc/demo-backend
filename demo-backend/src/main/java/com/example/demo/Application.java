package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.RoverConfigDao;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.RoverConfig;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner init(RoverConfigDao userDao){
//        return args -> {
////            User user1 = new User();
////            user1.setFirstName("Ali");
////            user1.setLastName("Veli");
////            user1.setUsername("aliveli");
////            user1.setPassword("asd");
////            List<String> roles=new ArrayList<>();
////            roles.add("RESEARCHER");
////            user1.setRoles(roles);
////            userDao.save(user1);
//        	RoverConfig config=new RoverConfig();
//        	config.setActive(true);
//        	config.setPort(6000);
//        	RoverConfig config2=new RoverConfig();
//        	config.setActive(true);
//        	config.setPort(6001);
//        	userDao.save(config);
//        	userDao.save(config2);
//
//           
//        };
//    }

}
