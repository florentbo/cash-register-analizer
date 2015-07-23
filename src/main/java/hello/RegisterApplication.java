package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegisterApplication implements CommandLineRunner {

    @Autowired
    RegisterRepository repository;
    
    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Orders found with findAll():");
        System.out.println("-------------------------------");
        for (RegisterOrder order : repository.findAll()) {
            System.out.println(order);
        }
        System.out.println();


    }

}
