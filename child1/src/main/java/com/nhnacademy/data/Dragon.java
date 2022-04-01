package com.nhnacademy.data;

public class Dragon {
    General general;

    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }

    public Dragon() {
        general = new General(3, 100, 10);
    }
}
