package com.example.wicket.demo.examples.inject.controller;

import org.springframework.stereotype.Service;

@Service
public class DemoController {


  public String hello(String message) {
    return "Hello " + message;
  }

}
