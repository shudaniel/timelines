package com.cs48.spring18.timelines;

import com.google.firebase.*;
import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference.*;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ArticleSaver{

  private DatabaseReference.CompletionListener listener;

  public ArticleSaver(DatabaseReference.CompletionListener listener){
    this.listener = listener;
  }

  //Precondition: There is an existing Timeline with an id matching timeline_id
  //Postcondition: A new Article, item, is saved in the database and belongs to corresponding timeline
  public void save(String timeline_id, Article item){

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("/timelines/");

    DatabaseReference timelinesRef = (ref.child(timeline_id)).child("articles");
    DatabaseReference pushedRef = timelinesRef.push();
    String postId = pushedRef.getKey(); //Generate a unique ID for item
    item.setId(postId);
    Map<String, Object> article = new HashMap<>();
    article.put(item.getId(), item);


    timelinesRef.updateChildren(article, listener);

    try{
      Thread.sleep(10000);
    }
    catch(InterruptedException e){
      System.out.println("Thread interrupted");
    }
  }

  public void save(String timeline_id, ArrayList<Article> articles){
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("/timelines/");
    DatabaseReference timelinesRef = (ref.child(timeline_id)).child("articles");
    Map<String, Object> data = new HashMap<>();

    for(Article a : articles){
      DatabaseReference pushedRef = timelinesRef.push();
      String postId = pushedRef.getKey(); //Generate a unique ID for item
      a.setId(postId);
      data.put(a.getId(), a);
    }

    timelinesRef.updateChildren(data, listener);

  }

  //Precondition: timeline_id is the id of a Timeline in the database
  // This timeline has an article with an id that matches article_id
  //Postcondition: The article that matches article_id will be deleted from the timeline whose id matches timeline_id
  public void delete(String timeline_id, String article_id){
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("/timelines/" + timeline_id + "/articles/");
    ref.child(article_id).removeValue(listener);
  }
}