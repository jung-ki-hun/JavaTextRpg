package com.nhnacademy.data;

public class Oak {
    private General general;

    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }

    public Oak() {
        general = new General(2, 40, 6);
    }
}
