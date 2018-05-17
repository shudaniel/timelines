package com.timelines.cs48.app;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimelineController {

    @RequestMapping("/create")
    public Timeline create(@RequestParam(value="name", defaultValue="") String name, @RequestParam(value="description", defaultValue="") String description) {
      FirebaseSave saver = new FirebaseSave();
      Timeline newEntry = new Timeline(name, description);
      saver.saveTimeline(newEntry);
      return newEntry;
    }
}