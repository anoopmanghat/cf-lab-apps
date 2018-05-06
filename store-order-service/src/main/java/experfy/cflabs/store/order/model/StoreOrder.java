package experfy.cflabs.store.order.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "orderId")
public class StoreOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderId;
	String productName;
	String customerName;
	int quantity;
	Date orderDate;
	Double orderPrice;

}
