package com.example.wicket.demo.examples.inheritance;

import org.apache.wicket.markup.html.basic.Label;

public class SubSubPage extends SubPage {

  @Override
  protected void onInitialize() {
    super.onInitialize();

    add(new Label("subsubcontent", ()-> "sub-sub's fancy content"));

  }
}
