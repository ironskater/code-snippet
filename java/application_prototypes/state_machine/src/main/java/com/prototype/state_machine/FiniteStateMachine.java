package com.prototype.state_machine;

public interface FiniteStateMachine {

    FiniteStateMachine switchState(CharSequence input);
    boolean canStop();
}
