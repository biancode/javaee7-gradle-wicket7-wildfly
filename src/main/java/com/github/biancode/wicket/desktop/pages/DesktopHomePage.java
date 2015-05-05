package com.github.biancode.wicket.desktop.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath(value = "/desktop")
public class DesktopHomePage extends BaseDesktopPage
{

	private static final long serialVersionUID = -3041441094213338074L;

	public DesktopHomePage(final PageParameters parameters)
	{

		super(parameters);

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		// TODO Add your page's components here

	}
}
