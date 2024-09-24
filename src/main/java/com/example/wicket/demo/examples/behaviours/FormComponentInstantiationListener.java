package com.example.wicket.demo.examples.behaviours;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.markup.html.form.FormComponent;

public class FormComponentInstantiationListener implements IComponentInstantiationListener {

  @Override
  public void onInstantiation(Component component) {
    if (component instanceof FormComponent) {
      component.add(AttributeModifier.append("data-tracking", "id_" + component.getId()));
    }
  }
}
