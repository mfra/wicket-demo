package com.example.wicket.demo.examples.inheritance;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public abstract class AbstractBasePage extends WebPage {


  @Override
  protected void onInitialize() {
    super.onInitialize();

    add(new Label("title", getString("title")));

    add(new BookmarkablePageLink<>("homepage", getApplication().getHomePage()));

  }
}
