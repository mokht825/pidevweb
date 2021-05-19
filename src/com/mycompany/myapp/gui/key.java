/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;
import com.codename1.io.Log;
import static com.codename1.ui.CN.getCurrentForm;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author belha
 */
public class key {
    
private Form current;
private Resources theme;
private Form home, allEvent, specEvent, picEvent;


public void init(Object context) {
    theme = UIManager.initFirstTheme("/theme");

    // Enable Toolbar on all Forms by default
    Toolbar.setGlobalToolbar(true);

    // Pro only feature
    Log.bindCrashProtection(true);
}

public void start() {
    if (current != null) {
        current.show();
        return;
  }

        home = new Form("Home", BoxLayout.y());
        home.setScrollableY(true);

        TextField txt = new TextField();
        txt.setFocusable(true);
        txt.setConstraint(TextField.NUMERIC);
        txt.startEditingAsync();

        home.addComponent(txt); 
        home.show();
 }


public void stop() {
    current = getCurrentForm();
}

public void destroy() {
}

    
}
