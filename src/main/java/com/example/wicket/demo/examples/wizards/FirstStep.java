package com.example.wicket.demo.examples.wizards;

import org.apache.wicket.extensions.wizard.dynamic.DynamicWizardStep;
import org.apache.wicket.extensions.wizard.dynamic.IDynamicWizardStep;

public class FirstStep extends DynamicWizardStep {

  public FirstStep() {
    super(null);
  }

  @Override
  public boolean isLastStep() {
    return false;
  }

  @Override
  public IDynamicWizardStep next() {
    return new SecondStep(this);
  }
}
