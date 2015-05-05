package com.github.biancode.wicket.mobile.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@RequireHttps
@MountPath(value = "/mobile")
public class MobileHomePage extends BaseMobilePage
{

	private static final long serialVersionUID = 5575974591215824093L;

	public MobileHomePage(final PageParameters parameters)
	{

		super(parameters);

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		// TODO Add your page's components here

	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		super.renderHead(response);

		CssHeaderItem cssItem = CssHeaderItem.forUrl("assets/css/sass/mobile.css");
		response.render(cssItem);
	}
}
