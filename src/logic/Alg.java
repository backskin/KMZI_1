package logic;
import java.text.MessageFormat;
import java.util.ArrayList;

public class Alg {

    private static Integer mod(int number, int mod){

        while (number < 0) number += mod;
        return number % mod;
    }

    private static Integer inv(int a, int mod){

        for (int i = 0; i < mod; i++)
            if ((a*i) % mod == 1)
                    return i;

        return null;
    }

    static ArrayList<Integer> sum(ArrayList<Integer> a, ArrayList<Integer> b, int mod) throws IllegalArgumentException{

        if (a.size() != b.size()) {
            throw new IllegalArgumentException (
                    MessageFormat.format (
                            "Matrices don't match: {0} != {1}.",
                            a.size(), b.size()));
        }

        return new ArrayList<Integer>(){{
            for (int i = 0; i < a.size(); i++) add((a.get(i) + b.get(i)) % mod);
        }};
    }

    static ArrayList<Integer> sum(ArrayList<Integer> a, ArrayList<Integer> b) throws IllegalArgumentException{

        if (a.size() != b.size()) {
            throw new IllegalArgumentException (
                    MessageFormat.format (
                            "Matrices don't match: {0} != {1}.",
                            a.size(), b.size()));
        }

        return new ArrayList<Integer>(){{
            for (int i = 0; i < a.size(); i++) add(a.get(i) + b.get(i));
        }};
    }

    private static ArrayList<Integer> mul(ArrayList<Integer> a, Integer b){

        return new ArrayList<Integer>(){{
            for (int i = 0; i < a.size(); i++)
                add(i, a.get(i) * b);
        }};
    }

    static ArrayList<Integer> mul(ArrayList<Integer> a, Integer b, int mod){

        return new ArrayList<Integer>(){{
            for (int i = 0; i < a.size(); i++)
                add(i, mod((a.get(i) * b), mod));
        }};
    }

    private static ArrayList<Double> mul(ArrayList<Double> a, Double b){

        return new ArrayList<Double>(){{
            for (int i = 0; i < a.size(); i++)
                add(i, (a.get(i) * b));
        }};
    }

    static ArrayList<ArrayList<Integer>> mulM(ArrayList<ArrayList<Integer>> a, Integer b, int mod){

        return new ArrayList<ArrayList<Integer>>(){{
            for (ArrayList<Integer> integers : a) add(mul(integers, b, mod));
        }};
    }

    static ArrayList<ArrayList<Integer>> mulM(ArrayList<ArrayList<Integer>> a, Integer b){

        return new ArrayList<ArrayList<Integer>>(){{
            for (ArrayList<Integer> integers : a) add(mul(integers, b));
        }};
    }

    private static ArrayList<ArrayList<Double>> mulM(ArrayList<ArrayList<Double>> a, Double b){

        return new ArrayList<ArrayList<Double>>(){{
            for (ArrayList<Double> doubs : a) add(mul(doubs,b));
        }};
    }

    public static ArrayList<ArrayList<Integer>> mul (ArrayList<ArrayList<Integer>> x,
                                                     ArrayList<ArrayList<Integer>> y,
                                                     Integer mod)
            throws IllegalArgumentException
    {

        if (x.get(0).size() != y.size()) {
            throw new IllegalArgumentException (
                    MessageFormat.format (
                            "Matrices don't match: {0} != {1}.",
                            x.get(0).size(), y.size()));
        }

        return new ArrayList<ArrayList<Integer>>(){{

            for (int i = 0; i < x.size(); i++){

                add(new ArrayList<>());
                for (int j = 0; j < y.get(i).size(); j++){

                    get(i).add(0);

                    for (int k = 0; k < x.get(i).size(); k ++)
                        get(i).set(j,
                                mod(get(i).get(j)
                                        + x.get(i).get(k) * y.get(k).get(j), mod));
                }
            }
        }};
    }

    static ArrayList<ArrayList<Double>> Int2Double(ArrayList<ArrayList<Integer>> a){

        return new ArrayList<ArrayList<Double>>(){{
            for (int i = 0; i < a.size(); i++) {
                add(new ArrayList<>());
                for (int j = 0; j < a.get(i).size(); j++)
                    get(i).add(a.get(i).get(j).doubleValue());
            }
        }};
    }

    static ArrayList<ArrayList<Integer>> getEMInt(int size){

        return new ArrayList<ArrayList<Integer>>(){{

            for (int i = 0; i < size; i++){
                add(new ArrayList<>());
                for (int j = 0; j < size; j++) get(i).add(j == i ? 1 : 0);
            }
        }};
    }

