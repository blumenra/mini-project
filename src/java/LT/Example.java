
package src.java.LT;

/**
 * Created by blumenra on 10/02/18.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Example {

    private String csvFileName;
    private int label;
    private int index;

    public Example(int index, int label, String csvFileName){

        this.csvFileName = csvFileName;
        this.label = label;
        this.index = index;
    }

    public int getLabel(){

        return this.label;
    }

    public int getIndex(){
        return this.index;
    }

    public String[] getPixels(){

        String cvsSplitBy = ",";
        String pixels = "";
        try (Stream<String> lines = Files.lines(Paths.get(this.csvFileName))) {
            pixels =  lines.skip(this.index-1).findFirst().get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pixels.split(cvsSplitBy);
    }

    /*
    returns the pixels value on the @i-th row, @j-th column
     */
    public int getPixel(int i, int j){

        return Integer.parseInt(this.getPixels()[(i*28)+j+1]);
    }

    /*
    Returns an array of the number of appearance of each example in @n_set
     */
    private static int[] get_digits_info(List<Example> n_set) {

        int[] counter = new int[10];

        for(Example example : n_set){

            counter[example.getLabel()]++;
        }

        return counter;
    }


    /*
    Returns the label that
     */
    public static int most_appeared_digit(List<Example> n_set) {

        int[] counter = get_digits_info(n_set);
        int most_appeared_digit = 0;

        int max = counter[0];
        for(int j=0; j < counter.length; j++){
            if (counter[j] > max) {
                most_appeared_digit = j;
            }
        }

        return most_appeared_digit;
    }
}
