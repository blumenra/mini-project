
package src.java.LT;

/**
 * Created by blumenra on 10/02/18.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Example {

    private String cvsSplitBy = ",";
    private String csvFileName;
    private int label;
    private int index;
    private int chunkSize;
    private String[] pixels;
    private int pixelIndex;


    public Example(int index, int label, String csvFileName){

        this.csvFileName = csvFileName;
        this.label = label;
        this.index = index;
    }

    public Example(int index, String csvFileName, String example, int pixelsChunkSize) {

        String[] exampleArr = this.InitializePixels(example, 1, pixelsChunkSize+1);

        this.csvFileName = csvFileName;
        this.label = Integer.parseInt(exampleArr[0]);
        this.index = index;
        this.chunkSize = pixelsChunkSize;
        this.pixelIndex = 0;
    }

    public String[] InitializePixels(String example, int from, int to){

        String[] exampleArr = example.split(this.cvsSplitBy);
        this.pixels = Arrays.copyOfRange(exampleArr, from, to);

        return exampleArr;
    }

    public int getLabel(){

        return this.label;
    }

    public int getIndex(){
        return this.index;
    }

    /*
    returns the pixels value on the @i-th row, @j-th column
     */
    public int getPixel(int i, int j){

        int pixelIndexInArray = (i*28)+j;
        if(pixelIndexInArray >= 784 || pixelIndexInArray < 0){

            try {
                throw new Exception("Invalid pixel: Cannot access pixel [" + i +", " + j +"]");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(pixelIndexInArray > (this.pixelIndex + this.chunkSize - 1)){

            Main.trainingSet.loadPixelsFrom(i, j);
        }

        return Integer.parseInt(this.pixels[this.pixelIndex % this.chunkSize]);
    }

    private void readPixelsFrom(int i, int j){

//        int pixelIndexInArray = (i*28)+j+1;
        //        String cvsSplitBy = ",";
        String example = "";


//        try (Stream<String> lines = Files.lines(Paths.get(this.csvFileName))) {
//            Main.trainingSet.loadPixelsFrom(int i, int j);

//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        this.pixelIndex = pixelIndexInArray;
//        int indexOfLastPixel = example.length() - 2;
//
//        int indexOfLast = this.pixelIndex + this.chunkSize;
//        if(indexOfLast > indexOfLastPixel){
//
//            this.InitializePixels(example, this.pixelIndex, indexOfLastPixel+1);
//        }
//        else{
//            this.InitializePixels(example, this.pixelIndex, indexOfLast);
//        }


//        pixels.split(cvsSplitBy);
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

    public void setPixelIndex(int pixelIndex) {
        this.pixelIndex = pixelIndex;
    }

    public int getPixelIndex() {
        return pixelIndex;
    }

    public int getChunkSize() {
        return chunkSize;
    }
}
