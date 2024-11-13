package com.prototype.state_machine;

public interface State {

    /**
     * Add a transition to the current state
     * @param tr
     * @return the current state
     */
    State with(Transition tr);

    State transit(CharSequence input);

    boolean isFinal();
}
