package com.prototype.state_machine;

public interface State {

    State with(Transition tr);

    State transit(CharSequence input);

    boolean isFinal();
}
