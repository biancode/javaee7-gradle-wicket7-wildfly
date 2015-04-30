package de.agilecoders.wicket.samples.pages;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextField;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.DateTextFieldConfig;
import de.agilecoders.wicket.samples.components.basecss.DatePickerModal;
import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.joda.time.DateTime;
import org.wicketstuff.annotation.mount.MountPath;

/**
 * The {@code BaseCssPage}
 *
 * @author miha
 * @version 1.0
 */
@MountPath(value = "/datepicker")
public class DatePickerPage extends BasePage
{

  /**
   * Construct.
   *
   * @param parameters
   *          the current page parameters.
   */
  public DatePickerPage(PageParameters parameters)
  {

    super(parameters);

    add(
        newDefaultDatePicker("default"),
        new Code("default-html-code", Model.of("//HTML\n<form><input wicket:id=\"default\"></form>"))
            .setShowLineNumbers(true),
        new Code("default-java-code", Model.of("//JAVA\nadd(new DateTextField(\"default\"));"))
            .setShowLineNumbers(true));

    add(
        newDatePicker("birthday", new DateTextFieldConfig()
            .showTodayButton(DateTextFieldConfig.TodayButton.TRUE).withStartDate(new DateTime().withYear(1900))
            .autoClose(true).withView(DateTextFieldConfig.View.Decade)),
        new Code("birthday-html-code", Model.of("//HTML\n<form><input wicket:id=\"birthday\"></form>"))
            .setShowLineNumbers(true),
        new Code("birthday-java-code", Model
            .of("//JAVA\nadd(new DateTextField(\"birthday\",\n\t\t new DateTextFieldConfig()\n"
                + "\t\t\t.autoClose(true)\n" + "\t\t\t.withView(DateTextFieldConfig.View.Decade)\n"
                + "\t\t\t.showTodayButton(true)\n" + "\t\t\t.withStartDate(new DateTime().withYear(1900));"))
            .setShowLineNumbers(true));

    add(
        newDatePicker("language", new DateTextFieldConfig()
            .showTodayButton(DateTextFieldConfig.TodayButton.LINKED).autoClose(true).withLanguage("es")),
        new Code("language-html-code", Model.of("//HTML\n<form><input wicket:id=\"language\"></form>"))
            .setShowLineNumbers(true),
        new Code("language-java-code", Model
            .of("//JAVA\nadd(new DateTextField(\"language\",\n\t\t new DateTextFieldConfig()\n"
                + "\t\t\t.autoClose(true)\n" + "\t\t\t.withLanguage(\"es\")\n" + "\t\t\t.showTodayButton(true);"))
            .setShowLineNumbers(true));

    DatePickerModal modal = new DatePickerModal("modal");
    modal.show(false);
    modal.size(Modal.Size.Small);
    modal.setUseKeyboard(true);
    BootstrapButton modalButton = new BootstrapButton("modal-opener", Buttons.Type.Default);
    modalButton.setLabel(Model.of("Open Modal Dialog"));
    modal.addOpenerAttributesTo(modalButton);

    add(modal, modalButton);
  }

  private Component newDatePicker(String markupId, DateTextFieldConfig dateTextFieldConfig)
  {

    return new DateTextField(markupId, dateTextFieldConfig);
  }

  private Component newDefaultDatePicker(String markupId)
  {

    return newDatePicker(markupId, new DateTextFieldConfig());
  }

  @Override
  protected boolean hasNavigation()
  {

    return true;
  }
}
