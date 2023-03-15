package islandADT.Generator;

import java.util.Random;

public class RandomSeed {

    static int randomseed;

    static Random randomseedrandom = new Random(randomseed);

    protected static void set_randomseed(int seed) {
        randomseedrandom = new Random(randomseed);
        randomseed = seed;
    }

    protected static void set_randomseed() {
        Random random = new Random();
        randomseed =  random.nextInt();
    }

    public static int randomInt() {
       return randomseedrandom.nextInt();
    }

    public static int randomInt(int max) {
        return randomseedrandom.nextInt(max);
    }

    public static int randomInt(int min, int max) {
        return randomseedrandom.nextInt(min, max);
    }

    public static int get_seed() {
        return randomseed;
    }


}
