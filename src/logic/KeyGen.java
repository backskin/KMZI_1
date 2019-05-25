package logic;

import java.util.ArrayList;
import java.util.Random;

public class KeyGen {

    private ArrayList<ArrayList<Integer>> key;

    public static int alphabetPower = 256;
    private int gramaPower;


    public KeyGen(int gramma){

        this.gramaPower = gramma;
        key = new ArrayList<>();
    }

    public String getKeyAsString(){

        return getKeyAsString(key);
    }

    public static String getKeyAsString(ArrayList<ArrayList<Integer>> key){

        StringBuilder output = new StringBuilder();
        for (ArrayList<Integer> ints : key) {

            for (Integer anInt : ints) {
                output.append(" ").append(anInt.intValue());
            }

            output.append("\n");
        }
        return output.toString();
    }

    public static String getKeyAsStringChars(ArrayList<ArrayList<Integer>> key){

        StringBuilder output = new StringBuilder();
        for (ArrayList<Integer> ints : key) {

            for (Integer anInt : ints){
                output.append(" ").append( (char) anInt.intValue());
            }

            output.append("\n");
        }
        return output.toString();
    }


    public void generateKey(){

        key = Alg.getEMInt(gramaPower);

        Random random = new Random();
        for (int i = 0; i < 100; i++){

            int randRow = random.nextInt(key.size());
            int randMul = random.nextInt(alphabetPower);//-alphabetPower;
            randMul = randMul == 0 ? 1 : randMul;

            for (int j = 0; j < key.size(); j++) {
                if (j != randRow)
                    key.set(j, Alg.sum(key.get(j), Alg.mul(key.get(randRow), randMul, alphabetPower), alphabetPower));
            }
        }
    }

    static ArrayList<ArrayList<Integer>> antikey(ArrayList<ArrayList<Integer>> key, int mod){

        return Alg.invert(key, mod);
    }

    public ArrayList<ArrayList<Integer>> getKey() {
        return key;
    }
}
