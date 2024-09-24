package com.example.wicket.demo.examples.inject;

import com.example.wicket.demo.examples.inject.controller.DemoController;
import jakarta.inject.Inject;
import net.bytebuddy.utility.RandomString;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class InjectPage extends WebPage {

  @Inject
  private DemoController demoController;

  @Override
  protected void onInitialize() {
    super.onInitialize();
    add(new Label("message", () -> demoController.hello(RandomString.make(8))));
  }
}
