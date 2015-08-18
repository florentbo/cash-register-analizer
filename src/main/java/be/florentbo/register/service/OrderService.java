package be.florentbo.register.service;


import be.florentbo.register.repository.RegisterOrder;
import be.florentbo.register.repository.RegisterOrderRepository;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
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
        return TO_DOMAIN.apply(registerOrders);
    }

    public Set<LocalDate> getDay(LocalDate localDate) {
        Set<RegisterOrder> registerOrders = repository.findByDate(Date.valueOf(localDate));
        return TO_DOMAIN.apply(registerOrders);
    }

    Function<Set<RegisterOrder>, Set<LocalDate>> TO_DOMAIN
                = registerOrders -> registerOrders
                                                .stream()
                                                .map(ro -> ro.getOrderTime().toLocalDate())
                                                .collect(Collectors.toSet());

    public boolean read(byte[] file) {
        return false;
    }

    public String test() {
        return "not mock";
    }

}
