/*
 * Copyright (c) 2016, the original author or authors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * A copy of the GNU General Public License accompanies this software,
 * and is also available at http://www.gnu.org/licenses.
 */

package name.abhijitsarkar.feign

import java.net.URI

import name.abhijitsarkar.feign.core.web.FeignController
import name.abhijitsarkar.feign.persistence.RecordingRequest
import org.scalatest.{FlatSpec, Matchers}
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.boot.test.{SpringApplicationConfiguration, TestRestTemplate, WebIntegrationTest}
import org.springframework.core.ParameterizedTypeReference
import org.springframework.hateoas.client.Traverson
import org.springframework.hateoas.{MediaTypes, Resource}
import org.springframework.http.HttpMethod._
import org.springframework.http.HttpStatus.OK
import org.springframework.test.context.{ActiveProfiles, TestContextManager}
import org.springframework.web.util.UriComponentsBuilder

/**
  * @author Abhijit Sarkar
  */
@SpringApplicationConfiguration(Array(classOf[FeignApp], classOf[FeignConfiguration]))
@WebIntegrationTest(randomPort = true)
@ActiveProfiles(Array("p1"))
class FeignSpecP1 extends FlatSpec with Matchers {
  @Autowired
  var FeignController: FeignController = _

  @Value("${local.server.port}")
  var port: Int = _

  new TestContextManager(this.getClass).prepareTestInstance(this)

  val restTemplate = new TestRestTemplate()
  val uriBuilder = UriComponentsBuilder.fromUriString(s"http://localhost:$port")

  "feign" should "match POST request and find it" in {
    val uri = uriBuilder.path("feign/abc").build().toUri()
    val response = restTemplate.exchange(uri, POST, null, classOf[String])

    response.getStatusCode shouldBe OK

    val traverson = new Traverson(new URI(s"http://localhost:$port"), MediaTypes.HAL_JSON)

    val t = new ParameterizedTypeReference[Resource[RecordingRequest]]() {}

    def request = traverson.
      follow("requests").
      follow("$._embedded.requests[0]._links.self.href").
      toObject(t)
      .getContent

    println(request)

    request.path shouldEqual "/feign/abc"
    request.method shouldEqual "POST"
  }

  it should "match any GET request" in {
    val uri = uriBuilder.path("feign/abc").build().toUri()
    val response = restTemplate.exchange(uri, GET, null, classOf[String])

    response.getStatusCode shouldBe OK
  }
}
