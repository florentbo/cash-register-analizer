package be.florentbo.register.service;

import be.florentbo.register.repository.Factory;
import be.florentbo.register.repository.RegisterOrderRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    private OrderService service;
    private RegisterOrderRepository repositoryMock;

    @Before
    public void setUp() throws Exception {
        repositoryMock = mock(RegisterOrderRepository.class);
        service = new OrderService(repositoryMock);
    }

    @Test
    public void testGetDays() throws Exception {
        when(repositoryMock.findAll()).thenReturn(Factory.create());
        Set<LocalDate> days = service.getDays();
        assertThat(days).hasSize(2);
        verify(repositoryMock).findAll();

    }

    @Test
    public void testGetDay() throws Exception {
        LocalDate localDate = LocalDate.of(2015, 6, 26);
        when(repositoryMock.findByDate(any(Date.class))).thenReturn(Factory.create());
        Set<LocalDate> days = service.getDay(localDate);
        assertThat(days).hasSize(2);
        verify(repositoryMock).findByDate(any(Date.class));
    }
}