package name.abhijitsarkar.javaee.feign

import name.abhijitsarkar.javaee.feign.web.FeignController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.TestRestTemplate
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import spock.lang.Specification

/**
 * @author Abhijit Sarkar
 */
@SpringApplicationConfiguration([FeignApp, FeignAutoConfiguration])
@WebIntegrationTest(randomPort = true)
abstract class AbstractFeignSpec extends Specification {
    @Autowired
    FeignController feignController

    @Value('${local.server.port}')
    protected int port

    protected RestTemplate restTemplate = new TestRestTemplate()

    protected UriComponentsBuilder uriBuilder

//    @PostConstruct
//    def postConstruct() {
//        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:$port")
//    }

    def setup() {
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:$port")
    }
}