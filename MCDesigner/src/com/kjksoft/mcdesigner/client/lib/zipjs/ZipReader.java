package com.kjksoft.mcdesigner.client.lib.zipjs;

import com.google.gwt.core.client.JavaScriptObject;

public class ZipReader extends JavaScriptObject {
	public static interface Callback {
		public void onCreate(ZipReader zipReader);
	}
	
	protected ZipReader() { }
	
	public final native void getEntries(ZipEntry.Callback callback) /*-{
		var callbackFn = $entry(function(zipEntries) {
			callback.@com.kjksoft.mcdesigner.client.lib.zipjs.ZipEntry$Callback::onCreate(Lcom/google/gwt/core/client/JsArray;)(zipEntries);
		});
		this.getEntries(callbackFn);
	}-*/;
}
