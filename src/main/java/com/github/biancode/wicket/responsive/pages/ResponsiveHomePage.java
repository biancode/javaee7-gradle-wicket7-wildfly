package com.github.biancode.wicket.responsive.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath(value = "/repsonsive")
public class ResponsiveHomePage extends BaseResponsivePage
{

	private static final long serialVersionUID = 7132887694037303878L;

	public ResponsiveHomePage(final PageParameters parameters)
	{

		super(parameters);

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		// TODO Add your page's components here

	}
}
