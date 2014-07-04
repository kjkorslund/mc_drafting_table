package com.kjksoft.mcdesigner.client.lib.zipjs;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsDate;

public class JsZipEntry extends JavaScriptObject {
	public static interface Callback {
		public void onCreate(JsArray<JsZipEntry> entries);
	}
	
	protected JsZipEntry() { }
	
	public final native String getFilename() /*-{
		return this.filename;
	}-*/;
	
	public final native boolean isDirectory() /*-{
		return this.directory;
	}-*/;
	
	public final native int getCompressedSize() /*-{
		return this.compressedSize;
	}-*/;
	
	public final native boolean getUncompressedSize() /*-{
		return this.uncompressedSize;
	}-*/;
	
	public final native JsDate getLastModDate() /*-{
		return this.lastModDate;
	}-*/;
	
	public final native boolean getLastModDateRaw() /*-{
		return this.lastModDateRaw;
	}-*/;
	
	public final native String getComment() /*-{
		return this.comment;
	}-*/;
	
	public final native boolean getCrc32() /*-{
		return this.crc32;
	}-*/;
	
	// TODO: implement getData function.  Might be tricky because of callbacks.
}
