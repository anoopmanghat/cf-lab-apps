package experfy.cflabs.store.manager.service;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import experfy.cflabs.store.manager.cloud.service.WebServiceInfo;
import experfy.cflabs.store.manager.model.Order;

/**
 * Order Service Helper
 * @author insignia
 *
 */

@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	private static final String DEFAULT_STOREORDER_SERVICE_URI = "http://localhost:8181";
	
	@Value("${default.storeorder.serviceuri:" + DEFAULT_STOREORDER_SERVICE_URI + "}")
	private String baseUri;
	private String endpoint;

	
	private RestTemplate restTemplate;
	
	public OrderService() {
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new Jackson2HalModule());
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
		converter.setObjectMapper(mapper);
		this.restTemplate = new RestTemplate(Collections.<HttpMessageConverter<?>> singletonList(converter));
	}

	

	@HystrixCommand(fallbackMethod = "defaultList")
	public List<Order> getOrders() {
		try {
			
			ResponseEntity<Order[]> responseEntity = restTemplate.getForEntity(endpoint, Order[].class);
			Order[] objects = responseEntity.getBody();
			return Arrays.asList(objects);
		} catch (Exception e) {
			logger.error("Failed to retrieve customer orders.  Returning empty list.", e);
			throw e;
		}
	}

	@SuppressWarnings("unused")
	private List<Order> defaultList() {
		return new ArrayList<>();
	}

	@PostConstruct
	public void init() {
		try {
			CloudFactory cloudFactory = new CloudFactory();
			Cloud cloud = cloudFactory.getCloud();
			List<ServiceInfo> serviceInfos = cloud.getServiceInfos();
			for (ServiceInfo serviceInfo : serviceInfos) {
				if (serviceInfo instanceof WebServiceInfo) {
					WebServiceInfo webServiceInfo = (WebServiceInfo) serviceInfo;
					this.baseUri = webServiceInfo.getUri();
				}
			}
		} catch (CloudException e) {
			logger.debug("Failed to read cloud environment.  Ignore if running locally.");
		}
		deriveEndpointFromBaseUri();
		logger.info("order-service uri is: {}", this.baseUri);
	}

	private void deriveEndpointFromBaseUri() {
		if (this.baseUri.endsWith("/storeOrders")) {
			this.endpoint = this.baseUri;
		} else if (this.baseUri.endsWith("/")) {
			this.endpoint = String.format("%sstoreOrders", baseUri);
		} else {
			this.endpoint = String.format("%s/storeOrders", baseUri);
		}
	}

	private HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return new HttpEntity<>(headers);
	}

}
