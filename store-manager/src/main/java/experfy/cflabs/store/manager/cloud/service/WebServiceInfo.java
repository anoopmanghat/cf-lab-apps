/**
 * 
 */
package experfy.cflabs.store.manager.cloud.service;

import org.springframework.cloud.service.UriBasedServiceInfo;

/**
 * Order Service Web Service Info
 * @author insignia
 *
 */
public class WebServiceInfo extends UriBasedServiceInfo {

	public WebServiceInfo(String id, String url) {
		super(id, url);
	}
}
