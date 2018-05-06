/**
 * 
 */
package experfy.cflabs.store.manager.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import experfy.cflabs.store.manager.service.EnvironmentHelper;
import experfy.cflabs.store.manager.service.OrderService;

/**
 * Order Service Controller
 * 
 * @author insignia
 *
 */
@Controller
@RequestMapping("/orderManager")
public class OrderServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceController.class);

	@Autowired
	OrderService orderService;

	@Autowired
	EnvironmentHelper environmentHelper;

	@GetMapping
	public String getOrders(Model model, HttpServletRequest request, @RequestParam(value = "doit", required = false) boolean doit) throws Exception {
		model.addAttribute("orders", orderService.getOrders());
		model.addAllAttributes(environmentHelper.addAppEnv(request));
		return "dashboard/index";

	}

	@GetMapping(value = "/basics")
	public String kill(HttpServletRequest request, @RequestParam(value = "doit", required = false) boolean doit,
			Model model) throws Exception {

		model.addAllAttributes(environmentHelper.addAppEnv(request));

		if (doit) {
			model.addAttribute("killed", true);
			logger.warn("*** The system is shutting down. ***");
			Runnable killTask = () -> {
				try {
					String name = Thread.currentThread().getName();
					logger.warn("killing shortly " + name);
					TimeUnit.SECONDS.sleep(5);
					logger.warn("killed " + name);
					System.exit(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			new Thread(killTask).start();
		}

		return "basics";

	}

}