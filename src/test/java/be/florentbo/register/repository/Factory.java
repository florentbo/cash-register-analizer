package be.florentbo.register.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Factory {

    public static final String PRODUCT_NUMBER_1 = "Product number 1";
    public static final String PRODUCT_NUMBER_2 = "Product number 2";

    public static Set<RegisterOrder> createRegisterOrders(){
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalTime time = LocalTime.now();
        return Stream.of(
                RegisterOrder.getBuilder(LocalDateTime.of(today,time.minusMinutes(5))).build(),
                RegisterOrder.getBuilder(LocalDateTime.of(today,time)).build(),
                RegisterOrder.getBuilder(LocalDateTime.of(yesterday,time)).build()
        ).collect(Collectors.toSet());
    }

    public static Set<RegisterOrderDetail> createRegisterOrderDetails(){
        return Stream.of(
                RegisterOrderDetail.getBuilder(PRODUCT_NUMBER_1,1).build(),
                RegisterOrderDetail.getBuilder(PRODUCT_NUMBER_2, 1).build(),
                RegisterOrderDetail.getBuilder(PRODUCT_NUMBER_1,1).build()
        ).collect(Collectors.toSet());
    }

    public static Set<LocalDate> createLocalDates(){
        return Stream.of(
                LocalDate.now(),
                LocalDate.now()
        ).collect(Collectors.toSet());
    }

    public static Map<String, Integer> createDay(){
        return new HashMap<String, Integer>() {{
            put(PRODUCT_NUMBER_1, 1);
            put(PRODUCT_NUMBER_2, 2);
        }};
    };
}
