/**
 * 
 */
package experfy.cflabs.store.order.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import experfy.cflabs.store.order.model.StoreOrder;

/**
 * @author insignia
 *
 */
public interface OrderRepository extends JpaRepository<StoreOrder, Long>{

}
