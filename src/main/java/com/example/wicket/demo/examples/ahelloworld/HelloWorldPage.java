package com.example.wicket.demo.examples.ahelloworld;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class HelloWorldPage extends WebPage {

  @Override
  protected void onInitialize() {
    super.onInitialize();
    add(new Label("message", "Hello World!"));
  }
}
