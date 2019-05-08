package com.austpos.postcode.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class PostcodeDetailTest {

  @Test
  public void testPostcodeDetail(){
    PostCodeDetail detail = getPostcodeDetail();
    assertEquals(Integer.valueOf(3145), detail.getPostcode());
    assertEquals("Malvern", detail.getSuburb());
    assertEquals("Vic", detail.getState());

    PostCodeDetail detail2 = getPostcodeDetail();
    assertEquals(detail, detail2);
    detail2.setPostcode(3456);
    assertFalse(detail.equals(detail2));
  }

  @Test
  public void testToString(){
    PostCodeDetail detail = getPostcodeDetail();
    assertFalse(detail.toString().isEmpty());
  }

  private PostCodeDetail getPostcodeDetail(){
    return new PostCodeDetail(3145, "Malvern", "Vic");
  }
}
