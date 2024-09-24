package com.example.wicket.demo.examples.events;

import lombok.Builder;
import lombok.Data;
import org.apache.wicket.Component;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class EventPage extends WebPage {

  private String message = "";

  @Data
  @Builder
  private static class ClickEvent {
    private String payload;
  }

  public EventPage() {
    setDefaultModel(new PropertyModel<String>(this, "message"));
  }

  @Override
  protected void onInitialize() {
    super.onInitialize();

    add(newPanel("panel1"));
    add(newPanel("panel2"));

  }

  private Component newPanel(String id) {
    return new Fragment(id, "frag1", EventPage.this){
      @Override
      protected void onInitialize() {
        super.onInitialize();
        add(new Label("message", () -> id + " : " + getModel().getObject()));
        add(new Link<>("link") {
          @Override
          public void onClick() {
            EventPage.this.getModel().setObject("clicked!");
            send(getPage(), Broadcast.BREADTH, ClickEvent.builder().payload(id).build());
          }
        });
      }
    };
  }

  @Override
  public void onEvent(IEvent<?> event) {
    super.onEvent(event);

    if(event.getPayload() instanceof ClickEvent) {
      ClickEvent clickEvent = (ClickEvent) event.getPayload();
      message = clickEvent.getPayload() + " says: " + message;
    }

  }

  private IModel<String> getModel(){
    return (IModel<String>) getDefaultModel();
  }

}
