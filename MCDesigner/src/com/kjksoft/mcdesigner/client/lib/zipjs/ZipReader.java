package com.kjksoft.mcdesigner.client.lib.zipjs;

import com.google.gwt.core.client.JavaScriptObject;

public class ZipReader extends JavaScriptObject {
	public static interface Callback {
		public void onCreate(ZipReader zipReader);
	}
}
