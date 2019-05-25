package logic;

import java.util.ArrayList;
import java.util.Random;

public class KeyGen {

    private ArrayList<ArrayList<Long>> key;

    public static int alphabet = 65536;
    private int grama;


    public KeyGen(int gramma){

        this.grama = gramma;
        key = new ArrayList<>();
    }

    public String getKeyAsString(){

        return getKeyAsString(key);
    }

    public static String getKeyAsString(ArrayList<ArrayList<Long>> key){

        StringBuilder output = new StringBuilder();
        for (ArrayList<Long> ints : key) {

            for (Long aLong : ints) {
                output.append(" ").append(aLong.intValue());
            }

            output.append("\n");
        }
        return output.toString();
    }

    static String getKeyAsStringChars(ArrayList<ArrayList<Long>> key){

        StringBuilder output = new StringBuilder();
        for (ArrayList<Long> ints : key) {

            for (Long aLong : ints){
                output.append(" ").append( (char) aLong.intValue());
            }

            output.append("\n");
        }
        return output.toString();
    }


    public void generateKey(){

        key = Alg.getEMInt(grama);

        Random random = new Random();

        for (int i = 0; i < 100; i++){

            int randRow = random.nextInt(key.size());

            long randMul = random.nextInt(alphabet);

            randMul = randMul == 0 ? 1 : randMul;

            for (int j = 0; j < key.size(); j++) {

                if (j != randRow){

                    ArrayList<Long> longs = Alg.mul(key.get(randRow), randMul, alphabet);

                    key.set(j, Alg.sum(key.get(j), longs, alphabet));
                }
            }
        }
    }

    static ArrayList<ArrayList<Long>> antikey(ArrayList<ArrayList<Long>> key){

        return Alg.invert(key, alphabet);
    }

    public ArrayList<ArrayList<Long>> getKey() {
        return key;
    }
}
