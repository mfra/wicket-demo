package com.example.wicket.demo.examples.inheritance;

import org.apache.wicket.markup.html.basic.Label;

public class SubPage extends AbstractBasePage {

  @Override
  protected void onInitialize() {
    super.onInitialize();
    add(new Label("subcontent", ()-> "sub's fancy content"));
  }
}
