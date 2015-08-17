package be.florentbo.register.service;


import be.florentbo.register.repository.RegisterOrder;
import be.florentbo.register.repository.RegisterOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderService {
    private final RegisterOrderRepository repository;

    @Autowired
    public OrderService(RegisterOrderRepository repository) {
        this.repository = repository;
    }

    public Set<LocalDate> getDays() {
        Set<RegisterOrder> registerOrders = repository.findAll();
        return registerOrders.stream().map(ro -> ro.getOrderTime().toLocalDate()).collect(Collectors.toSet());
    }

    public boolean read(byte[] file) {
        return false;
    }

    public String test() {
        return "not mock";
    }

}
