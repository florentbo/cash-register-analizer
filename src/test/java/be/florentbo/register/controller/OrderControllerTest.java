package be.florentbo.register.controller;

import be.florentbo.register.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static be.florentbo.register.controller.OrderController.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfiguration.class)
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private OrderService orderServiceMock;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

    }

    @Test
    public void testAddForm() throws Exception {
        mockMvc.perform(get(ORDER_PATH))
                .andExpect(status().isOk())
                .andExpect(view().name(ORDER_VIEW));

    }

    @Test
    public void testOrderList() throws Exception {
        /*when(orderServiceMock.getDays()).thenReturn("this is mock");*/
        mockMvc.perform(get(REQUEST_MAPPING_ORDER_LIST))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_ORDER_LIST));
        verify(orderServiceMock).getDays();
        verifyNoMoreInteractions(orderServiceMock);
    }
}