package com.github.biancode.wicket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.https.HttpsConfig;
import org.apache.wicket.protocol.https.HttpsMapper;
import org.apache.wicket.response.filter.AjaxServerAndClientTimeFilter;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.string.Strings;

import com.github.biancode.wicket.mobile.pages.MobileHomePage;
import com.github.biancode.wicket.web.pages.DesktopHomePage;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.DefaultThemeProvider;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ITheme;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.extensions.request.StaticResourceRewriteMapper;
import de.agilecoders.wicket.less.BootstrapLess;
import de.agilecoders.wicket.samples.pages.BaseCssPage;
import de.agilecoders.wicket.samples.pages.ComponentsPage;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import de.agilecoders.wicket.themes.markup.html.google.GoogleTheme;
import de.agilecoders.wicket.themes.markup.html.metro.MetroTheme;
import de.agilecoders.wicket.themes.markup.html.wicket.WicketTheme;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see net.aokv.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{

  private Properties properties = new Properties();

  public WicketApplication()
  {

    super();

    loadProperties();
    setConfigurationType(RuntimeConfigurationType.valueOf(properties.getProperty("configuration.type")));
  }

  @Override
  public Class<? extends WebPage> getHomePage()
  {

    return HomePage.class;
  }

  @Override
  public void init()
  {

    super.init();

    getResourceSettings().getResourceFinders().add(new WebApplicationPath(getServletContext(), "assets"));
    getResourceSettings().getResourceFinders().add(new WebApplicationPath(getServletContext(), "markup"));

    // new AnnotatedMountScanner().scanPackage("net.aokv.mobile.pages").mount(this);
    // new AnnotatedMountScanner().scanPackage("net.aokv.web.pages").mount(this);
    // new AnnotatedMountScanner().scanPackage("de.agilecoders.wicket.samples.pages").mount(this);

    configureSSLPortMapper();
    configureWicketCdi();
    configureAjax();
    configureBootstrap();

    if (Strings.isTrue(properties.getProperty("cdn.useCdn")))
    {
      final String cdn = properties.getProperty("cdn.baseUrl");
      StaticResourceRewriteMapper.withBaseUrl(cdn).install(this);
    }

    mountPages();

    mountPackages();
  }

  private void configureSSLPortMapper()
  {

    setRootRequestMapper(new HttpsMapper(getRootRequestMapper(), new HttpsConfig(8080, 443)));
  }

  private void configureWicketCdi()
  {

    new CdiConfiguration().configure(this);
  }

  private void configureAjax()
  {

    getApplicationSettings().setUploadProgressUpdatesEnabled(true);
    getRequestCycleSettings().addResponseFilter(new AjaxServerAndClientTimeFilter());
    getDebugSettings().setAjaxDebugModeEnabled(true);
  }

  private void mountPackages()
  {

    // mountPackage("/versis", DesktopHomePage.class);
    // mountPackage("/mversis", MobileHomePage.class);
  }

  private void mountPages()
  {

    mountPage("/app", DesktopHomePage.class);
    mountPage("/mobile", MobileHomePage.class);
    mountPage("/basecss", BaseCssPage.class);
    mountPage("/components", ComponentsPage.class);
  }

  public static WicketApplication get()
  {

    return (WicketApplication) Application.get();
  }

  private void configureBootstrap()
  {

    final IBootstrapSettings settings = new BootstrapSettings();
    final BootswatchThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.Cerulean);

    Bootstrap.install(this, settings);
    BootstrapLess.install(this);

    final ThemeProvider defaultThemeProvider = new DefaultThemeProvider()
    {

      {
        add(new MetroTheme());
        add(new GoogleTheme());
        add(new WicketTheme());

        for (ITheme theme : themeProvider.available())
        {
          if (!theme.name().equals("Amelia")) // Amelia theme has errors
          {
            add(theme);
          }
        }

        // defaultTheme(new GoogleTheme());
        defaultTheme(BootswatchTheme.Spacelab);
      }

    };

    settings.setThemeProvider(defaultThemeProvider);
  }

  public Properties getProperties()
  {

    return properties;
  }

  private void loadProperties()
  {

    try
    {
      InputStream stream = getClass().getResourceAsStream("/config.properties");
      try
      {
        properties.load(stream);
      }
      finally
      {
        IOUtils.closeQuietly(stream);
      }
    }
    catch (IOException e)
    {
      throw new WicketRuntimeException(e);
    }
  }
}
