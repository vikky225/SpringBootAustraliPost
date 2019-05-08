package com.austpos.postcode.api.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "postcodedetail")
public class PostCodeDetail implements Serializable {

  @ApiModelProperty(required = true)
  @Id
  @GeneratedValue
  @Column(name = "postcode_id")
  private Long id;

  @ApiModelProperty(required = true)
  @Column(name = "post_code")
  private Integer postcode;

  @ApiModelProperty(required = true)
  @NotNull
  @Column
  private String suburb;

  @Column(name = "state")
  private String state;

  public PostCodeDetail(){
  }

  public PostCodeDetail(Integer postcode, String suburb, String state) {
    this.postcode = postcode;
    this.suburb = suburb;
    this.state = state;
  }

  public PostCodeDetail(Long id, Integer postcode, String suburb, String state) {
    this.id = id;
    this.postcode = postcode;
    this.suburb = suburb;
    this.state = state;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getPostcode() {
    return postcode;
  }

  public void setPostcode(Integer postcode) {
    this.postcode = postcode;
  }

  public String getSuburb() {
    return suburb;
  }

  public void setSuburb(String suburb) {
    this.suburb = suburb;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "PostCodeDetail{" +
        "id=" + id +
        ", postcode=" + postcode +
        ", suburb='" + suburb + '\'' +
        ", state='" + state + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PostCodeDetail)) {
      return false;
    }
    PostCodeDetail other = (PostCodeDetail) o;
    return Objects.equals(this.postcode, other.postcode)
        && Objects.equals(this.suburb, other.suburb)
        && Objects.equals(this.state, other.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(postcode, suburb, state);
  }
}
