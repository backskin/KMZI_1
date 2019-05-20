package logic;

import java.util.ArrayList;

public class Cipherator {

    public static ArrayList<ArrayList<Integer>> getVectorsFromText(String text, int keySize){
        
        ArrayList<ArrayList<Integer>> vectors = new ArrayList<ArrayList<Integer>>(){{

            for (int i = 0; i < text.length() / keySize + (text.length() % keySize > 0 ? 1 : 0); i++) {

                add(new ArrayList<>());
                int r = text.length() > (i+1)*keySize? (i+1)*keySize : text.length()-1;
                CharSequence cs = text.subSequence(i*keySize, r);
                for (int j = 0; j < keySize; j++) {

                    if (text.length() > i*keySize + j)
                        get(i).add((int)cs.charAt(j));
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


        return null;
    }
}
