package com.example.wicket.demo.examples.wizards;

import org.apache.wicket.extensions.wizard.IWizardModelListener;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.extensions.wizard.dynamic.DynamicWizardModel;
import org.apache.wicket.markup.html.WebPage;

public class WizardPage extends WebPage implements IWizardModelListener{


  @Override
  protected void onInitialize() {
    super.onInitialize();
    DynamicWizardModel model = new DynamicWizardModel(new FirstStep());
    model.addListener(this);
    add(new Wizard("wizard", model));
  }

  @Override
  public void onCancel() {
    setResponsePage(getApplication().getHomePage());
  }

  @Override
  public void onFinish() {
    setResponsePage(getApplication().getHomePage());
  }
}
