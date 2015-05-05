package com.github.biancode.wicket.components.other;

import java.io.Serializable;

public class JavaScriptHelper implements Serializable
{
	// Filenames
	public static final String MAIN_JS = "assets/js/main.js";
	public static final String JQUERY_TABLESORT_JS = "assets/js/jquery.tablesorter.min.js";
	public static final String JQUERY_UI_JS = "assets/js/jquery-ui-1.9.1.custom.min.js";
	
	// Id's
	public static final String MAIN_JS_REF_ID = "js_main";
	public static final String JQUERY_TABLESORT_JS_REF_ID = "js_tablesort";
	public static final String JQUERY_UI_JS_REF_ID = "js_ui";
	
	// Templates
	public static final String LOAD_TABLE_SORTER = "activateTableSort('%s');";
	public static final String INIT_COLLAPSE = "initCollapse();";
	public static final String TOGGLE_COLLAPSE = "toggleCollapse('%s');";
	public static final String CLOSE_COLLAPSE = "closeCollapse('%s');";
	public static final String SHOW_COLLAPSE = "showCollapse('%s');";
	public static final String PREPEND_ELEM_TEMPLATE = "notify|prependElemToContainer('%s', '%s', '%s');";
	public static final String APPEND_ELEM_TEMPLATE = "notify|appendElemToContainer('%s', '%s', '%s');";
	public static final String FADE_IN_ELEM_TEMPLATE = "fadeInElem('%s');";
	public static final String FADE_OUT_ELEM_TEMPLATE = "notify|fadeOutElem('%s', notify);";
	public static final String FADE_OUT_AND_REMOVE_ELEM_TEMPLATE = "notify|fadeOutAndRemoveElem('%s', notify);";
	public static final String SLIDE_UP_ELEM_TEMPLATE = "notify|slideUp('%s', notify);";
	public static final String SLIDE_DOWN_ELEM_TEMPLATE = "notify|slideDown('%s', notify);";
}
