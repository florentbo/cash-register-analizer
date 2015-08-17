package be.florentbo.register.repository;

import be.florentbo.register.SimpleConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}