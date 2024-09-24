package com.example.wicket.demo.examples.wizards;

import org.apache.wicket.extensions.wizard.dynamic.DynamicWizardStep;
import org.apache.wicket.extensions.wizard.dynamic.IDynamicWizardStep;

public class SecondStep extends DynamicWizardStep {


  SecondStep(IDynamicWizardStep previousStep) {
    super(previousStep);
  }

  @Override
  public boolean isLastStep() {
    return true;
  }

  @Override
  public IDynamicWizardStep next() {
    return null;
  }
}