    private static ArrayList<ArrayList<Double>> getOMDouble(int size) {

        return new ArrayList<ArrayList<Double>>() {{

            for (int i = 0; i < size; i++) {
                add(new ArrayList<>());
                for (int j = 0; j < size; j++) get(i).add(.0);
            }
        }};
    }

    private static ArrayList<ArrayList<Integer>> getOMInt(int size){

        return new ArrayList<ArrayList<Integer>>() {{

            for (int i = 0; i < size; i++) {
                add(new ArrayList<>());
                for (int j = 0; j < size; j++) get(i).add(0);
            }
        }};
    }

    public static ArrayList<ArrayList<Integer>> getTrans(ArrayList<ArrayList<Integer>> m, int mod){

        return new
                ArrayList<ArrayList<Integer>>(){{

                   for (int i = 0; i < m.get(0).size(); i++) {

                       add(new ArrayList<>());
                       for (ArrayList<Integer> integers : m) {

                           get(i).add(mod(integers.get(i), mod));
                       }
                   }
                }};
    }

    public static ArrayList<ArrayList<Double>> getTrans(ArrayList<ArrayList<Double>> m){

        return new
                ArrayList<ArrayList<Double>>(){{

                    for (int i = 0; i < m.get(i).size(); i++) {

                        add(new ArrayList<>());
                        for (ArrayList<Double> doubles : m) {

                            get(i).add(doubles.get(i));
                        }
                    }
                }};
    }

    public static ArrayList<ArrayList<Integer>> getMinorMatrix(ArrayList<ArrayList<Integer>> m, int i, int j, int mod){

        return new ArrayList<ArrayList<Integer>>(){{
            for (int i1 = 0; i1 < m.size(); i1++){
                if (i1 != i) {
                    add(new ArrayList<>());

                    for (int j1 = 0; j1 < m.size(); j1++)
                        if (j1 != j) get(i1 < i ? i1 : i1 - 1).add(mod(m.get(i1).get(j1), mod));
                }
            }
        }};
    }

    public static ArrayList<ArrayList<Double>> getMinorMatrix(ArrayList<ArrayList<Double>> m, int i, int j){

        return new ArrayList<ArrayList<Double>>(){{
            for (int i1 = 0; i1 < m.size(); i1++){
                if (i1 != i) add(new ArrayList<>());
                for (int j1 = 0; j1 < m.size(); j1++)
                    if (j1 != j) get(i1).add(m.get(i1).get(j1));
            }
        }};
    }

    public static Integer determinant(ArrayList<ArrayList<Integer>> m, int mod){

        ArrayList<ArrayList<Integer>> temporary;

        if (m.size() == 1)
            return m.get(0).get(0);
        if (m.size() == 2)
            return m.get(0).get(0) * m.get(1).get(1)
                - m.get(0).get(1) * m.get(1).get(0);

        int result = 0;

        for (int i = 0; i < m.size(); i++){

            temporary = getMinorMatrix(m,0,i,mod);/*getOMInt(m.size()-1);

            for (int j = 1; j < m.size(); j++) {
                for (int k = 0; k < m.get(0).size(); k++)
                    if (k < i)
                        temporary.get(j-1).set(k, m.get(j).get(k));
                    else if (k > i)
                        temporary.get(j-1).set(k-1, m.get(j).get(k));

            }*/


            result += m.get(0).get(i) * Math.pow (-1, (double) i) * determinant(temporary, mod);
        }
        return mod(result, mod);
    }

    public static Double determinant(ArrayList<ArrayList<Double>> m) {
        ArrayList<ArrayList<Double>> temporary;

        if (m.size() == 1) return m.get(0).get(0);

        if (m.size() == 2) return m.get(0).get(0) * m.get(1).get(1)
                    - m.get(0).get(1) * m.get(1).get(0);

        double result = 0;

        for (int i = 0; i < m.size(); i++){

            temporary = getOMDouble(m.size()-1);

            for (int j = 1; j < m.size(); j++) {


                for (int k = 0; k < m.get(0).size(); k++)
                    if (k < i)
                        temporary.get(j-1).set(k, m.get(j).get(k));
                    else if (k > i)
                        temporary.get(j-1).set(k-1, m.get(j).get(k));
            }

            result += m.get(0).get(i) * Math.pow (-1, (double) i) * determinant(temporary);
        }
        return result;
    }

    public static Integer minor(ArrayList<ArrayList<Integer>> m, int i, int j, int mod){

        ArrayList<ArrayList<Integer>> minorMatrix = getMinorMatrix(m, i, j, mod);

        return determinant(minorMatrix, mod);
    }


    public static Double minor(ArrayList<ArrayList<Double>> m, int i, int j){

        return determinant(getMinorMatrix(m, i, j));
    }


