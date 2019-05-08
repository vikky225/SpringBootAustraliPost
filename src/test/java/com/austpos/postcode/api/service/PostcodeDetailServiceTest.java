package com.austpos.postcode.api.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.austpos.postcode.api.model.PostCodeDetail;
import com.austpos.postcode.api.repository.PostcodeDetailRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PostcodeDetailServiceTest {

  @Mock
  private PostcodeDetailRepository repo;

  PostcodeDetailService service;

  @Before
  public void setup(){
    service = new PostcodeDetailService(repo);
  }

  @Test
  public void testCreatePostcodeDetail(){
    PostCodeDetail postCodeDetail = new PostCodeDetail(3456, "Glenn", "Vic");
    when(repo.save(postCodeDetail)).thenReturn(
        new PostCodeDetail(123L, 3456, "Glenn", "Vic"));

    PostCodeDetail result = service.createPostcodeDetail(postCodeDetail);
    verify(repo, times(1)).save(postCodeDetail);

    assertEquals(Integer.valueOf(3456), result.getPostcode());
    assertEquals("Glenn", result.getSuburb());
    assertEquals("Vic", result.getState());
  }

  @Test
  public void testGetPostcodeDetail() {
    when(repo.findPostCodeDetailsBySuburb("KEW")).thenReturn(
        new PostCodeDetail(3453L, 7894, "Kew", "Vic"));

    PostCodeDetail detail = service.getPostcodeDetailBySuburb("Kew");
    verify(repo, times(1)).findPostCodeDetailsBySuburb("KEW");

    assertEquals(Integer.valueOf(7894), detail.getPostcode());
    assertEquals("Vic", detail.getState());
  }

  @Test
  public void testGetPostCodeDetailsByPostcode() {
    PostCodeDetail detail = new PostCodeDetail(23L, 4568, "Fizroy", "Vic");
    when(repo.findPostCodeDetailsByPostcode(4568)).thenReturn(Arrays.asList(detail));

    List<PostCodeDetail> result = service.getPostCodeDetailsByPostcode(4568);
    assertEquals(1, result.size());
    assertEquals(Integer.valueOf(4568), result.get(0).getPostcode());
    assertEquals("Fizroy", result.get(0).getSuburb());
  }
}
