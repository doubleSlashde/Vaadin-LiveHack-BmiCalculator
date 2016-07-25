package de.doubleslash.vaadin.bmi;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 * Small Vaadin web app which calculates the Body Mass Index (BMI) of a person.
 *
 * The program originates from an 1/2 hour <b>live-hack</b> session during a workshop,
 * to demonstrate how quickly you can come up with a working web app from scratch using Vaadin.
 * <br>
 * So don't expect it to be "clean" from a code and architectural point of view!
 * Not to mention the missing test cases...! ;-)
 *
 * @author Stefan Waldmann, doubleSlash Net-Business GmbH
 */
@Theme("valo")
public class BmiApp extends UI {
   @Override
   public void init(VaadinRequest request) {
      BmiForm form = new BmiForm();
      setContent(form);
   }
}
