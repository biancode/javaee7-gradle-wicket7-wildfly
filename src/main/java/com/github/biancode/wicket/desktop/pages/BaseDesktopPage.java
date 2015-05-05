package com.github.biancode.wicket.desktop.pages;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.filter.HeaderResponseContainer;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.time.Duration;

import com.github.biancode.wicket.components.other.ApplicationImage;
import com.github.biancode.wicket.components.other.ApplicationJavaScriptBehavior;
import com.github.biancode.wicket.components.other.Clock;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.BootstrapResourcesBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuHeader;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.AffixBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.ImmutableNavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.core.markup.html.references.BootlintHeaderItem;
import de.agilecoders.wicket.core.markup.html.references.RespondJavaScriptReference;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeCssReference;

/**
 * Basic WebPage to work for Project.
 * 
 * @author Klaus Landsdorf
 *
 */
@RequireHttps
public abstract class BaseDesktopPage extends GenericWebPage<Void>
{
	private static final long serialVersionUID = 2297393916148474310L;

	protected static final String MESSAGE_PARAM = "message";

	protected static final String LEVEL_PARAM = "level";

	protected static final String FEEDBACKPANEL_ID = "feedbackPanel";

	protected static final String DATE_FORMAT = "MM/dd/yyyy HH:mm a";

	private String projectBrand = "Wicket 7 Quickstart";

	protected final FeedbackPanel feedbackPanel;

	protected final Format dateFormatter;

	@Inject
	protected Conversation conversation;

	public BaseDesktopPage(final PageParameters parameters)
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

		Image programLogo = ApplicationImage.getRelativeResourceImage("programLogo", "Logo.jpg");

		switch (getApplication().getConfigurationType())
		{
			case DEPLOYMENT:
			{
				programLogo.add(new AttributeAppender("class", new Model<String>("programlogo-right-deploy"), " "));
			}
				break;

			default:
			{
				programLogo.add(new AttributeAppender("class", new Model<String>("programlogo-right"), " "));
			}
		}

		addOrReplace(programLogo);

		displayMessage(parameters);

		initJavaScriptExtras();
		add(new HeaderResponseContainer("footerJS", "footerJS"));

		add(new DebugBar("debug"));
	}

	private void initJavaScriptExtras()
	{
		add(new ApplicationJavaScriptBehavior());
	}

	public Component newNavbar(String markupId)
	{
		Navbar navbar = new Navbar(markupId);

		navbar.fluid();
		navbar.setPosition(Navbar.Position.TOP);
		navbar.setInverted(true);

		navbar.setBrandName(Model.of(this.getProjectBrand()));
		navbar.setBrandImage(ApplicationImage.getRelativeResourceReference("Logo.jpg"), Model.of(this.getProjectBrand()));

		buildNavigation(navbar);

		// append CSS to brand
		Component brand = navbar.get(0).get("brandName").get("brandImage");
		brand.add(new AttributeAppender("class", new Model<String>("programlogo"), " "));

		return navbar;
	}

	private void buildNavigation(Navbar navbar)
	{
		DropDownButton dropdown = new NavbarDropDownButton(Model.of("Themes"))
		{
			@Override
			public boolean isActive(Component item)
			{
				return false;
			}

			@Override
			protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId)
			{
				final List<AbstractLink> subMenu = new ArrayList<AbstractLink>();
				subMenu.add(new MenuHeader(Model.of("all available themes:")));
				subMenu.add(new MenuDivider());

				final IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
				final List<ITheme> themes = settings.getThemeProvider().available();

				for (final ITheme theme : themes)
				{
					PageParameters params = new PageParameters();
					params.set("theme", theme.name());

					subMenu.add(new MenuBookmarkablePageLink<Void>(getPageClass(), params, Model.of(theme.name())));
				}

				return subMenu;
			}
		}.setIconType(GlyphIconType.book);

		navbar.addComponents(new ImmutableNavbarComponent(dropdown, Navbar.ComponentPosition.RIGHT));
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
		add(new BootstrapResourcesBehavior());
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

		cssItem = CssHeaderItem.forUrl("assets/css/sass/baseproject.css");
		response.render(cssItem);

		cssItem = CssHeaderItem.forUrl("assets/css/sass/desktop.css");
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

	private void configureTheme(PageParameters pageParameters)
	{
		StringValue theme = pageParameters.get("theme");

		if (!theme.isEmpty())
		{
			IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
			settings.getActiveThemeProvider().setActiveTheme(theme.toString(""));
		}
	}

	protected ITheme activeTheme()
	{
		IBootstrapSettings settings = Bootstrap.getSettings(getApplication());

		return settings.getActiveThemeProvider().getActiveTheme();
	}

	@Override
	protected void onConfigure()
	{
		super.onConfigure();

		configureTheme(getPageParameters());
	}
}
