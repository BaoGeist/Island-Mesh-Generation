package islandADT.Generator;

import java.util.Optional;
import java.util.Random;

public class RandomSeed {

    static int randomseed;

    static Random randomseedrandom = new Random(randomseed);

    //TODO B better implementation of this
    protected static void set_randomseed(String seed) {
        if(seed != "") {
            randomseedrandom = new Random(randomseed);
            randomseed = Integer.parseInt(seed);
        } else {
            Random random = new Random();
            randomseed = random.nextInt();
            System.out.println(randomseed);
        }

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
