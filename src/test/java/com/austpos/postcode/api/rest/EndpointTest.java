package com.austpos.postcode.api.rest;

import com.austpos.postcode.api.model.PostCodeDetail;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class EndpointTest {

  protected static final MediaType JSON_MEDIA_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("UTF-8"));

  @Autowired
  protected WebApplicationContext webApplicationContext;

  @Autowired
  protected ObjectMapper objectMapper;

  protected MockMvc mockMvc;

  protected void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  /**
   * Returns json representation of the object.
   * @param o instance
   * @return json
   * @throws IOException
   */
  protected String json(Object o) throws IOException {
    return objectMapper.writeValueAsString(o);
  }

  protected PostCodeDetail toPostcodeDetail(String response) throws IOException {
    return objectMapper.readValue(response, PostCodeDetail.class);
  }
}
