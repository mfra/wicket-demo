package com.example.wicket.demo.examples;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.PackageResourceReference;

@WicketHomePage
public class HomePage extends WebPage {

  @Override
  protected void onInitialize() {
    super.onInitialize();


    add(new Image("logo", new PackageResourceReference(HomePage.class, "logo.png")));
  }
}
