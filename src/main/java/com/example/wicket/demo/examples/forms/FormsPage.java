package com.example.wicket.demo.examples.forms;

import lombok.Builder;
import lombok.Data;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.feedback.FencedFeedbackPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

import java.io.Serializable;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.*;

public class FormsPage extends WebPage {

  @Data
  @Builder
  private static class FormData implements Serializable {

    private String name;
    private int age;
    private Date birthday;

  }

  public FormsPage() {
    super();
    setDefaultModel(new CompoundPropertyModel<>(FormData.builder().build()));
  }

  @Override
  protected void onInitialize() {
    super.onInitialize();

    Form<FormData> form = new Form<>("form", (IModel<FormData>) getDefaultModel()) {
      @Override
      protected void onSubmit() {
        super.onSubmit();
        FormData formData = getModelObject();
        info("hello " + formData.name);
      }
    };

    form.add(new FencedFeedbackPanel("feedback", form));

    form.add(new RequiredTextField<String>("name") {
      @Override
      protected void onInitialize() {
        super.onInitialize();
        add(new PatternValidator("\\w*"));
        add(StringValidator.lengthBetween(3, 30));
      }
    });

    NumberTextField<Integer> ageField = new NumberTextField<>("age");
    ageField.add(RangeValidator.range(18, 99));
    form.add(ageField);

    DateTextField birthdayField = new DateTextField("birthday", "yyyy-MM-dd");
    birthdayField.add((IValidator<Date>) validatable -> {
      Date value = validatable.getValue();
      Date today = truncate(Date.from(Instant.now()), Calendar.DATE);
      if (value.after(today)) {
        validatable.error((IValidationError) messageSource -> getString("birthday.Invalid"));
      }
    });
    form.add(birthdayField);


    form.add(new IFormValidator() {
      @Override
      public FormComponent<?>[] getDependentFormComponents() {
        return new FormComponent[]{ageField, birthdayField};
      }

      @Override
      public void validate(Form<?> form) {
        int expected = toCalendar(
            addYears(birthdayField.getConvertedInput(), ageField.getConvertedInput()))
            .get(Calendar.YEAR);
        int actual = toCalendar(Date.from(Instant.now())).get(Calendar.YEAR);
        if (actual != expected) {
          form.error("something ist wrong with your birthday year.");
        }

      }
    });

    form.add(new Button("submit"));
    add(form);
  }
}
