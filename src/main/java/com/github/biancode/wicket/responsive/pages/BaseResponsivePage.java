package com.github.biancode.wicket.responsive.pages;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Duration;

import com.github.biancode.wicket.components.other.ApplicationImage;
import com.github.biancode.wicket.components.other.Clock;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.AffixBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.references.BootlintHeaderItem;
import de.agilecoders.wicket.core.markup.html.references.RespondJavaScriptReference;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;

/**
 * Basic WebPage to work for Project.
 * 
 * @author Klaus Landsdorf
 *
 */
@RequireHttps
public abstract class BaseResponsivePage extends GenericWebPage<Void>
{
	private static final long serialVersionUID = 3581401519542376322L;

	protected static final String MESSAGE_PARAM = "message";

	protected static final String LEVEL_PARAM = "level";

	protected static final String FEEDBACKPANEL_ID = "feedbackPanel";

	protected static final String DATE_FORMAT = "MM/dd/yyyy HH:mm a";

	private String projectBrand = "Wicket 7 Responsive Quickstart";

	protected final FeedbackPanel feedbackPanel;

	protected final Format dateFormatter;

	@Inject
	protected Conversation conversation;

	public BaseResponsivePage(final PageParameters parameters)
	{

		dateFormatter = new SimpleDateFormat(DATE_FORMAT);
		feedbackPanel = new FeedbackPanel(FEEDBACKPANEL_ID, new ComponentFeedbackMessageFilter(this));

		initWicketBootstrap();
		beginConversation();

		add(newNavbar("navbar"));
		add(newNavigation("navigation"));

		setPageTitle(getPageTitleModel());

		final Clock clock = new Clock("currentDate", TimeZone.getTimeZone("Europe/Berlin"));
		add(clock);
		clock.add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(60)));

		add(feedbackPanel.setOutputMarkupId(true));

		addOrReplace(ApplicationImage.getRelativeResourceImage("programLogo", "Logo.jpg"));

		displayMessage(parameters);

		add(new HeaderResponseContainer("footerJS", "footerJS"));

		add(new DebugBar("debug"));
	}

	public Component newNavbar(String markupId)
	{
		Navbar navbar = new Navbar(markupId);

		navbar.setPosition(Navbar.Position.TOP);
		navbar.setInverted(true);
		navbar.setBrandName(Model.of(getProjectBrand()));

		return navbar;
	}

	public Component newNavigation(String markupId)
	{
		WebMarkupContainer navigation = new WebMarkupContainer(markupId);
		navigation.add(new AffixBehavior("200"));
		navigation.setVisible(hasNavigation());

		return navigation;
	}

	protected boolean hasNavigation()
	{
		return false;
	}

	private void beginConversation()
	{

		if (conversation.isTransient())
		{
			conversation.begin();
		}
	}

	private void initWicketBootstrap()
	{
		// always add the bootstrap resources (css, js, icons)
		add(new BootstrapResourcesBehavior());
		// add js and css for tags input
		// add(new MainBehavior());
	}

	protected void setPageTitle(final IModel<String> titleModel)
	{

		addOrReplace(new Label("pageTitle", titleModel));
	}

	protected IModel<String> getPageTitleModel()
	{

		return new StringResourceModel("page.title", this, null);
	}

	private void displayMessage(final PageParameters parameters)
	{

		String message = parameters.get(MESSAGE_PARAM).toString(null);
		int messageLevel = parameters.get(LEVEL_PARAM).toInt(-1);

		if ((message != null) && (messageLevel != -1))
		{
			switch (messageLevel)
			{
				case FeedbackMessage.SUCCESS:
					success(message);
					break;
				case FeedbackMessage.INFO:
					info(message);
					break;
				case FeedbackMessage.WARNING:
					warn(message);
					break;
				case FeedbackMessage.ERROR:
					error(message);
					break;
			}
		}
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);

		response.render(RespondJavaScriptReference.headerItem());

		if (!getRequest().getRequestParameters().getParameterValue("bootlint").isNull())
		{
			response.render(BootlintHeaderItem.INSTANCE);
		}

		Bootstrap.renderHead(response);

		response.render(CssHeaderItem.forReference(FontAwesomeCssReference.instance()));
		
		CssHeaderItem cssItem = CssHeaderItem.forUrl("assets/css/normalize.css");
		response.render(cssItem);

		cssItem = CssHeaderItem.forUrl("assets/css/sass/base.css");
		response.render(cssItem);

		cssItem = CssHeaderItem.forUrl("assets/css/sass/responsive.css");
		response.render(cssItem);
	}

	public String getProjectBrand()
	{
		return projectBrand;
	}

	public void setProjectBrand(String projectBrand)
	{
		this.projectBrand = projectBrand;
		addOrReplace(newNavbar("navbar"));
	}
}
