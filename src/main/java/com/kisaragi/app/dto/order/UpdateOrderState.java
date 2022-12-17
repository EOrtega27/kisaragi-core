package com.kisaragi.app.dto.order;

import com.kisaragi.app.order.State;

public class UpdateOrderState {
    private String tracking;
    private State state;

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
