package com.nhnacademy.data;

public class User {
    General general;

    String id;

    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String id) {
        general = new General(1, 100, 10);
        setId(id);
    }
}
