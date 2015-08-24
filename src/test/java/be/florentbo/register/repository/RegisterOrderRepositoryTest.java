package be.florentbo.register.repository;


import be.florentbo.register.SimpleConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SimpleConfiguration.class})
public class RegisterOrderRepositoryTest {

    @Autowired
    private RegisterOrderRepository repository;

    @Test
    public void testFindAll(){
        assertThat(repository.findAll()).hasSize(4);
    }

    @Test
    public void findByDate(){
        LocalDate localDate = LocalDate.of(2015, 6, 26);

        Set<RegisterOrderDetail> registerOrders = repository.find(Date.valueOf(localDate));
        assertThat(registerOrders).hasSize(3);
    }

    @Test
    public void findBetweenTowDates(){
        LocalDate startDate = LocalDate.of(2015, 6, 23);
        LocalDate endDate = LocalDate.of(2015, 6, 26);

        Set<RegisterOrderDetail> registerOrders = repository.find(Date.valueOf(startDate), Date.valueOf(endDate));
        assertThat(registerOrders).hasSize(6);
    }
}