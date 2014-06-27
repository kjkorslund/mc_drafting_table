package com.kjksoft.mcdesigner.client.lib.zipjs;

import com.google.gwt.core.client.JavaScriptObject;

public class Zip extends JavaScriptObject {
	public static final native Zip getInstance() /*-{
		return $wnd.zip;
	}-*/;
	
	protected Zip() { }
	
	public final native void createZipReader(String url, ZipReader.Callback callback, ErrorCallback errorCallback) /*-{
		var reader = new this.HttpReader(url);
		var callbackFn = $entry(function(reader) {
			callback.@com.kjksoft.mcdesigner.client.lib.zipjs.ZipReader$Callback::onCreate(Lcom/kjksoft/mcdesigner/client/lib/zipjs/ZipReader;)(reader);
		});
		var errorFn = $entry(function(errorMsg) {
			errorCallback.@com.kjksoft.mcdesigner.client.lib.zipjs.ErrorCallback::onError(Ljava/lang/String;)(errorMsg);
		});
		this.createReader(reader, callbackFn, errorFn);
	}-*/;
}
