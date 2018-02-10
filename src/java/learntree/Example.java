
package src.java.learntree;

/**
 * Created by blumenra on 10/02/18.
 */

import java.util.Arrays;

public class Example {

    private int label;
    private String[] pixles;

    public Example(String[] example){

        this.label = Integer.parseInt(example[0]);
        this.pixles = Arrays.copyOfRange(example, 1, example.length);
    }

    public int getLabel(){

        return this.label;
    }

    public int getPixle(int i, int j){

        int pixleIJ = Integer.parseInt(this.pixles[(i*28)+j]);
        return pixleIJ;
    }
}
