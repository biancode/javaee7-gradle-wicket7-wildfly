package com.github.biancode.wicket.web.pages;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath(value = "/app")
public class DesktopHomePage extends WebPage
{

  private static final long serialVersionUID = 1L;

  public DesktopHomePage(final PageParameters parameters)
  {

    super(parameters);

    add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

    // TODO Add your page's components here

  }
}
