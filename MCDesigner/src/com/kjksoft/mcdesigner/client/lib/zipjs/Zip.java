package com.kjksoft.mcdesigner.client.lib.zipjs;

import com.google.gwt.core.client.JavaScriptObject;
import com.kjksoft.mcdesigner.client.lib.zipjs.ZipReader.Callback;

public class Zip extends JavaScriptObject {
	public static final native Zip getInstance() /*-{
		return $wnd.zip;
	}-*/;
	
	public final native Reader newTextReader(String text) /*-{
		return new this.TextReader(text);
	}-*/;
	
	public final native Reader newBlobReader(JavaScriptObject blob) /*-{
		return new this.BlobReader(blob);
	}-*/;
	
	public final native Reader newData64URIReader(String dataURI) /*-{
		return new this.Data64URIReader(dataURI);
	}-*/;
	
	public final native Reader newHttpReader(String url) /*-{
		return new this.HttpReader(url);
	}-*/;
	
	public final native Reader newHttpRangeReader(String url) /*-{
		return new this.HttpReader(url);
	}-*/;
	
	public final native Writer newTextWriter() /*-{
		return new this.TextWriter(text);
	}-*/;
	
	public final native Writer newBlobWriter() /*-{
		return new this.BlobWriter();
	}-*/;
	
	
	public final native void create(Reader reader, ZipReader.Callback callback, ErrorCallback error) /*-{
		
	}-*/;
	
	public static void importResPack() {
		_importResPack();
	}
	
	private static native void _importResPack() /*-{
		$wnd.alert("Native alert test");
		$wnd.console.log("Log test");
		
		// use a BlobReader to read the zip from a Blob object
		$wnd.zip.createReader(new $wnd.zip.BlobReader(blob), function(reader) {
		
		  // get all entries from the zip
		  reader.getEntries(function(entries) {
		    if (entries.length) {
		
		      // get first entry content as text
		      entries[0].getData(new $wnd.zip.TextWriter(), function(text) {
		        // text contains the entry data as a String
		        $wnd.console.log(text);
		
		        // close the zip reader
		        reader.close(function() {
		          // onclose callback
		        });
		
		      }, function(current, total) {
		        // onprogress callback
		      });
		    }
		  });
		}, function(error) {
		  // onerror callback
		});
		
	}-*/;
}
