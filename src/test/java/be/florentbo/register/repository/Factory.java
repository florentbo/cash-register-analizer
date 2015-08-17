package be.florentbo.register.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Factory {

    public static Set<RegisterOrder> create(){
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalTime time = LocalTime.now();
        return Stream.of(
                RegisterOrder.getBuilder(LocalDateTime.of(today,time.minusMinutes(5))).build(),
                RegisterOrder.getBuilder(LocalDateTime.of(today,time)).build(),
                RegisterOrder.getBuilder(LocalDateTime.of(yesterday,time)).build()
        ).collect(Collectors.toSet());
    }
}
