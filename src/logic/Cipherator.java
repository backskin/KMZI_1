package logic;

import java.util.ArrayList;

import static logic.Alg.mul;

public class Cipherator {



    private static ArrayList<ArrayList<Integer>> getVectorsFromText(String text, int keySize){
        
        ArrayList<ArrayList<Integer>> vectors = new ArrayList<ArrayList<Integer>>(){{

            for (int i = 0; i < Math.round((double) text.length() / keySize); i++) {

                add(new ArrayList<>());

                int r = text.length() > (i+1) * keySize ? (i+1)*keySize : text.length()-1;

                char[] chars = new char[keySize];

                text.getChars(i*keySize, r, chars, 0);
                for (int j = 0; j < keySize; j++) {

                    if (chars.length - 1 > j )
                        get(i).add((int)chars[j]);
                    else
                        get(i).add((int)' ');
                }
            }
        }};

        return vectors;
    }

    public static String getTextFromVectors(ArrayList<ArrayList<Integer>> vectors){

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < vectors.size(); i++) {

            for (int j = 0; j < vectors.get(i).size(); j++) {
                output.append((char)vectors.get(i).get(j).intValue());
            }
        }

        return output.toString();
    }

    public static String cip(String source, ArrayList<ArrayList<Integer>> key){

        ArrayList<ArrayList<Integer>> vectors = getVectorsFromText(source,KeyGen.alphabetPower);

        System.out.println(KeyGen.getKeyAsStringChars(vectors));

        return null;
    }

    public static String desip(String cip, ArrayList<ArrayList<Integer>> key){

        return getTextFromVectors(Alg.getTrans(mul(getVectorsFromText(cip, key.size()), KeyGen.antikey(key, KeyGen.alphabetPower), KeyGen.alphabetPower),KeyGen.alphabetPower));
    }
}
