package com.github.dstewart.toastman.util;

import com.github.dstewart.toastman.gui.RequestModel;
import org.apache.commons.validator.routines.UrlValidator;

public class RequestValidator {
    private final RequestModel model;
    private final UrlValidator urlValidator;

    public RequestValidator(RequestModel model) {
        this.model = model;
        this.urlValidator = new UrlValidator();
    }

    public boolean validate() {
        return urlValidator.isValid(model.getUriAddress());
    }
}
