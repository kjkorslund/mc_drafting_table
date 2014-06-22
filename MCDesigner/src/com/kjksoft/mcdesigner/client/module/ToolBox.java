package com.kjksoft.mcdesigner.client.module;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ToggleButton;
import com.kjksoft.mcdesigner.client.Tool;
import com.kjksoft.mcdesigner.client.ToolSelectionHandler;

public class ToolBox extends Composite {
	private final HashSet<ToolSelectionHandler> toolSelectionHandlers = new HashSet<ToolSelectionHandler>();
	
	private final LinkedHashMap<Tool,ToggleButton> buttons = new LinkedHashMap<Tool,ToggleButton>();
	private Tool selectedTool;
	
	public ToolBox() {
		HTMLPanel panel = new HTMLPanel("span","");
		panel.setStyleName("toolbox");
		initWidget(panel);
		
		buttons.put(Tool.PENCIL, createToolButton("Pencil", "glyphicons_030_pencil.png"));
		buttons.put(Tool.ERASER, createToolButton("Eraser", "glyphicons_030_pencil_rotated.png"));
		//buttons.put(Tool.FILL, new ToggleButton("F"));
		buttons.put(Tool.SCROLL, createToolButton("Scroll", "glyphicons_186_move.png"));
		
		panel.add(buttons.get(Tool.PENCIL));
		panel.add(buttons.get(Tool.ERASER));
		//panel.add(buttons.get(Tool.FILL));
		panel.add(buttons.get(Tool.SCROLL));
		
		for(Entry<Tool,ToggleButton> entry : buttons.entrySet()) {
			entry.getValue().addClickHandler(new ToolClickHandler(entry.getKey()));
			entry.getValue().addStyleDependentName("toolpanel");
			panel.add(entry.getValue());
		}
		
		selectTool(Tool.PENCIL);
	}
	
	private ToggleButton createToolButton(String title, String iconFileName) {
		Image image = new Image("res/icons/" + iconFileName);
		image.setStylePrimaryName("toolIcon");
		ToggleButton toggleButton = new ToggleButton(image);
		toggleButton.setTitle(title);
		return toggleButton;
	}
	
	private class ToolClickHandler implements ClickHandler {
		private Tool tool;

		public ToolClickHandler(Tool tool) {
			this.tool = tool;
		}
		
		@Override
		public void onClick(ClickEvent event) {
			selectTool(tool);
		}
	}
	
	public void selectTool(Tool tool) {
		selectedTool = tool;
		for(Entry<Tool,ToggleButton> entry : buttons.entrySet()) {
			if (entry.getKey() != tool) {
				entry.getValue().setDown(false);
				continue;
			}
			entry.getValue().setDown(true);
		}
		
		for(ToolSelectionHandler handler : toolSelectionHandlers) {
			handler.onToolSelection(selectedTool);
		}
	}
	
	public Tool getSelectedTool() {
		return selectedTool;
	}
	
	public void addToolSelectionHandler(ToolSelectionHandler handler) {
		toolSelectionHandlers.add(handler);
	}
	
	public void removeToolSelectionHandler(ToolSelectionHandler handler) {
		toolSelectionHandlers.remove(handler);
	}
}
