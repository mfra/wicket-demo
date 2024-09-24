package com.example.wicket.demo.examples.behaviours;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.StyleAttributeModifier;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.DefaultCssAutoCompleteTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BehaviourPage extends WebPage {

  @Override
  protected void onInitialize() {
    super.onInitialize();


    Form<Object> form = new Form<>("form");
    add(form);

    form.add(new DefaultCssAutoCompleteTextField<String>("name"){
      @Override
      protected Iterator<String> getChoices(String input) {
          return List.of("John", "Maria").iterator();

      }
    });

    form.add(new TextField<String>("forename")
        .add(AttributeModifier.append("aria", "something"))
    );

    form.add(new StyleAttributeModifier() {
      @Override
      protected Map<String, String> update(Map<String, String> oldStyles) {
        return Map.of("text-shadow", "#FC0 1px 0 10px;");
      }
    });

  }
}
