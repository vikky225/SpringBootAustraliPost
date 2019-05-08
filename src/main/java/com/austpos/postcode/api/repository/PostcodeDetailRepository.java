package com.austpos.postcode.api.repository;

import com.austpos.postcode.api.model.PostCodeDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostcodeDetailRepository extends JpaRepository<PostCodeDetail, Long> {

  PostCodeDetail findPostCodeDetailsBySuburb(String suburb);

  List<PostCodeDetail> findPostCodeDetailsByPostcode(Integer postcode);
}
