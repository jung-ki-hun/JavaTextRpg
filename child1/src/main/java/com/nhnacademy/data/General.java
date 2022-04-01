package com.nhnacademy.data;

public class General {
    private int level;
    private int hp;
    private int attack;

    public General(int level, int hp, int attack) {
        this.level = level;
        this.hp = hp;
        this.attack = attack;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }


}
