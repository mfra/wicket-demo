package com.example.wicket.demo.examples.composition;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;

class SecondPanel extends GenericPanel<String> {
  SecondPanel(String id, IModel<String> model) {
    super(id, model);
  }


  @Override
  protected void onInitialize() {
    super.onInitialize();
    add(new Label("label", getModelObject()));

  }
}
