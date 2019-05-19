package logic;

import java.util.ArrayList;

public class Cipherator {
    
    public static String applicate(String text, ArrayList<ArrayList<Integer>> key){
        
        ArrayList<ArrayList<Integer>> intText = new ArrayList<ArrayList<Integer>>(){{

            for (int i = 0; i < key.size(); i++) {

                add(new ArrayList<>());

                for (int j = 0; j < text.length() / key.size() + (text.length() % key.size() > 0 ? 1 : 0); j++) {



                }
                
            }
            
        }};

        return null;
        
    }
}
