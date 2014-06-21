package com.kjksoft.mcdesigner.client.gwt.event;

import com.google.gwt.dom.client.Element;

public class NativeEvents {
	public static void register(Element element, NativeEventHandler eventHandler) {
		_register(element, eventHandler);
	}
	
	private static native void _register(Element element, NativeEventHandler eventHandler) /*-{
		element.onload = function() {
			eventHandler.@com.kjksoft.mcdesigner.client.gwt.event.NativeEventHandler::onLoad()()
		}
	}-*/ ;
}
