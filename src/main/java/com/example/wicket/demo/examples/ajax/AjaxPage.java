package com.example.wicket.demo.examples.ajax;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class AjaxPage extends WebPage {


  @Override
  protected void onInitialize() {
    super.onInitialize();


    add(new AjaxLink<>("link") {
      @Override
      public void onClick(AjaxRequestTarget target) {

        WebMarkupContainer place = (WebMarkupContainer) AjaxPage.this.findPage().stream()
            .filter(c -> c.getId().equals("place"))
            .findFirst()
            .orElseThrow();
        target.add(place);

        place.setVisible(true);
        place.addOrReplace(new Label("message", () -> now().format(ISO_DATE_TIME) ));

      }
    });

    add(new WebMarkupContainer("place") {
      @Override
      protected void onInitialize() {
        super.onInitialize();
        add(new Label("message", ""));

      }

      @Override
      protected void onConfigure() {
        super.onConfigure();
        setOutputMarkupId(true);
      }
    });


  }
}
