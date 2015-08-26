package be.florentbo.register.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfiguration.class})
@Transactional
public class ITOrderServiceTest {

    @Autowired
    private OrderService service;

    @Test
    public void getDays() {
        Set<LocalDate> days = service.getDays();
        assertThat(days).hasSize(3);
    }

    @Test
    public void getDay() throws Exception {
        LocalDate localDate = LocalDate.of(2015, 6, 26);

        Map<String, Integer> day = service.getDay(localDate);
        assertThat(day).hasSize(2);

    }

    @Test
    public void find() throws Exception {
        LocalDate localStartDate = LocalDate.of(2015, 6, 26);
        LocalDate localEndDate = LocalDate.of(2015, 6, 27);

        Map<String, Integer> day = service.find(localStartDate, localEndDate);
        assertThat(day).hasSize(2);

    }
}
