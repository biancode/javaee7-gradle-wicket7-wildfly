package de.agilecoders.wicket.samples.pages;

import java.util.List;

import javafx.animation.Animation;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.annotation.mount.MountPath;

import com.google.common.collect.Lists;

import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.common.NotificationPanel;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.TooltipConfig;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.ProgressBar;
import de.agilecoders.wicket.core.markup.html.bootstrap.components.progress.UploadProgressBar;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.Modal;
import de.agilecoders.wicket.core.markup.html.bootstrap.dialog.TextContentModal;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.extensions.javascript.jasny.FileUploadField;
import de.agilecoders.wicket.extensions.javascript.jasny.InputMaskBehavior;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior.Draggable;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior.DraggableConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.behavior.Resizable;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.button.DropDownAutoOpen;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.contextmenu.ButtonListContextMenu;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.checkboxx.CheckBoxX;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner.Spinner;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.form.spinner.SpinnerConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5Player;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Html5VideoConfig;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.html5player.Video;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.OpenWebIconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.OpenWebIconsCssReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaAjaxButton;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaAjaxLink;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.ladda.LaddaBehavior;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.tour.TourBehavior;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.tour.TourStep;

/**
 * The {@code ExtensionsPage}
 *
 * @author miha
 */
@MountPath(value = "/extensions")
public class ExtensionsPage extends BasePage
{

  /**
   * Construct.
   *
   * @param parameters
   *          the current page parameters.
   */
  public ExtensionsPage(PageParameters parameters)
  {

    super(parameters);

    List<Html5Player.IVideo> videos = Lists.<Html5Player.IVideo> newArrayList(new Video(
        "http://ia700305.us.archive.org/18/items/CopyingIsNotTheft/CINT_Nik_H264_720.ogv", "video/ogg"), new Video(
        "http://ia700305.us.archive.org/18/items/CopyingIsNotTheft/CINT_Nik_H264_720_512kb.mp4", "video/mp4"));
    add(new Html5Player("video", Model.ofList(videos)));
    add(new Code("video-code", Model.of("List<Html5Player.IVideo> videos = Lists.<Html5Player.IVideo>newArrayList(\n"
        + "\t\tnew Video(\"video.ogv\", \"video/ogg\"),\n" + "\t\tnew Video(\"video.mp4\", \"video/mp4\")\n" + ");\n"
        + "add(new Html5Player(\"video\", Model.ofList(videos)));")));

    add(new Html5Player("video-custom", Model.ofList(videos), new Html5VideoConfig()
        .showProgressBar(false).autoHideControlBar(false)).setWidth(680).setHeight(360));
    add(new Code(
        "video-custom-code", Model.of("List<Html5Player.IVideo> videos = Lists.<Html5Player.IVideo>newArrayList(\n"
            + "\t\tnew Video(\"video.ogv\", \"video/ogg\"),\n" + "\t\tnew Video(\"video.mp4\", \"video/mp4\")\n"
            + ");\n" + "add(new Html5Player(\"video\", Model.ofList(videos),\n"
            + "\tnew Html5VideoConfig().showProgressBar(false).autoHideControlBar(false))\n"
            + "\t\t.setWidth(680).setHeight(360));")));

    final List<? extends AbstractLink> buttons = Lists
        .<AbstractLink> newArrayList(
            new MenuBookmarkablePageLink<Void>(DatePickerPage.class, Model.of("DatePicker"))
                .setIconType(GlyphIconType.time),
            new MenuBookmarkablePageLink<Void>(IssuesPage.class, Model.of("Github Issues"))
                .setIconType(GlyphIconType.book),
            new MenuBookmarkablePageLink<Void>(ExtensionsPage.class, Model.of("Extensions"))
                .setIconType(GlyphIconType.qrcode));

    Modal<String> draggableModal = new TextContentModal(
        "draggable-modal",
        Model
            .of("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."))
    {

      @Override
      protected WebMarkupContainer createDialog(String id)
      {

        WebMarkupContainer dialog = super.createDialog(id);
        dialog.add(new Draggable(new DraggableConfig().withHandle(".modal-header").withCursor("move")));
        return dialog;
      }
    };
    draggableModal.add(new Resizable().withChildSelector(".modal-content"));
    draggableModal.setUseKeyboard(true).addCloseButton();
    draggableModal.setFadeIn(false);
    Label draggableButton = new Label("open-draggable", "Open Modal Dialog");
    draggableModal.addOpenerAttributesTo(draggableButton);
    add(draggableModal, draggableButton, new Code("draggable-code", Model.of("")));

    DropDownButton dropDownButton = new DropDownButton("dropdown", Model.of("open-on-hover"))
    {

      @Override
      protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId)
      {

        return Lists.<AbstractLink> newArrayList(
            new MenuBookmarkablePageLink<Void>(DatePickerPage.class, Model.of("DatePicker"))
                .setIconType(GlyphIconType.time),
            new MenuBookmarkablePageLink<Void>(IssuesPage.class, Model.of("Github Issues"))
                .setIconType(GlyphIconType.book),
            new MenuBookmarkablePageLink<Void>(ExtensionsPage.class, Model.of("Extensions"))
                .setIconType(GlyphIconType.qrcode));
      }
    };

