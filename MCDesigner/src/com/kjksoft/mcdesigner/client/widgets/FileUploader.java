package com.kjksoft.mcdesigner.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.kjksoft.mcdesigner.client.lib.filereader.FileUploadChangeHandler;
import com.kjksoft.mcdesigner.client.lib.filereader.JsFile;

public class FileUploader extends Composite {

	private static FileUploaderUiBinder uiBinder = GWT
			.create(FileUploaderUiBinder.class);

	interface FileUploaderUiBinder extends UiBinder<Widget, FileUploader> {
	}

	public FileUploader() {
		initWidget(uiBinder.createAndBindUi(this));
		
		fileUpload.addChangeHandler(new FileUploadChangeHandler() {
			@Override
			protected void onFileUpload(JsFile file) {
				GWT.log(file.getName());
				selectionHandler.onFileSelection(file);
			}
		});
		
	}

	@UiField HTMLPanel dropTarget;
	@UiField FileUpload fileUpload;
	@UiField Label headerLabel;
	
	private SelectionHandler selectionHandler = null;
	
	public void setHeaderText(String text) {
		headerLabel.setText(text);
	}

	public SelectionHandler getSelectionHandler() {
		return selectionHandler;
	}

	public void setSelectionHandler(SelectionHandler selectionHandler) {
		this.selectionHandler = selectionHandler;
	}

	public static interface SelectionHandler {
		public void onFileSelection(JsFile file);
	}
}
