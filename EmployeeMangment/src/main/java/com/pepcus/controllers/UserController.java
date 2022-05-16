package com.pepcus.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pepcus.models.Book;
import com.pepcus.models.Shelves;
import com.pepcus.models.User;
import com.pepcus.repositorys.UserRepository;
import com.pepcus.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserServices userServices;
  
/*
 * add user in db
 */
  @PostMapping
  public ResponseEntity<User> addUsers(@Valid @RequestBody User user) {

    return new ResponseEntity<User>(userServices.saveUser(user), HttpStatus.CREATED);
  }
  
  @PutMapping
  public ResponseEntity<User> issueBookByUser(@Valid @RequestBody List<Book> book, @RequestParam Integer userId) {

    return new ResponseEntity<User>(userServices.issueBook(userId, book), HttpStatus.CREATED);
  }

  @PatchMapping("/deactivate")
  public ResponseEntity<User> deactivate(@Valid @RequestParam Integer userId) {
    return new ResponseEntity<User>(userServices.deactivateUser(userId), HttpStatus.OK);
  }

  @PatchMapping("/activate")
  public ResponseEntity<User> activate(@Valid @RequestParam Integer userId) {
    return new ResponseEntity<User>(userServices.activateUser(userId), HttpStatus.OK);
  }

  /*
   * this handler for return book from user
   */
  @DeleteMapping
  public ResponseEntity<String> returnBookFromUser(@Valid @RequestBody List<Book> book, @RequestParam Integer userId) {
    userServices.returnBooks(userId, book);
    return new ResponseEntity<String>("Book return successfully!......", HttpStatus.OK);
  }

}
