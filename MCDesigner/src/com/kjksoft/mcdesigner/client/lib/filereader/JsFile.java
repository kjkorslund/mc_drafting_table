package com.kjksoft.mcdesigner.client.lib.filereader;

import com.google.gwt.core.client.JavaScriptObject;

public class JsFile extends JavaScriptObject {
	// TODO: This class should actually extend from a JsBlob object

	protected JsFile() { }
	
	public final native String getName() /*-{
		return this.name;
	}-*/;
}
