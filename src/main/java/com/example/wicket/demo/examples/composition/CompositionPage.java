package com.example.wicket.demo.examples.composition;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

public class CompositionPage extends WebPage {

  @Override
  protected void onInitialize() {
    super.onInitialize();


    add(new FirstPanel("first"));
    add(new SecondPanel("second", Model.of("model's text content")));




  }
}
