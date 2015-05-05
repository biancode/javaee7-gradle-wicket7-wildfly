package com.github.biancode.wicket.components.other;

import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.request.resource.ContextRelativeResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class ApplicationImage
{
	// working relative in src/main/webapp folder
	public static ContextImage getRelativeResourceContextImage(String id, String imageFileName)
	{
		return new ContextImage(id, new Model<String>("assets/img/" + imageFileName));
	}

	public static Image getRelativeResourceImage(String id, String imageFileName)
	{
		return new Image(id, new ContextRelativeResource("/assets/img/" + imageFileName));
	}

	public static ResourceReference getRelativeResourceReference(String imageFileName)
	{
		return new ContextRelativeResourceReference("assets/img/" + imageFileName, false);
	}
}
