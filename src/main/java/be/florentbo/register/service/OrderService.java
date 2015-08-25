package be.florentbo.register.service;


import be.florentbo.register.repository.RegisterOrder;
import be.florentbo.register.repository.RegisterOrderDetail;
import be.florentbo.register.repository.RegisterOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class OrderService {

    private final RegisterOrderRepository repository;
    private final Function<Set<RegisterOrder>,Set<LocalDate>> toLocalDates;
    private final Function<Set<RegisterOrderDetail>, Map<String,Integer>> toDay;

    @Autowired
    public OrderService(RegisterOrderRepository repository,
                        Function<Set<RegisterOrder>, Set<LocalDate>> toLocalDates,
                        Function<Set<RegisterOrderDetail>, Map<String,Integer>> toDay) {
        this.repository = repository;
        this.toLocalDates = toLocalDates;
        this.toDay = toDay;
    }

    public Set<LocalDate> getDays() {
        return toLocalDates.apply(repository.findAll());
    }

    public Map<String,Integer> getDay(LocalDate localDate) {
        return toDay.apply(repository.find(Date.valueOf(localDate)));
    }

    public Map<String,Integer> find(LocalDate startDate, LocalDate endDate) {
        return toDay.apply(repository.find(Date.valueOf(startDate), Date.valueOf(endDate)));
    }

    public Map<String,Integer> print(LocalDate startDate, LocalDate endDate) {
        return find(startDate,endDate);
    }


    /*public boolean read(byte[] file) {
        return false;
    }

    public String test() {
        return "not mock";
    }*/

    public static class RegisterOrderMapper implements Function<Set<RegisterOrder>,Set<LocalDate>>{
        @Override
        public Set<LocalDate> apply(Set<RegisterOrder> registerOrders) {
            return registerOrders
                                .stream()
                                .map(ro -> ro.getOrderTime().toLocalDate())
                                .collect(toSet());
        }
    }

    public static class RegisterOrderDetailMapper implements Function<Set<RegisterOrderDetail>, Map<String,Integer>>{
        @Override
        public Map<String, Integer> apply(Set<RegisterOrderDetail> registerOrderDetails) {
            return registerOrderDetails
                                .stream()
                                .collect(groupingBy(
                                                detail -> detail.getProduct().getName(),
                                                summingInt(RegisterOrderDetail::getQuantity)));
        }
    }

}
