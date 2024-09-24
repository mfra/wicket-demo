package com.example.wicket.demo.examples.forms;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.Test;

class FormsPageTest {

  @Test
  void render() {

    WicketTester tester = new WicketTester();
    tester.startPage(FormsPage.class);
    tester.assertRenderedPage(FormsPage.class);

    tester.newFormTester("form")
        .submit();
    tester.assertErrorMessages(
        "Please enter a name.",
        "Choose a value between 18 and 99.");

    tester.newFormTester("form")
        .setValue("name", "John")
        .setValue("age", "1")
        .setValue("birthday", "1.1.1")
        .submit();
    tester.assertErrorMessages(
        "Choose a value between 18 and 99.",
        "This date seems to be invalid: null.");

    tester.newFormTester("form")
        .setValue("name", "John")
        .setValue("age", "18")
        .setValue("birthday", "2006-01-01")
        .submit();
    tester.assertNoErrorMessage();


  }
}
