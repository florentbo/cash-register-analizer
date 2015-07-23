package hello;

import org.springframework.data.repository.CrudRepository;

public interface RegisterRepository extends CrudRepository<RegisterOrder, Long> {

}
