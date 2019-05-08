package com.austpos.postcode.api.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.austpos.postcode.api.model.PostCodeDetail;
import com.austpos.postcode.api.service.PostcodeDetailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostcodeEndpointsTest extends EndpointTest {

  @Autowired
  private PostcodeDetailService postcodeDetailService;

  PostCodeDetail detail;

  @Before
  public void setup(){
    super.setup();
    detail = new PostCodeDetail(9877, "Somewhere".toUpperCase(), "AU");
    postcodeDetailService.createPostcodeDetail(detail);
  }

  @Test
  public void getPostcodeBySuburb() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/postcode-detail/suburb/{name}", "Somewhere"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(JSON_MEDIA_TYPE))
        .andReturn();

    String postcode = result.getResponse().getContentAsString();
    assertEquals("9877", postcode);
  }

  @Test
  public void getPostcodeBySuburbInvalidSuburbName() throws Exception {
    mockMvc.perform(get("/api/postcode-detail/suburb/{name}", "klkjwer"))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(JSON_MEDIA_TYPE))
        .andReturn();
  }

  @Test
  public void getSuburbsByPostcode() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/postcode-detail/postcode/{postcode}", 9877))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(JSON_MEDIA_TYPE))
        .andReturn();

    String content = result.getResponse().getContentAsString();
    assertTrue(content.contains("SOMEWHERE"));
  }

  @Test
  public void getSuburbByPostcodeInvalidPostcode() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/postcode-detail/postcode/{postcode}", 99998))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(JSON_MEDIA_TYPE))
        .andReturn();

    String content = result.getResponse().getContentAsString();
    assertEquals("[]", content);
  }

  @Test
  public void createPostcodeWithSuburb() throws Exception {
    mockMvc.perform(post("/api/postcode-detail")
                    .param("postcode", "4560")
                    .param("suburb","Random"))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNumber())
        .andExpect(jsonPath("$.postcode").value(4560))
        .andExpect(jsonPath("$.suburb").value("RANDOM"));
  }

  @Test
  public void createPostcodeWithSuburbInvalid() throws Exception {
    mockMvc.perform(post("/api/postcode-detail")
        .param("postcode", "9877")
        .param("suburb","Somewhere"))
        .andDo(print())
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.message").value("resource already existed"));

  }
}
