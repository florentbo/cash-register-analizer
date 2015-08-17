package be.florentbo.register.repository;

import be.florentbo.register.SimpleConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SimpleConfiguration.class})
public class RegisterOrderRepositoryTest {

    @Autowired
    private RegisterOrderRepository repository;

    @Test
    public void testFindAll() throws Exception {

        assertThat(repository.findAll()).hasSize(3);

    }

    @Test
    public void findByDate() throws Exception {

        assertThat(repository.findByDate(LocalDate.of(2014, 6, 2).getMonthValue())).hasSize(3);

    }
}