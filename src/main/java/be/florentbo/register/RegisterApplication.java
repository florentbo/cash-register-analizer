package be.florentbo.register;

import be.florentbo.register.repository.RegisterOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegisterApplication implements CommandLineRunner {

    @Autowired
    RegisterOrderRepository registerOrderRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        /*System.out.println("Orders found with findAll():");
        System.out.println("-------------------------------");
        for (RegisterOrder order : registerOrderRepository.findAll()) {
            System.out.println(order);
            for (RegisterOrderDetail registerOrderDetail : order.getDetails()) {
                System.out.println();
                long quantity = registerOrderDetail.getQuantity();
                if (quantity!=1)
                    System.out.println(registerOrderDetail.getProduct().getName() + " " + quantity);
            }
        }*/
    }

}
