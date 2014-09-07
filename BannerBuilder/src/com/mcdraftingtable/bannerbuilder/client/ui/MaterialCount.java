package com.mcdraftingtable.bannerbuilder.client.ui;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.mcdraftingtable.bannerbuilder.client.image.ImageBuffer;
import com.mcdraftingtable.bannerbuilder.client.image.ImageBuffer.ImageJob;

public class MaterialCount extends Composite {

	private static MaterialCountUiBinder uiBinder = GWT
			.create(MaterialCountUiBinder.class);

	interface MaterialCountUiBinder extends UiBinder<Widget, MaterialCount> {
	}
	
	@UiField CanvasElement iconCanvas;
	@UiField SpanElement countSpan;

	public MaterialCount() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setMaterialCount(ImageBuffer icon, int count) {
		icon.runOrScheduleJob(new ImageJob() {
			@Override
			public void run(CanvasElement imageData) {
				Context2d ctx = iconCanvas.getContext2d();
				ctx.drawImage(imageData, 0, 0, iconCanvas.getWidth(), iconCanvas.getHeight());
			}
		});
		countSpan.setInnerText(Integer.toString(count));
	}
}
