package com.example.wicket.demo.examples.ajax;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

class AjaxPageTest {

  @Test
  void render() {
    WicketTester tester = new WicketTester();
    tester.startPage(AjaxPage.class);
    tester.assertRenderedPage(AjaxPage.class);
    tester.clickLink("link", true);
    //System.out.println(tester.getLastResponseAsString());
    tester.assertComponentOnAjaxResponse("place");
  }
}
