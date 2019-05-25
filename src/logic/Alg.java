package logic;
import java.text.MessageFormat;
import java.util.ArrayList;

public class Alg {

    private static Long mod(long number, int mod){

        while (number < 0) number += mod;
        return number % mod;
    }

    private static Long inv(long a, int mod){

        for (long i = 0; i < mod; i++)
            if ((a*i) % mod == 1)
                    return i;

        return null;
    }

    static ArrayList<Long> sum(ArrayList<Long> a, ArrayList<Long> b, int mod) throws IllegalArgumentException{

        if (a.size() != b.size()) {
            throw new IllegalArgumentException (
                    MessageFormat.format (
                            "Matrices don't match: {0} != {1}.",
                            a.size(), b.size()));
        }

        return new ArrayList<Long>(){{
            for (int i = 0; i < a.size(); i++) add((a.get(i) + b.get(i)) % mod);
        }};
    }

    static ArrayList<Long> mul(ArrayList<Long> a, Long b, int mod){

        return new ArrayList<Long>(){{
            for (int i = 0; i < a.size(); i++)
                add(i, mod((a.get(i) * b), mod));
        }};
    }

    private static ArrayList<ArrayList<Long>> mulM(ArrayList<ArrayList<Long>> a, Long b, int mod){

        return new ArrayList<ArrayList<Long>>(){{
            for (ArrayList<Long> Longs : a) add(mul(Longs, b, mod));
        }};
    }

    private static ArrayList<Long> mulVectorMatrix(ArrayList<Long> x,
                                                   ArrayList<ArrayList<Long>> y,
                                                   int mod)
            throws IllegalArgumentException
    {

        if (x.size() != y.size()) {

            throw new IllegalArgumentException (
                    "Matrices don't match!");
        }

        ArrayList<Long> out = new ArrayList<>();

        for (int i = 0; i < y.size(); i++) {

            int res = 0;

            for (int j = 0; j < x.size(); j++) {

                res += x.get(j)*y.get(j).get(i);
            }

            out.add(mod(res, mod));
        }

        return out;
    }

    static ArrayList<ArrayList<Long>> mul(ArrayList<ArrayList<Long>> x,
                                          ArrayList<ArrayList<Long>> y,
                                          int mod)
            throws IllegalArgumentException
    {

        ArrayList<ArrayList<Long>> out = new ArrayList<>();

        for (ArrayList<Long> Longs : x) {

            out.add(mulVectorMatrix(Longs, y, mod));
        }

        return out;
    }

    static ArrayList<ArrayList<Long>> getEMInt(int size){

        return new ArrayList<ArrayList<Long>>(){{

            for (int i = 0; i < size; i++){

                add(new ArrayList<>());

                for (int j = 0; j < size; j++) {

                    get(i).add(j == i ? (long)1 : (long)0);
                }
            }
        }};
    }

    private static ArrayList<ArrayList<Long>> getTrans(ArrayList<ArrayList<Long>> m, int mod){

        return new
                ArrayList<ArrayList<Long>>(){{

                   for (int i = 0; i < m.get(0).size(); i++) {

                       add(new ArrayList<>());

                       for (ArrayList<Long> Longs : m) {

                           get(i).add(mod(Longs.get(i), mod));
                       }
                   }
                }};
    }

    private static ArrayList<ArrayList<Long>> getMinorMatrix(ArrayList<ArrayList<Long>> m, int i, int j){

        return new ArrayList<ArrayList<Long>>(){{

            for (int i1 = 0; i1 < m.size(); i1++){

                if (i1 != i) {

                    add(new ArrayList<>());

                    for (int j1 = 0; j1 < m.size(); j1++){

                        if (j1 != j) {

                            get(i1 < i ? i1 : i1 - 1)
                                    .add(m.get(i1).get(j1));
                        }
                    }
                }
            }
        }};
    }

    public static Long determinant(ArrayList<ArrayList<Long>> m, int mod){

        ArrayList<ArrayList<Long>> temporary;

        if (m.size() == 1)
            return mod(m.get(0).get(0), mod);

        if (m.size() == 2)
            return mod(m.get(0).get(0) * m.get(1).get(1) - m.get(0).get(1) * m.get(1).get(0), mod);

        long result = 0;

        for (int i = 0; i < m.size(); i++){

            temporary = getMinorMatrix(m,0,i);
            
            result += m.get(0).get(i) * Math.pow (-1, i) * determinant(temporary, mod);
        }
        return mod(result, mod);
    }

    private static Long minor(ArrayList<ArrayList<Long>> m, int i, int j, int mod){

        ArrayList<ArrayList<Long>> minorMatrix = getMinorMatrix(m, i, j);

        return determinant(minorMatrix, mod);
    }


    private static ArrayList<ArrayList<Long>> algAddMatrix(ArrayList<ArrayList<Long>> m, int mod){

        return new ArrayList<ArrayList<Long>>(){{

            for(int i = 0; i < m.size(); i++){

                add(new ArrayList<>());

                for (int j = 0; j < m.get(i).size(); j++) {

                    long minor = minor(m, i, j, mod);

                    get(i).add(mod(((int) Math.pow(-1, i + j)) * minor, mod));
                }
            }
        }};
    }

    static ArrayList<ArrayList<Long>> invert(ArrayList<ArrayList<Long>> m, int mod){

        ArrayList<ArrayList<Long>> algAddittio = algAddMatrix(m, mod);

        long det = determinant(m, mod);

        return mulM(getTrans(algAddittio, mod), inv(det,mod),mod);
    }
}

