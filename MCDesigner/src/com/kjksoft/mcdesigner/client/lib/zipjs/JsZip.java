package com.kjksoft.mcdesigner.client.lib.zipjs;

import com.google.gwt.core.client.JavaScriptObject;

public class JsZip extends JavaScriptObject {
	public static final native JsZip getInstance() /*-{
		return $wnd.zip;
	}-*/;
	
	protected JsZip() { }
	
	public final native void createZipReader(String url, JsZipReader.CreateCallback callback, ErrorCallback errorCallback) /*-{
		var reader = new this.HttpReader(url);
		var callbackFn = $entry(function(reader) {
			callback.@com.kjksoft.mcdesigner.client.lib.zipjs.JsZipReader$CreateCallback::onCreate(Lcom/kjksoft/mcdesigner/client/lib/zipjs/JsZipReader;)(reader);
		});
		var errorFn = $entry(function(errorMsg) {
			errorCallback.@com.kjksoft.mcdesigner.client.lib.zipjs.ErrorCallback::onError(Ljava/lang/String;)(errorMsg);
		});
		this.createReader(reader, callbackFn, errorFn);
	}-*/;
}
