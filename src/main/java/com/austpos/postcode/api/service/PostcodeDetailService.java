package com.austpos.postcode.api.service;

import com.austpos.postcode.api.model.PostCodeDetail;
import com.austpos.postcode.api.repository.PostcodeDetailRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostcodeDetailService {
  private final Logger LOGGER = LoggerFactory.getLogger(getClass());

  @Autowired
  private PostcodeDetailRepository repository;

  public PostcodeDetailService(PostcodeDetailRepository repository) {
    this.repository = repository;
  }

  public PostCodeDetail createPostcodeDetail(PostCodeDetail postCodeDetail) {
    LOGGER.info("Saving new postcode details");
    return repository.save(postCodeDetail);
  }

  public PostCodeDetail getPostcodeDetailBySuburb(String suburb){
    LOGGER.info("Getting postcode details for suburb - " + suburb);
    return repository.findPostCodeDetailsBySuburb(suburb.toUpperCase());
  }

  public List<PostCodeDetail> getPostCodeDetailsByPostcode(Integer postcode) {
    LOGGER.info("Getting multiple postcode details for postcode - " + postcode);
    return repository.findPostCodeDetailsByPostcode(postcode);
  }
}
