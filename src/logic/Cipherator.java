package logic;

import java.util.ArrayList;

public class Cipherator {

    private static ArrayList<ArrayList<Long>> getVectorsFromText(String text, int keySize){

        ArrayList<ArrayList<Long>> out = new ArrayList<>();

        for (int i = 0; i < Math.ceil((double) text.length() / keySize); i++) {

            out.add(new ArrayList<>());

            int r = text.length() > (i+1) * keySize ? keySize : text.length() - i * keySize;

            for (int j = 0; j < keySize; j++) {

                if (r > j)

                    out.get(i).add((long) text.charAt(i*keySize + j));
                else
                    out.get(i).add((long) ' ');
            }
        }
        return out;
    }

    private static String getTextFromVectors(ArrayList<ArrayList<Long>> vectors){

        StringBuilder output = new StringBuilder();

        for (ArrayList<Long> vector : vectors) {

            for (Long aLong : vector) {

                output.append((char) aLong.intValue());
            }
        }

        return output.toString();
    }

    public static String cip(String src, ArrayList<ArrayList<Long>> key){

        ArrayList<ArrayList<Long>> vectors = getVectorsFromText(src,key.size());

        vectors = Alg.mul(vectors,key,KeyGen.alphabet);

        return getTextFromVectors(vectors);
    }

    public static String desip(String cipher, ArrayList<ArrayList<Long>> key){

        ArrayList<ArrayList<Long>> vectors = getVectorsFromText(cipher, key.size());

        vectors = Alg.mul(vectors,KeyGen.antikey(key),KeyGen.alphabet);

        return getTextFromVectors(vectors);
    }
}
