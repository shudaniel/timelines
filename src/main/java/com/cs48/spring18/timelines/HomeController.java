//Code taken from this spring-boot tutorial: 
//https://spring.io/guides/tutorials/react-and-spring-data-rest/
package com.cs48.spring18.timelines;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.*;
import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference.*;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class HomeController {

  FirebaseSave saver;

  public HomeController(){
    saver = new FirebaseSave();
  }

  @RequestMapping(value = "/")
  public String index() {
    return "index";
  }

  @RequestMapping(value = "/create")
  public void create(@RequestParam(value="name", defaultValue="") String name, @RequestParam(value="description", defaultValue="") String description) {
    Timeline newEntry = new Timeline(name, description);
    saver.saveTimeline(newEntry);
  }

  @RequestMapping(value = "/all-timelines")
  public void allTimelines(){

  }

  @RequestMapping(value = "/add-article")
  public void addArticle(){
    //STUB
    saver.saveArticle("2", new Article());
  }
  

}