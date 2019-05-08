package com.austpos.postcode.api.rest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.austpos.postcode.api.model.PostCodeDetail;
import com.austpos.postcode.api.service.PostcodeDetailService;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PostcodeDetailRestControllerTest {

  @Mock
  private PostcodeDetailService postcodeDetailService;

  PostcodeDetailRestController controller;

  @Before
  public void setup(){
    controller = new PostcodeDetailRestController(postcodeDetailService);
  }

  @Test
  public void testCreatePostcodeWithSuburb(){
    PostCodeDetail detail = new PostCodeDetail(3456, "SUNNY", "AU");
    when(postcodeDetailService.getPostCodeDetailsByPostcode(3456)).thenReturn(Collections.emptyList());
    when(postcodeDetailService.createPostcodeDetail(detail)).thenReturn(detail);

    controller.createPostcodeWithSuburb(3456, "Sunny");
    verify(postcodeDetailService, times(1)).createPostcodeDetail(detail);
  }

}
