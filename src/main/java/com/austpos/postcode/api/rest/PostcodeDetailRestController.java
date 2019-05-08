package com.austpos.postcode.api.rest;

import com.austpos.postcode.api.model.PostCodeDetail;
import com.austpos.postcode.api.service.PostcodeDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/postcode-detail")
@Api(value = "PostcodeDetailControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostcodeDetailRestController extends BaseRestController {

  @Autowired
  private PostcodeDetailService postcodeDetailService;

  public PostcodeDetailRestController(PostcodeDetailService service) {
    this.postcodeDetailService = service;
  }

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create a postcode detail resource.")
  @ApiResponses(value = {@ApiResponse(code = 201, message = "New postcode-suburb created"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "System error encountered")})
  public PostCodeDetail createPostcodeWithSuburb(@RequestParam("postcode") Integer postcode,
      @RequestParam("suburb") String suburb){
    List<PostCodeDetail> postCodeDetails = postcodeDetailService.getPostCodeDetailsByPostcode(postcode);
    List<PostCodeDetail> results = postCodeDetails.stream()
                   .filter(detail -> detail.getSuburb().equalsIgnoreCase(suburb))
                   .limit(1)
                   .collect(Collectors.toList());
    checkResourceAlreadyExist(CollectionUtils.isEmpty(results) ? null : results.get(0));
    return postcodeDetailService.createPostcodeDetail(new PostCodeDetail(postcode, suburb.toUpperCase(), "AU"));
  }

  @RequestMapping(value = "/suburb/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get a customer resource.")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Postcode resource cannot be found.")})
  public Integer getPostcodeBySuburb(@ApiParam(value = "The name of suburb.", required = true)
  @PathVariable("name") String suburb) {
    PostCodeDetail postCodeDetail = postcodeDetailService.getPostcodeDetailBySuburb(suburb);
    checkResourceFound(postCodeDetail);
    return postCodeDetail.getPostcode();
  }


  @RequestMapping(value = "/postcode/{postcode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get a customer resource.")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Postcode resource cannot be found.")})
  public List<String> getSuburbsByPostcode(@ApiParam(value = "The postcode.", required = true)
  @PathVariable("postcode") Integer postcode) {
    List<PostCodeDetail> postCodeDetails = postcodeDetailService.getPostCodeDetailsByPostcode(postcode);
    return postCodeDetails.stream()
                          .map(PostCodeDetail::getSuburb)
                          .collect(Collectors.toList());
  }
}
