package com.example.wicket.demo.examples.ahelloworld;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

class HelloWorldPageTest {

  @Test
  void render() {
    WicketTester tester = new WicketTester();
    tester.startPage(HelloWorldPage.class);
    //tester.debugComponentTrees();
    tester.assertRenderedPage(HelloWorldPage.class);
    tester.assertComponent("message", Label.class);
    tester.assertModelValue("message", "Hello World!");
  }
}
