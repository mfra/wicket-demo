package com.example.wicket.demo.examples.models;

import lombok.Builder;
import lombok.Data;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ChainingModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

import java.io.Serializable;

public class ModelsPage extends WebPage {

  @Data
  @Builder
  private static class DataObject implements Serializable {
    private String value;
  }

  public ModelsPage() {
    setDefaultModel(Model.of(DataObject.builder().value("aValue").build()));
  }

  @Override
  protected void onInitialize() {
    super.onInitialize();

    add(new Label("first", "direct"));
    add(new Label("second", () -> "lamda")); // like IModel#getObject
    add(new Label("third", Model.of("factory")));
    add(new Label("fourth", new StringResourceModel("key", this)));
    add(new Label("fifth", new PropertyModel<>(getDefaultModel(), "value")));
    add(new Label("sixth", new ChainingModel<String>(getDefaultModelObject())));

  }
}
