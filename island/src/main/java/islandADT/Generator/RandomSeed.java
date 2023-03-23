package islandADT.Generator;

import java.util.Optional;
import java.util.Random;

public class RandomSeed {

    static int randomseed;

    static Random randomFactory = new Random(randomseed);

    //TODO B better implementation of this
    protected static void set_randomseed(String seed) {
        if(seed != "") {
            randomseed = Integer.parseInt(seed);
            randomFactory = new Random(randomseed);
        } else {
            Random random = new Random();
            randomseed = random.nextInt();
            randomFactory = new Random(randomseed);
            System.out.println("Island seed is: " + randomseed);
        }

    }

    public static int randomInt() {
       return randomFactory.nextInt();
    }

    public static int randomInt(int max) {
        return randomFactory.nextInt(max);
    }

    public static int randomInt(int min, int max) {
        return randomFactory.nextInt(min, max);
    }

    public static double randomDouble() {return randomFactory.nextDouble(); }

    public static double randomDouble(double min, double max) {return randomFactory.nextDouble(min, max); }
    public static double randomDouble(double max) {return randomFactory.nextDouble(max); }

    public static boolean randomBoolean() {return randomFactory.nextBoolean(); }

    public static int get_seed() {
        return randomseed;
    }


}
