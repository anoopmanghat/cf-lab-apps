package experfy.cflabs.store.manager.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import experfy.cflabs.store.manager.service.EnvironmentHelper;

/**
 * 
 * Application Environment Controller
 * @author insignia
 *
 */

@RestController
public class CloudEnvController {

	private final EnvironmentHelper environmentHelper;

	public CloudEnvController(EnvironmentHelper environmentHelper) {
		this.environmentHelper = environmentHelper;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/deploy-check")
	public String bluegreenRequest() throws Exception {
		return (String) environmentHelper.getVcapApplicationMap().getOrDefault("application_name", "no name environment variable");
	}
}