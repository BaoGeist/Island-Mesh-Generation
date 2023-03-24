package islandADT.Generator;

import islandADT.Specifications.IslandSpecifications;

import java.util.Optional;
import java.util.Random;

public class RandomSeed {

    private static int seed;

    private static Random randomFactory = null;

    //TODO B better implementation of this

    private static RandomSeed onlyInstance = null;

    private RandomSeed() {
            randomFactory = new Random(seed);
    }

    protected static void set_randomseed(IslandSpecifications islandSpecifications) {
        String seedString = islandSpecifications.getSeed();
        if(seedString == "") {
            seed = new Random().nextInt();
        } else {
            seed = Integer.parseInt(seedString);
        }
    }

    public static RandomSeed getInstance() {
        if(onlyInstance == null) {
            onlyInstance = new RandomSeed();
        }
        return onlyInstance;
    }

    public static int randomInt() {
       return randomFactory.nextInt();
    }

    public int randomInt(int max) {
        return randomFactory.nextInt(max);
    }

    public int randomInt(int min, int max) {
        return randomFactory.nextInt(min, max);
    }

    public double randomDouble() {return randomFactory.nextDouble(); }

    public double randomDouble(double min, double max) {return randomFactory.nextDouble(min, max); }
    public double randomDouble(double max) {return randomFactory.nextDouble(max); }

    public boolean randomBoolean() {return randomFactory.nextBoolean(); }

    public int get_seed() {
        return seed;
    }


}
