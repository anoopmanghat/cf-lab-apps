package experfy.cflabs.store.manager.test;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import experfy.cflabs.store.manager.controller.OrderServiceController;
import experfy.cflabs.store.manager.model.Order;
import experfy.cflabs.store.manager.service.OrderService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderServiceController.class)
public class OrderServiceControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private OrderService service;
	
	@Test
	public void givenOrders_whenGetOrders_thenReturnJsonArray()
	  throws Exception {
		
		Order order = new Order(1, "Prod1","Customer1", 1, new Date(), 123.25);
		
		java.util.List<Order> orders = Arrays.asList(order);
		
		given(service.getOrders()).willReturn(orders);
		
		mvc.perform(get("/api/employees")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$", hasSize(1)))
			      .andExpect(jsonPath("$[0].productName", is(order.getProductName())));
		
		
	}
	
	

}
