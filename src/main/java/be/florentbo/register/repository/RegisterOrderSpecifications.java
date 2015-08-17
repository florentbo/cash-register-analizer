package be.florentbo.register.repository;


import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class RegisterOrderSpecifications {


	public static Specification<RegisterOrder> orderDateEquals(final LocalDate date) {

		return (root, query, cb) -> {

            Root<RegisterOrder> order = query.from(RegisterOrder.class);
            Path<LocalDateTime> orderTime = order.<LocalDateTime> get("orderTime");
            //Predicate customerIsAccountOwner = cb.equal(accounts.<Customer> get("customer"), root);

            return cb.equal(orderTime,
                    Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        };
	}
}
