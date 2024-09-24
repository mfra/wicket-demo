package com.example.wicket.demo;

import com.example.wicket.demo.examples.behaviours.FormComponentInstantiationListener;
import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import org.apache.wicket.protocol.http.WebApplication;

@ApplicationInitExtension
public class WicketDemoApplicationExtension implements WicketApplicationInitConfiguration {
  @Override
  public void init(WebApplication app) {

    app.getCspSettings()
        .blocking().disabled();

    app.getComponentInstantiationListeners().add(new FormComponentInstantiationListener());

  }

}
