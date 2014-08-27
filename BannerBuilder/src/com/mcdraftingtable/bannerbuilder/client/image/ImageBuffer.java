package com.mcdraftingtable.bannerbuilder.client.image;

import java.util.LinkedList;

import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class ImageBuffer {
	private final LinkedList<ImageJob> jobQueue = new LinkedList<>();
	private CanvasElement imageData = null;
	
	public CanvasElement getImageData() {
		return imageData;
	}
	
	public void clear() {
		imageData = null;
	}

	public void loadFromImgSrc(String imgSrc) {
		imageData = null;
		
		final Image image = new Image();
		image.setVisible(false);
		RootPanel.get().add(image);
		
		image.addLoadHandler(new LoadHandler() {
			@Override
			public void onLoad(LoadEvent event) {
				ImageElement imageElement = image.getElement().cast();

				imageData = Document.get().createCanvasElement();
				imageData.setWidth(image.getWidth());
				imageData.setHeight(image.getHeight());
				imageData.getContext2d().drawImage(imageElement, 0, 0);

				image.removeFromParent();
				
				processJobQueue();
			}
		});
		
		image.setUrl(imgSrc);
	}
	
	public void loadFromCanvas(CanvasElement canvas) {
		imageData = Document.get().createCanvasElement();
		imageData.setWidth(canvas.getWidth());
		imageData.setHeight(canvas.getHeight());
		imageData.getContext2d().drawImage(canvas, 0, 0);
		processJobQueue();
	}
	
	public void runOrScheduleJob(ImageJob job) {
		jobQueue.addLast(job);
		if (imageData != null) {
			processJobQueue();
		}
	}
	
	private void processJobQueue() {
		while(imageData != null && !jobQueue.isEmpty()) {
			ImageJob job = jobQueue.removeFirst();
			job.run(imageData);
		}
	}
	
	public static interface ImageJob {
		public void run(CanvasElement imageData);
	}
}
