package com.kjksoft.mcdesigner.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.kjksoft.mcdesigner.client.lib.filereader.FileUploadChangeHandler;
import com.kjksoft.mcdesigner.client.lib.filereader.JsFile;

public class ComboUploader extends Composite {

	private static ComboUploaderUiBinder uiBinder = GWT
			.create(ComboUploaderUiBinder.class);

	interface ComboUploaderUiBinder extends UiBinder<Widget, ComboUploader> {
	}

	public ComboUploader() {
		initWidget(uiBinder.createAndBindUi(this));
		linkButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (selectionHandler != null) {
					// TODO [kjk] This won't work because of XSS protections. It
					// can be circumvented, but only with the assistance of a
					// web server. So for now, I should only support HTML5 local
					// file upload.
					String url = URL.encode(linkBox.getText());
					selectionHandler.onLinkSelection(url);
				}
			}
		});
		fileUpload.addChangeHandler(new FileUploadChangeHandler() {
			@Override
			protected void onFileUpload(JsFile file) {
				GWT.log(file.getName());
			}
		});
	}

	@UiField Button linkButton;
	@UiField HTMLPanel dropTarget;
	@UiField TextBox linkBox;
	@UiField FileUpload fileUpload;
	
	private SelectionHandler selectionHandler = null;

	public SelectionHandler getSelectionHandler() {
		return selectionHandler;
	}

	public void setSelectionHandler(SelectionHandler selectionHandler) {
		this.selectionHandler = selectionHandler;
	}

	public static interface SelectionHandler {
		public void onLinkSelection(String url);
//		public void onFileSelection();
	}
}
