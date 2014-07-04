package com.kjksoft.mcdesigner.client.materials;

import java.util.HashMap;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.Command;
import com.kjksoft.mcdesigner.client.gwt.event.NativeEvents;
import com.kjksoft.mcdesigner.client.lib.zipjs.JsZipEntry;

public class ResourcePackTextureLoader extends TextureLoader {
	HashMap<String, TextureLoadRequest> requestMap = new HashMap<String, TextureLoadRequest>();
	private Command scheduledCommand = null;
	
	@Override
	public void postLoadRequest(TextureLoadRequest loadRequest) {
		requestMap.put(loadRequest.getMaterial().textureName, loadRequest);
		
		if (scheduledCommand == null) {
			scheduledCommand = new Command() {
				@Override
				public void execute() {
					ReadResourcePackCallbacks callbacks = new ReadResourcePackCallbacks() {
						@Override
						public boolean shouldExtract(JsZipEntry zipEntry) {
							if (requestMap.size() > 0) {
								String filename = getFilename(zipEntry);
								return requestMap.containsKey(filename);
							}
							return false;
						}
						
						@Override
						public void onError(String error) {
							GWT.log("Error loading resource pack: " + error);
						}
						
						@Override
						public void onData64URIExtracted(JsZipEntry zipEntry, String data64uri) {
							String filename = getFilename(zipEntry);
							TextureLoadRequest request = requestMap.get(filename);
							
//							System.out.println("Posting request for material " + material);
							ImageElement img = Document.get().createImageElement();
							ImageLoadHandler loadHandler = new ImageLoadHandler(img, request);
							NativeEvents.register(img, loadHandler);
							
							img.setSrc(data64uri);
						}

						private String getFilename(JsZipEntry zipEntry) {
							String filename = zipEntry.getFilename();
							int start = filename.lastIndexOf('/') + 1;
							int end = filename.lastIndexOf('.');
							return filename.substring(start, end);
						}
					};
					
					// TODO: load zip file and look for matches to the request
					// map. When a match is found, call the callback from the
					// load request
					
					scheduledCommand = null;
				}
			};
			Scheduler.get().scheduleDeferred(scheduledCommand);
		}
	}
	
	
	
	private final native void readResourcePack(String url, ReadResourcePackCallbacks callbacks) /*-{
		var shouldExtractFn = $entry(function(entry) {
			callbacks.@com.kjksoft.mcdesigner.client.materials.ResourcePackTextureLoader$ReadResourcePackCallbacks::shouldExtract(Lcom/kjksoft/mcdesigner/client/lib/zipjs/JsZipEntry;)(entry);
		});
		var onData64URIExtractedFn = $entry(function(entry, uri) {
			callbacks.@com.kjksoft.mcdesigner.client.materials.ResourcePackTextureLoader$ReadResourcePackCallbacks::onData64URIExtracted(Lcom/kjksoft/mcdesigner/client/lib/zipjs/JsZipEntry;Ljava/lang/String;)(entry, uri);
		});
		var errorFn = $entry(function(errorMsg) {
			callbacks.@com.kjksoft.mcdesigner.client.materials.ResourcePackTextureLoader$ReadResourcePackCallbacks::onError(Ljava/lang/String;)(errorMsg);
		});
		
		var zip = $wnd.zip;
		var httpReader = new zip.HttpReader(url);
		
		var entryFn = function(entries) {
			for(var i=0; i<entries.length; i++) {
				var entry = entries[i];
				console.log("Checking entry #" + i);
				if (shouldExtractFn(entry)) {
					var dataFn = function(data64URI) {
						console.log("Data function callback");
						onData64URIExtractedFn(entry, data64URI);
					}
					entry.getData(new zip.Data64URIWriter(), dataFn);
				}
			}
			console.log("Done checking entries");
		}
			
		var callbackFn = function(reader) {
			reader.getEntries(entryFn);
		}
		
		zip.createReader(httpReader, callbackFn, errorFn);
	}-*/;

	private static interface ReadResourcePackCallbacks {
		public boolean shouldExtract(JsZipEntry zipEntry);
		public void onData64URIExtracted(JsZipEntry zipEntry, String data64URI);
		public void onError(String error);
	}
}
