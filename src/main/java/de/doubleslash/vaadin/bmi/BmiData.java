package de.doubleslash.vaadin.bmi;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Input data for BMI calculation.
 *
 * Class uses Bean Validation for the weight property.
 *
 * For this to work, e.g. hibernate-validator v. 4.3.0.Final has to be added to the
 * classpath / war file. The Bean Validator is then automatically detected via SPI and used
 * by the BeanFieldGroup class (later hibernate-validator versions such as 5.2.4.Final do not work
 * out of the box with Vaadin 7 due to an instantiation exception of the Validator factory).
 */
public class BmiData {

   @Max(value=500, message="Du bist zu schwer!")
   @Min(value=15, message="Du bist zu leicht!")
   @NotNull(message = "Bitte gib dein Gewicht ein!")
   private Integer weight;

   private Double height;

   public Integer getWeight() {
      return weight;
   }

   public void setWeight(Integer weight) {
      this.weight = weight;
   }

   public Double getHeight() {
      return height;
   }

   public void setHeight(Double height) {
      this.height = height;
   }
}
