package com.kjksoft.mcdesigner.client.lib.zipjs;

import com.google.gwt.core.client.JavaScriptObject;

public class JsZipReader extends JavaScriptObject {
	public static interface CreateCallback {
		public void onCreate(JsZipReader zipReader);
	}
	
	public static interface CloseCallback {
		public void onClose();
	}
	
	protected JsZipReader() { }
	
	public final native void getEntries(JsZipEntry.Callback callback) /*-{
		var callbackFn = $entry(function(zipEntries) {
			callback.@com.kjksoft.mcdesigner.client.lib.zipjs.JsZipEntry$Callback::onCreate(Lcom/google/gwt/core/client/JsArray;)(zipEntries);
		});
		this.getEntries(callbackFn);
	}-*/;
	
	public final native void close(CloseCallback callback) /*-{
		var callbackFn = $entry(function(zipEntries) {
			callback.@com.kjksoft.mcdesigner.client.lib.zipjs.JsZipReader$CloseCallback::onClose()();
		});
		this.close(callbackFn);
	}-*/;
}
