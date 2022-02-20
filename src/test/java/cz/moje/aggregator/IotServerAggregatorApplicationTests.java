package cz.moje.aggregator;

import cz.moje.aggregator.controller.AggregatorJSONController;
import cz.moje.aggregator.model.*;
import cz.moje.aggregator.sampledata.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IotServerAggregatorApplicationTests {

	@Autowired
	private AggregatorJSONController jsonController;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private Data data;

	@LocalServerPort
	private int port;

	@Test
	public void contextLoad() {
		Assertions.assertThat(this.jsonController).isNotNull();
	}

	@Test
	// TODO, kdyz tento test poustim samotny tak projde. kdyz poustim vsechny testy tak fail
	public void testString() {
		ResponseEntity<String> responseEntity = this.restTemplate.getForEntity
				("http://localhost:" + this.port + "/simple", String.class);

		Assertions.assertThat(responseEntity.toString()).contains(this.data.getStringData());
	}

	@Test
	public void testWithMock() {
		MockRestServiceServer mock = MockRestServiceServer.
				createServer(this.restTemplate.getRestTemplate());
		mock.expect(ExpectedCount.once(),
				MockRestRequestMatchers.requestTo(
						"http://localhost:" + this.port + "/createJSONIdentification")
				);
	}

	@Test
	public void testGetJSONInfo() {
		ResponseEntity<Identification> responseEntity = this.restTemplate.getForEntity
				("http://localhost:" + this.port + "/json-info", Identification.class);

		System.out.println(responseEntity.toString());

		Assertions.assertThat(responseEntity.toString()).contains
				(this.data.getJSONData().toString());
	}

	@Test
	// TODO, tohle je taky na palici, sam tento test passne :(
	public void testJSONResponseIsNotSame() {
		ResponseEntity<Identification> responseEntity = this.restTemplate.getForEntity
				("http://localhost:" + this.port + "/json-info", Identification.class);

		Address a = new Address("Na dobre", "13", "Zlin");
		Person p = new Person("Pepa", "Modry");
		List<Device> devices = new LinkedList<>();
		devices.add(new Device("teplomer", DeviceType.INDUSTRIAL));
		devices.add(new Device("tlakomer", DeviceType.INDOOR));

		Identification i = new Identification("moje chybna meteostanice", a, p, devices);

		// TODO, omg, nad this.data.getJSONData() nemuzu zavolat setter?

		Assertions.assertThat(responseEntity.toString()).doesNotContain(i.toString());
	}
}
