package com.prototype.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationResponse
{
    private boolean isValid;

    public ValidationResponse(Boolean isValid) {
        this.isValid = isValid;
    }

    @JsonProperty(value = "isValid")
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}
