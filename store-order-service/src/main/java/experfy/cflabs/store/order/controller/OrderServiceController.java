/**
 * 
 */
package experfy.cflabs.store.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import experfy.cflabs.store.order.model.StoreOrder;
import experfy.cflabs.store.order.repository.OrderRepository;

/**
 * @author insignia
 *
 */
@RestController
public class OrderServiceController {
	
	@Autowired
	private OrderRepository orderRepo;
	
	
	@RequestMapping("/storeOrders")
	public List<StoreOrder> stOrder() throws Exception {
		return orderRepo.findAll();
		
	}

}
