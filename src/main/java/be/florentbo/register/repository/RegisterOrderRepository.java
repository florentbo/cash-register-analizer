package be.florentbo.register.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public interface RegisterOrderRepository extends CrudRepository<RegisterOrder, Long> {

    Set<RegisterOrder> findAll();

    @Query("SELECT ro FROM RegisterOrder ro JOIN FETCH ro.details WHERE CAST(ro.orderTime as date) = :date")
    Set<RegisterOrder> findByDate(@Param("date") Date date);
}
