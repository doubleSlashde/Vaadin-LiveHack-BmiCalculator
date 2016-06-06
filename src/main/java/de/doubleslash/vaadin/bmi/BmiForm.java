package de.doubleslash.vaadin.bmi;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.Caption;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class BmiForm extends FormLayout {

   // Not good to set the caption like this if you need to use i18n;
   // in this case use tfWeight.setCaption(...) in initLayout(...) instead.
   @Caption("Gewicht (kg):")
   @PropertyId("weight")
   private TextField tfWeight;

   @PropertyId("height")
   private TextField tfHeight;

   private Button buCalculate;

   private Label laBmi;

   private BmiData bmiData;
   private BeanFieldGroup<BmiData> bmiFieldGroup;

   public BmiForm() {
      bmiData = new BmiData();
      buildLayout();
      initLayout();
   }

   private void buildLayout() {
      // automatically instantiates tfWeight and tfHeight
      // and binds them to the BmiData instance:
      bmiFieldGroup = new BeanFieldGroup<>(BmiData.class);
      bmiFieldGroup.buildAndBindMemberFields(this);
      bmiFieldGroup.setItemDataSource(bmiData);

      buCalculate = new Button("Berechen!");

      laBmi = new Label("--");

      addComponents(tfWeight, tfHeight, buCalculate, laBmi);

      setSpacing(true);
      setMargin(true);
   }

   private void initLayout() {
      buCalculate.addClickListener(event -> {
         buttonClicked();
      });

      laBmi.setCaption("Dein BMI:");
      // IDs can be set on every component, e.g. to identify UI elements in automated UI tests
      laBmi.setId("la_bmi");

      tfHeight.addValidator(new NullValidator("Bitte gib deine Größe ein!", false));
      tfHeight.addValidator(new DoubleRangeValidator("Das ist kein Sinnvoller Wert!", 0.5, 3.0));
      tfHeight.setNullRepresentation("");
      tfHeight.setCaption("Größe (m):");

      // => BeanValidation in class BmiData is used for weight
      tfWeight.setNullRepresentation("");
      tfWeight.focus();
   }

   private void buttonClicked() {
      System.out.println("Klick!");

      try {
         // validate input , and if successful write it into the bmiData bean
         bmiFieldGroup.commit();

         double h = bmiData.getHeight();
         int w = bmiData.getWeight();

         double bmi = calculateBmi(h, w);
         laBmi.setValue(String.valueOf(bmi));

         System.out.println("Gewicht: " + w);
         System.out.println("Größe: " + h);

      } catch (FieldGroup.CommitException e) {
         // thrown if there are validation errors
         System.out.println("Invalid data!");
      }

   }

   /*
    * In a real-life production application we'd have this business logic in a separate class and
     * corresponding test cases!
    */
   private double calculateBmi(double height, int weight) {
      // bmi = weight / (height * height);
      double bmi = (double) weight / (height * height);
      return new BigDecimal(bmi).setScale(2, RoundingMode.HALF_UP).doubleValue();
   }

}
