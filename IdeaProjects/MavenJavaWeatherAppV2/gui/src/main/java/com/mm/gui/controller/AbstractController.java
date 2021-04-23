package com.mm.gui.controller;

import com.mm.gui.view.ViewFactory;

public abstract class AbstractController {

    protected ViewFactory viewFactory;
    private String fxmlName;

    public AbstractController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
