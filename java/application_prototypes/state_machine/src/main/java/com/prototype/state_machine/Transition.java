package com.prototype.state_machine;

public interface Transition {

    boolean isPossible(CharSequence input);

    State nextState();
}
