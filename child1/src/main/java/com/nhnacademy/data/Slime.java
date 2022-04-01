package com.nhnacademy.data;

public class Slime {
    private General general;

    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }

    public Slime() {
        general = new General(1, 30, 4);
    }
}
