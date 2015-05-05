package com.github.biancode.wicket.components.other;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;

public class Clock extends Label
{
	private static final long serialVersionUID = 5528206840891813085L;

	public Clock(String id, TimeZone tz)
	{
		super(id, new ClockModel(tz));
		this.setOutputMarkupId(true);
	}

	private static class ClockModel extends AbstractReadOnlyModel<String>
	{
		private static final long serialVersionUID = -4351860649379167019L;
		private final DateFormat df;

		public ClockModel(TimeZone tz)
		{
			df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT);
			df.setTimeZone(tz);
		}

		@Override
		public String getObject()
		{
			return df.format(new Date());
		}
	}
}
