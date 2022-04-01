package com.nhnacademy.server;

import com.nhnacademy.data.*;
import java.security.SecureRandom;

public class Gameplay {
    public final User user;
    public final Slime slime;
    public final Oak oak;
    public final Dragon dragon;
    private final SecureRandom sr = new SecureRandom();

    Gameplay(String id) {
        user = new User(id);
        slime = new Slime();
        oak = new Oak();
        dragon = new Dragon();
    }

    public String fight(int monster) throws InterruptedException {
        int userHp = user.getGeneral().getHp();
        int monsterHp = slime.getGeneral().getHp();
        if (monster == 1) {
            while (true) {
                monsterHp -= sr.nextInt(user.getGeneral().getAttack()) + 1;
                userHp -= sr.nextInt(slime.getGeneral().getAttack()) + 1;
                System.out.println(monsterHp + " " + userHp);
                Thread.sleep(500);
                if (monsterHp < 0) {
                    user.getGeneral().setHp(userHp);
                    String strSend = "슬라임을 물리쳤다.\n야생의 오크가 나타났다.\n1. 공격\n2. 도망간다. (게임 종료)";
                    System.out.println(strSend);
                    return strSend;
                }
            }
        }
        return null;
    }

    public String fight2(int monster) throws InterruptedException {

        int userHp = user.getGeneral().getHp();
        if (monster == 1) {
            int monsterHp = oak.getGeneral().getHp();
            while (true) {
                monsterHp -= sr.nextInt(user.getGeneral().getAttack()) + 1;
                userHp -= sr.nextInt(oak.getGeneral().getAttack()) + 1;
                System.out.println(monsterHp + " " + userHp);
                Thread.sleep(500);
                if (monsterHp < 0) {
                    user.getGeneral().setHp(150);
                    user.getGeneral().setLevel(2);
                    user.getGeneral().setAttack(20);
                    String strSend = "오크를 물리쳤다.\n레벨" + user.getGeneral().getLevel()
                        + "로 상승했다.\n*보스* 드래곤이 나타났다.\n1. 공격\n2. 도망간다. (게임 종료)";
                    System.out.println(strSend);
                    return strSend;
                }
            }
        }
        return null;
    }

    public String fight3(int monster) throws InterruptedException {
        int userHp = user.getGeneral().getHp();
        int bless = 15;
        if (monster == 1) {
            int monsterHp = dragon.getGeneral().getHp();
            while (true) {

                if (sr.nextInt(10) == 1) {
                    userHp -= bless;
                } else {
                    monsterHp -= sr.nextInt(user.getGeneral().getAttack()) + 1;
                }
                userHp -= sr.nextInt(dragon.getGeneral().getAttack()) + 1;
                System.out.println(monsterHp + " " + userHp);
                Thread.sleep(500);
                if (monsterHp < 0) {
                    String strSend = "[외침] 용사 " + this.user.getId() + "가 드래곤을 물리쳤다!.\n";
                    System.out.println(strSend);
                    return strSend;
                }
                if (userHp < 0) {
                    String strSend = "user lose and monster remaining hp : " + monsterHp;
                    System.out.println(strSend);
                    return strSend;
                }
            }
        }
        return null;
    }
}