    private static ArrayList<ArrayList<Integer>> algAddMatrix(ArrayList<ArrayList<Integer>> m, int mod){

        return new ArrayList<ArrayList<Integer>>(){{
            for(int i = 0; i < m.size(); i++){
                add(new ArrayList<>());
                for (int j = 0; j < m.get(i).size(); j++) {
                    int minor = minor(m,i,j,mod);
                    get(i).add(mod(((int) Math.pow(-1, i + j)) * minor, mod));
                }
            }
        }};
    }

    private static ArrayList<ArrayList<Double>> algAddMatrix(ArrayList<ArrayList<Double>> m){

        return new ArrayList<ArrayList<Double>>(){{

            for(int i = 0; i < m.size(); i++){
                add(new ArrayList<>());
                for (int j = 0; j < m.get(i).size(); j++) {
                    double minor = minor(m,i,j);
                    get(i).add(Math.pow(-1, i + j) * minor);
                }
            }
        }};
    }

    public static ArrayList<ArrayList<Integer>> invert(ArrayList<ArrayList<Integer>> m, int mod){

        ArrayList<ArrayList<Integer>> algAdd = algAddMatrix(m, mod);
        int det = determinant(m, mod);

        return mulM(getTrans(algAdd, mod), inv(det, mod), mod);
    }

    public static ArrayList<ArrayList<Double>> invert(ArrayList<ArrayList<Double>> m){

        return mulM(getTrans(algAddMatrix(m)), 1.0/determinant(m));
    }

    public static ArrayList<ArrayList<Double>> invertMatrix (ArrayList<ArrayList<Double>> m) {

        ArrayList<ArrayList<Double>> auxiliaryMatrix = getOMDouble(m.size());
        ArrayList<ArrayList<Double>>invertedMatrix = getOMDouble(m.size());
        ArrayList<Integer> index = new ArrayList<>(m.size());

        for (int i = 0; i < m.size(); ++i) {
            auxiliaryMatrix.get(i).set(i, 1.0);
        }

        transformToUpperTriangle (m, index);

        for (int i = 0; i < m.size() - 1; ++i)
            for (int j = (i + 1); j < m.size(); ++j)
                for (int k = 0; k < m.size(); ++k)

                    auxiliaryMatrix.get(index.get(j)).set(k,
                            auxiliaryMatrix.get(index.get(j)).get(k)
                                    - m.get(index.get(j)).get(i)
                                    * auxiliaryMatrix.get(index.get(i)).get(k));


        for (int i = 0; i < m.size(); ++i) {
            invertedMatrix.get(m.size() - 1).set(i,
                    auxiliaryMatrix.get(
                            index.get(m.size()-1)).get(i)
                            / m.get(index.get(m.size()-1)).get(m.size()-1));

            for (int j = (m.size() - 2); j >= 0; --j) {
                invertedMatrix.get(j).set(i, auxiliaryMatrix.get(index.get(j)).get(i));

                for (int k = (j + 1); k < m.size(); ++k) {
                    invertedMatrix.get(j).set(i, invertedMatrix.get(j).get(i)
                            - m.get(index.get(j)).get(k) * invertedMatrix.get(k).get(i));
                }

                invertedMatrix.get(j).set(i,
                        invertedMatrix.get(j).get(i)
                                / m.get(index.get(j)).get(j));
            }
        }

        return (invertedMatrix);
    }

    public static void transformToUpperTriangle (ArrayList<ArrayList<Double>> m, ArrayList<Integer> index) {
        ArrayList<Double> c;
        Double c0, c1, pi0, pi1, pj;
        int itmp, k;

        c = new ArrayList<>(m.size());

        for (int i = 0; i < m.size(); ++i) index.set(i, i);

        for (int i = 0; i < m.size(); ++i) {
            c1 = .0;

            for (int j = 0; j < m.size(); ++j) {
                c0 = Math.abs (m.get(i).get(j));
                if (c0 > c1) {
                    c1 = c0;
                }
            }
            c.set(i, c1);
        }
        k = 0;

        for (int j = 0; j < m.size()-1; ++j) {
            pi1 = .0;

            for (int i = j; i < m.size(); ++i) {
                pi0 = Math.abs (m.get(index.get(i)).get(j)) / c.get(index.get(i));

                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }
            itmp = index.get(j);
            index.set(j,index.get(k));
            index.set(k, itmp);

            for (int i = (j + 1); i < m.size(); ++i) {

                pj = m.get(index.get(i)).get(j) / m.get(index.get(j)).get(j);
                m.get(index.get(i)).set(j,
                        pj);

                for (int l = (j + 1); l < m.size(); ++l) {
                    m.get(index.get(i)).set(l, m.get(index.get(i)).get(l)- pj *  m.get(index.get(j)).get(l));
                }
            }
        }
    }
}

