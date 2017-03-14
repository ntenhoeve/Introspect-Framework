package nth.reflect.javafx.control.tabpane;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import javafx.scene.control.Skin;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.style.MaterialStyle;

import com.jfoenix.skins.JFXTabPaneSkin;

/**
 * RfxTabPane is the material design implementation of a tab pane. 
 * 
 * @author  original by: Shadi Shaheen, adopted by Nils ten Hoeve 
 * @version 1.0
 * @since   2016-03-09
 * 
 * 
 */
public class RfxTabPane extends TabPane {

	private MaterialStyle materialStyle;
	private final UserInterfaceContainer userInterfaceContainer;

	/**
	 * {@inheritDoc}
	 */
	public RfxTabPane(UserInterfaceContainer interfaceContainer) {
		super();
		userInterfaceContainer = interfaceContainer;
		initialize();
	}

	/**
	 * {@inheritDoc}
	 * @param userInterfaceContainer 
	 */
	@Override
	protected Skin<?> createDefaultSkin() {
		return new RfxTabPaneSkin3(this, userInterfaceContainer);
	}

	private void initialize() {
		this.getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}
	
	/**
	 * Initialize the style class to 'jfx-tab-pane'.
	 *
	 * This is the selector class from which CSS can be used to style
	 * this control.
	 */
	private static final String DEFAULT_STYLE_CLASS = "jfx-tab-pane";
	
	/**
	 * propagate any mouse events on the tab pane to its parent
	 */
	public void propagateMouseEventsToParent(){
		this.addEventHandler(MouseEvent.ANY, (e)->{
			e.consume();
			this.getParent().fireEvent(e);
		});
	}
}
 