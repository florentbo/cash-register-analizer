package be.florentbo.register.service;

import be.florentbo.register.repository.RegisterOrder;
import be.florentbo.register.repository.RegisterOrderDetail;
import be.florentbo.register.repository.RegisterOrderRepository;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static be.florentbo.register.repository.Factory.*;
import static be.florentbo.register.service.OrderService.RegisterOrderDetailMapper;
import static be.florentbo.register.service.OrderService.RegisterOrderMapper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.StrictAssertions.entry;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private OrderService service;
    private RegisterOrderRepository repositoryMock;
    private Function<Set<RegisterOrder>,Set<LocalDate>> toLocalDatesMock;
    private Function<Set<RegisterOrderDetail>, Map<String,Integer>> toDayMock;

    @Before
    public void setUp() throws Exception {
        repositoryMock = mock(RegisterOrderRepository.class);
        toLocalDatesMock = mock(Function.class);
        toDayMock = mock(Function.class);
        service = new OrderService(repositoryMock, toLocalDatesMock, toDayMock);
    }

    @Test
    public void getDays() {
        Set<LocalDate> localDates = createLocalDates();
        when(toLocalDatesMock.apply(anySet())).thenReturn(localDates);

        Set<LocalDate> days = service.getDays();

        assertThat(days).hasSize(localDates.size());
        verify(repositoryMock).findAll();
        verifyNoMoreInteractions(repositoryMock);

        verify(toLocalDatesMock).apply(anySet());
        verifyNoMoreInteractions(toLocalDatesMock);
    }

    @Test
    public void getDay() throws Exception {
        LocalDate localDate = LocalDate.of(2015, 6, 26);
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("26/6/2015");
        when(toDayMock.apply(anySet())).thenReturn(createDay());

        Map<String, Integer> day = service.getDay(localDate);
        assertThat(day).hasSize(createDay().size());

        verify(repositoryMock).find(date);
        verifyNoMoreInteractions(repositoryMock);
        verify(toDayMock).apply(anySet());
        verifyNoMoreInteractions(toDayMock);
    }

    @Test
    public void registerOrderMapper() {
        RegisterOrderMapper mapper = new RegisterOrderMapper();
        Set<RegisterOrder> registerOrders = createRegisterOrders();

        Set<LocalDate> localDates = mapper.apply(registerOrders);
        assertThat(registerOrders).hasSize(3);
        assertThat(localDates).hasSize(2);
    }

    @Test
    public void find() throws Exception {
        LocalDate localStartDate = LocalDate.of(2015, 6, 26);
        Date startDate = SIMPLE_DATE_FORMAT.parse("26/6/2015");

        LocalDate localEndDate = LocalDate.of(2015, 6, 27);
        Date endDate = SIMPLE_DATE_FORMAT.parse("27/6/2015");

        when(toDayMock.apply(anySet())).thenReturn(createDay());

        Map<String, Integer> day = service.find(localStartDate, localEndDate);
        assertThat(day).hasSize(createDay().size());

        verify(repositoryMock).find(startDate,endDate);
        verifyNoMoreInteractions(repositoryMock);
        verify(toDayMock).apply(anySet());
        verifyNoMoreInteractions(toDayMock);
    }

    @Test
    public void testName() throws Exception {
        RegisterOrderDetailMapper mapper = new RegisterOrderDetailMapper();
        Map<String, Integer> map = mapper.apply(createRegisterOrderDetails());
        assertThat(map).contains(
                            entry(PRODUCT_NUMBER_1,2),
                            entry(PRODUCT_NUMBER_2,1));



    }
}