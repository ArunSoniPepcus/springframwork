package com.pepcus.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "users_name")
  private String name;
  
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date registrationDate;
  
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date deactivateOn;
  
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date issueOn;
  
  @Valid
  @OneToMany(cascade = CascadeType.ALL)
  private List<Book> bookList;
  
}