    dropDownButton.add(new DropDownAutoOpen());
    add(dropDownButton, new Code("dropdown-code", Model.of("dropDownButton.add(new DropDownAutoOpen());")));

    addTour();
    add(new Icon("html5-colored", OpenWebIconType.html5_colored), new Icon("apml", OpenWebIconType.apml), new Icon(
        "feed", OpenWebIconType.feed_colored));
    add(
        new Icon("html5", OpenWebIconType.html5),
        new Code(
            "openwebicon-code",
            Model
                .of("response.render(JavaScriptHeaderItem.forReference(OpenWebIconsCssReference.instance()));\n\nadd(new Icon(\"html5\", OpenWebIconType.html5));")));

    addJasnyFileUploadDemo();
    addJasnyInputMaskDemo();

    laddaButton();

    checkboxX();

    spinnerSample();
  }

  private void spinnerSample()
  {

    final NotificationPanel feedback = new NotificationPanel("spinnerFeedback");
    feedback.setOutputMarkupId(true);
    final Number minValue = 20d;
    SpinnerConfig config = new SpinnerConfig();
    config
        .withPrefix("pre").withDecimals(2).withPostfix("post").withMin(minValue).withMax(30).withStep(.2)
        .withVerticalbuttons(true).withBootstap(2).withInitVal(24);
    Spinner<Double> spinner = new Spinner<Double>("spinner", config)
    {

      @Override
      protected boolean wantMinNotification()
      {

        return true;
      }

      @Override
      protected void onMin(AjaxRequestTarget target)
      {

        super.onMin(target);
        info("Reached the configured min value of " + minValue);
        target.add(feedback);
      }
    };
    add(spinner, feedback);
  }

  private void checkboxX()
  {

    CheckBoxX checkBoxX = new CheckBoxX("checkboxX", new Model<Boolean>(true))
    {

      @Override
      protected void onChange(Boolean value, AjaxRequestTarget target)
      {

        super.onChange(value, target);

        String s;
        if (Boolean.FALSE.equals(value))
        {
          s = "unchecked";
        }
        else if (Boolean.TRUE.equals(value))
        {
          s = "checked";
        }
        else
        {
          s = "undefined";
        }
        info("The selection is: " + s);
        target.add(getParent().get("feedback"));
      }
    };

    final NotificationPanel feedback = new NotificationPanel("feedback", checkBoxX);
    feedback.setOutputMarkupId(true);

    Code code = new Code(
        "linkCode", Model.of("CheckboxX checkboxX = new CheckboxX(\"checkboxX\", new Model<Boolean>(true)) {\n"
            + "  @Override\n" + "  protected void onChange(Boolean value, AjaxRequestTarget target) {\n"
            + "    info(\"The selection is: \" + value);\n" + "    target.add(feedback);\n" + "  }\n" + "};"));

    add(checkBoxX, feedback, code);
  }

  private void laddaButton()
  {

    Form form = new Form("laddaForm");
    add(form);

    LaddaAjaxButton laddaButton = new LaddaAjaxButton("laddaButton", Model.of("Button, 3secs"), form, Buttons.Type.Info)
    {

      @Override
      protected void onSubmit(AjaxRequestTarget target, Form<?> form)
      {

        super.onSubmit(target, form);

        Duration.seconds(3).sleep();
      }
    };
    laddaButton.setSize(Buttons.Size.Small);

    LaddaAjaxLink<String> laddaLink = new LaddaAjaxLink<String>(
        "laddaLink", Model.of("Link, 2secs"), Buttons.Type.Success)
    {

      @Override
      public void onClick(AjaxRequestTarget target)
      {

        Duration.seconds(2).sleep();
      }
    };
    laddaLink.setEffect(LaddaBehavior.Effect.EXPAND_LEFT).setSize(Buttons.Size.Medium);

    form.add(new Code("linkCode", Model
        .of("laddaLink = new LaddaAjaxLink<String>(\"laddaLink\", Model.of(\"Link, 2secs\"), Buttons.Type.Success) {\n"
            + "    @Override public void onClick(AjaxRequestTarget target) {\n"
            + "        Duration.seconds(2).sleep();\n" + "    }\n" + "};\n"
            + "laddaLink.setEffect(LaddaBehavior.Effect.EXPAND_LEFT).setSize(Buttons.Size.Medium);")));

    form.add(laddaButton, laddaLink);
  }

  @Override
  public void renderHead(IHeaderResponse response)
  {

    super.renderHead(response);

    response.render(CssHeaderItem.forReference(OpenWebIconsCssReference.instance()));
  }

  private void addJasnyFileUploadDemo()
  {

    Form<Void> jasnyFileUploadForm = new Form<Void>("jasnyFileUploadForm");
    add(jasnyFileUploadForm);
    jasnyFileUploadForm.setMultiPart(true);
    FileUploadField jasnyFileUpload = new FileUploadField("jasnyFileUpload");
    jasnyFileUploadForm.add(jasnyFileUpload);

    UploadProgressBar progressBar = new UploadProgressBar(
        "progressBar", jasnyFileUploadForm, jasnyFileUpload, Model.of(0));
    progressBar.striped(true).active(true).type(ProgressBar.Type.INFO);
    jasnyFileUploadForm.add(progressBar);
    jasnyFileUploadForm.add(new AjaxButton("submit")
    {
    });
  }

  private void addJasnyInputMaskDemo()
  {

    TextField<String> textField = new TextField<String>("inputMask", Model.of("l0rdn1kk0n"));
    InputMaskBehavior inputMask = new InputMaskBehavior()
    {

      @Override
      protected String getMask()
      {

        // Allow entering l0rdn1kk0n
        return "a9aaa9aa9a";
      }
    };
    textField.add(inputMask);
    add(textField);
  }

  /**
   * Demo for TourBehavior. Issue #116
   */
  private void addTour()
  {

    RepeatingView view = new RepeatingView("tourDemo");
    add(view);

    Label stepOne = new Label(view.newChildId(), "Step One");
    view.add(stepOne);

    Label stepTwo = new Label(view.newChildId(), "Step Two");
    view.add(stepTwo);

    Label stepThree = new Label(view.newChildId(), "Step Three");
    view.add(stepThree);

    TourBehavior tourBehavior = new TourBehavior()
    {

      @Override
      protected CharSequence createExtraConfig()
      {

        return "if ( tour.ended() ) {\n"
            + "    $('<div class=\"alert alert-info\">\\\n"
            + "      <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\\\n"
            + "      You ended the demo tour. <a href=\"\" class=\"restart\">Restart the demo tour.</a>\\\n"
            + "      </div>').prependTo(\".content\").alert();\n" + "  }\n" + "\n"
            + "  $(\".restart\").click(function (e) {\n" + "    e.preventDefault();\n" + "    tour.restart();\n"
            + "    $(this).parents(\".alert\").alert(\"close\");\n" + "  });";
      }
    };
    tourBehavior.addStep(new TourStep()
        .title(Model.of("Step One Title")).element(stepOne)
        .content(Model.of("Some longer help content <strong> for step <span style='color: red'>1</span>.")));
    tourBehavior.addStep(new TourStep()
        .title(new ResourceModel("tour.step.two")).element(stepTwo).placement(TooltipConfig.Placement.left)
        .content(Model.of("Some longer help content <strong> for step <span style='color: red'>2</span>."))
        .backdrop(true));
    tourBehavior.addStep(new TourStep()
        .title(Model.of("Step Three Title")).element(stepThree).placement(TooltipConfig.Placement.left)
        .content(Model.of("Some longer help content <strong> for step <span style='color: red'>3</span>.")));
    view.add(tourBehavior);
  }

  @Override
  protected boolean hasNavigation()
  {

    return true;
  }
}
