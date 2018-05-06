/**
 * 
 */
package experfy.cflabs.store.manager.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author insignia
 *
 */
public class StoreOrderCreator {
	
	
	public static void main(String[] args) {
		
		
		Random r1 = new Random();
		Random r2 = new Random();
		Random r3 = new Random();
		Date d = new Date();
		for(int i=500;i>=1;i--){
		
			int orderId =i;
			String customerName= "customer"+r2.nextInt(16);
			String productName = "product"+r3.nextInt(7);
			
			int quantity = r1.nextInt(9)+1;
			
			
			
			if(i%40 == 0){
				Calendar c = Calendar.getInstance(); 
				c.setTime(d); 
				c.add(Calendar.MONTH, -1);
				d = c.getTime();
			}
			
			float generatedDouble = new Random().nextFloat()+50;
			
			

			
			System.out.println("INSERT INTO store_order(ORDER_ID,CUSTOMER_NAME,ORDER_DATE, ORDER_PRICE,PRODUCT_NAME, QUANTITY) "
					+ "VALUES("+orderId+",'"+customerName+"','"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(d)+"',"+generatedDouble+",'"+productName+"',"+quantity+");");
		}	
	}

}
