import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

//http://spring.io/guides/tutorials/rest/5/

public class ClientTests {

	static String HOST_URL = "http://localhost:8081/spgo-ws/service/employee/";
	static String tokenId = "623bbcd3-eb8d-4dce-aad0-6934ab624b7c";

	static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) {

		ClientTests clientTests = new ClientTests();
		clientTests.callSringRestToAddEmployee();

	}

	public void callSringRestToAddEmployee() {

		EmployeeInfo info = new EmployeeInfo();		
		// Start Set Data to add employee
		info.setEmail("xxx@yyy.com");
		info.setAge(13);
		info.setName("juac son");
		// End Set Data to add employee
		
		String json = "";
		try { json = mapper.writeValueAsString(info); } catch (Exception e) { e.printStackTrace(); }
	
		HttpEntity<String> requestEntity = new HttpEntity<String>(json,
				getHeadersForToken(tokenId));

		RestTemplate template = new RestTemplate();
		ResponseEntity<EmployeeInfo> entity = template.postForEntity(
				HOST_URL + "addEmployee", requestEntity,
				EmployeeInfo.class);

		HttpStatus httpStatus = entity.getStatusCode();

		System.out.println("HTTP STATUS: " + httpStatus.value());

	}

	static HttpHeaders getHeadersForToken(String tokenId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		headers.add("Authorization", "Bearer " + tokenId);

		return headers;

	}
	
	static HttpHeaders getHeaders(String auth) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		headers.add("Authorization", "Basic "
				+ new String(encodedAuthorisation));

		return headers;

	}
}