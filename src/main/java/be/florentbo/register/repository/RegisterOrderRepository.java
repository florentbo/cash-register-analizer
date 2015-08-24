package be.florentbo.register.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public interface RegisterOrderRepository extends CrudRepository<RegisterOrder, Long> {

    Set<RegisterOrder> findAll();

    @Query("SELECT rod FROM RegisterOrderDetail rod " +
                                                    "JOIN FETCH rod.registerOrder ro " +
                                                    "WHERE CAST(ro.orderTime as date) = :date")
    Set<RegisterOrderDetail> find(@Param("date") Date date);

    @Query("SELECT rod FROM RegisterOrderDetail rod " +
                                "JOIN FETCH rod.registerOrder ro " +
                                "WHERE CAST(ro.orderTime as date) BETWEEN :startDate AND :endDate")
    Set<RegisterOrderDetail> find(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
