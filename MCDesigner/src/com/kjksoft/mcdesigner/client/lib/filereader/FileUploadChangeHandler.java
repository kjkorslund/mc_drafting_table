package com.kjksoft.mcdesigner.client.lib.filereader;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;

public abstract class FileUploadChangeHandler implements ChangeHandler {
	@Override
	public void onChange(ChangeEvent event) {
		JsFile file = getFile(event.getNativeEvent());
		onFileUpload(file);
	}
	
	protected abstract void onFileUpload(JsFile file);
	
	private native JsFile getFile(NativeEvent event) /*-{
//		console.log(event);
//		console.log(event.target);
//		console.log(event.target.files[0]);
		return event.target.files[0];
	}-*/;
}
