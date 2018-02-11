
package src.java.learntree;

/**
 * Created by blumenra on 10/02/18.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        return Integer.parseInt(this.getPixels()[(i*28)+j]);
    }
}
