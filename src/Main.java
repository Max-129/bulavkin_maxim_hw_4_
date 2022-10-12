import java.util.Arrays;
import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {250, 270, 280, 260, 500};
    public static int[] heroesDamage = {25, 20, 15, 5, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        healingHeroes();
        golemDefense();
        printStatistics();

    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: " + (bossDefenceType == null ? "No defence" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + "; damage: "
                    + heroesDamage[i]);
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(10) + 2;
                    hit = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }

            }
        }
    }
    public static void healingHeroes(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 3) {
                continue;
            }
                if (heroesHealth[i] > 0 && heroesHealth[i] <= 100) {
                    heroesHealth[i] = heroesHealth[i] + 20;
                    System.out.println("Медик вылечел героев ");
                    break;

                }
            }
        }
            public static void golemDefense(){
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[4] > 0 && heroesHealth[i] > 0 && heroesHealth[4] != heroesHealth[i]) {
                        heroesHealth[i] = heroesHealth[i] + bossDamage / 5;
                        heroesHealth[4] -= bossDamage / 5;
                        System.out.println("Голем защитил героев");
                    }
                }
            }
        }




