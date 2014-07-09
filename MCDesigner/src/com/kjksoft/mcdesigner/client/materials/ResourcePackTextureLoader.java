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
	private String url;
	private Command scheduledCommand = null;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public void postLoadRequest(TextureLoadRequest loadRequest) {
//		GWT.log("Posting load request: " + loadRequest.getMaterial().textureName);
		requestMap.put(loadRequest.getMaterial().textureName, loadRequest);
		
		if (scheduledCommand == null) {
			scheduledCommand = new Command() {

				@Override
				public void execute() {
					ReadResourcePackCallbacks callbacks = new ReadResourcePackCallbacks() {
						@Override
						public boolean shouldExtract(JsZipEntry zipEntry) {
							if (requestMap.size() > 0) {
								if (!zipEntry.isDirectory()) {
									try {
										String filename = getFilename(zipEntry);
										return requestMap.containsKey(filename);
									} catch (IndexOutOfBoundsException e) {
										GWT.log("Detected unsupported filename: " + zipEntry.getFilename());
									}
								}
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
							TextureLoadRequest request = requestMap.remove(filename);
							if (request == null) {
								GWT.log("Null load request: " + filename);
							} else {
								ImageElement img = Document.get().createImageElement();
								ImageLoadHandler loadHandler = new ImageLoadHandler(img, request);
								NativeEvents.register(img, loadHandler);
								
								img.setSrc(data64uri);
							}
						}

						/**
						 * @param zipEntry
						 * @return
						 * @throws StringIndexOutOfBoundsException
						 */
						private String getFilename(JsZipEntry zipEntry) throws IndexOutOfBoundsException {
							String filename = zipEntry.getFilename();
							int start = filename.lastIndexOf('/') + 1;
							int end = filename.lastIndexOf('.');
							return filename.substring(start, end);
						}
					};
					
					readResourcePack(url, callbacks);
					
					scheduledCommand = null;
				}
			};
			Scheduler.get().scheduleDeferred(scheduledCommand);
		}
	}
	
	
	
	private final native void readResourcePack(String url, ReadResourcePackCallbacks callbacks) /*-{
		var time = function() {
			return "[" + new Date() + "] ";
		}
//		console.log(time() + "Entering readResourcePack");
		
		var shouldExtractFn = $entry(function(entry) {
			return callbacks.@com.kjksoft.mcdesigner.client.materials.ResourcePackTextureLoader$ReadResourcePackCallbacks::shouldExtract(Lcom/kjksoft/mcdesigner/client/lib/zipjs/JsZipEntry;)(entry);
		});
		var onData64URIExtractedFn = $entry(function(entry, uri) {
			callbacks.@com.kjksoft.mcdesigner.client.materials.ResourcePackTextureLoader$ReadResourcePackCallbacks::onData64URIExtracted(Lcom/kjksoft/mcdesigner/client/lib/zipjs/JsZipEntry;Ljava/lang/String;)(entry, uri);
		});
		var errorFn = $entry(function(errorMsg) {
			console.log("Zip read error!  '" + errorMsg + "'");
			callbacks.@com.kjksoft.mcdesigner.client.materials.ResourcePackTextureLoader$ReadResourcePackCallbacks::onError(Ljava/lang/String;)(errorMsg);
		});
		
		var zip = $wnd.zip;
		var httpReader = new zip.HttpReader(url);
		
		var entryFn = function(entries) {
			for(var i=0; i<entries.length; i++) {
				var entry = entries[i];
//				console.log(time() + "Checking entry #" + i + " - " + entry.filename);
				if (shouldExtractFn(entry)) {
//					console.log("  Extracting entry (" + entry.filename + ")");
					var dataHandler = function(entry) {
						return function(data64URI) {
//							console.log("  Calling entry data callback (" + entry.filename + ")");
							onData64URIExtractedFn(entry, data64URI);
						};
					};
					entry.getData(new zip.Data64URIWriter(), dataHandler(entry));
				} else {
//					console.log("  Not extracting entry (" + entry.filename + ")");
				}
			}
//			console.log(time() + "Done checking entries");
		}
			
		var callbackFn = function(reader) {
//			console.log(time() + "Getting entries");
			reader.getEntries(entryFn);
//			console.log(time() + "Done getting entries");
//			console.log(time() + "Closing zip");
			reader.close(function() {
//				console.log(time() + "Zip closed");
			});
			
		}
		
//		console.log(time() + "Creating reader")
		zip.createReader(httpReader, callbackFn, errorFn);
//		console.log(time() + "Done creating reader");
	}-*/;

	private static interface ReadResourcePackCallbacks {
		public boolean shouldExtract(JsZipEntry zipEntry);
		public void onData64URIExtracted(JsZipEntry zipEntry, String data64URI);
		public void onError(String error);
	}
}