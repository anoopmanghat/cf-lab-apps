/**
 * 
 */
package experfy.cflabs.store.manager.cloud.service;

import java.util.Map;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

/**
 * Order WebServiceInfo Creator
 * 
 * @author insignia
 *
 */
public class OrderWebServiceInfoCreator extends CloudFoundryServiceInfoCreator<WebServiceInfo> {

	public OrderWebServiceInfoCreator() {
		super(new Tags(), "http", "https");
	}

	@Override
	public WebServiceInfo createServiceInfo(Map<String, Object> serviceData) {
		String id = (String) serviceData.get("name");

		Map<String, Object> credentials = getCredentials(serviceData);
		String uri = getUriFromCredentials(credentials);

		return new WebServiceInfo(id, uri);
	}

}
