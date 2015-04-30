package com.github.biancode.wicket.mobile.pages;

import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;

@RequireHttps
@MountPath(value = "/mobile")
public class MobileHomePage extends WebPage
{

  private static final long serialVersionUID = 1L;

  public MobileHomePage(final PageParameters parameters)
  {

    super(parameters);

    add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

    // TODO Add your page's components here

  }
}
