package com.prototype.service.rule;

public class TextRepeatValidator implements Validatable
{
    @Override
    public boolean validate(String password) {
        return !this.validateHelper(password, 1);
    }

    private boolean validateHelper(String password, int windowSize) {
        if(windowSize > password.length()/2) {
            return false;
        }

        for(int ix = windowSize * 2; ix <= password.length(); ix++) {
            String firstSubStr = password.substring(ix - windowSize * 2, ix - windowSize);
            String secondSubStr = password.substring(ix - windowSize, ix);

            if(firstSubStr.equals(secondSubStr)) {
                return true;
            }
        }

        return this.validateHelper(password, ++windowSize);
    }
}
