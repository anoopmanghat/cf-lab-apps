package experfy.cflabs.store.manager.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Order DTO
 * @author insignia
 *
 */
@Data
@AllArgsConstructor
public class Order {
	
	int orderId;
	String productName;
	String customerName;
	int quantity;
	Date orderDate;
	Double orderPrice;


}
