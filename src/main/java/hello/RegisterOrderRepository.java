package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RegisterOrderRepository extends CrudRepository<RegisterOrder, Long> {

    @Query("SELECT r FROM RegisterOrder r INNER JOIN r.details")
    Set<RegisterOrder> findAll();

}
