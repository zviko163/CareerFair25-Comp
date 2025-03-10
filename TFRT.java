/**
 * Ashesi University Programming Challenge Career Fair 2025
 * Triple File Reversal Transformation ( TFRT)
 *
 * The program reads a text file, reverses the lines, words and
 * characters in the file and writes the result to a new file.
 * It takes two command line arguments, the input file and the output file names
 * The program uses three stacks to store the extracted lines, words and characters
 * Stacks because of their LIFO (Last In First Out) nature, useful in reversing
 *
 * @author SHAMMAH Z. DZWAIRO
 * Date: 10th March, 2025
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class TFRT {

    // stack to store the extracted lines from a text file
    static Stack<String> lines = new Stack<String>();

    // stack to store the extracted words from a line
    static Stack<String> words = new Stack<String>();

    // stack to store the extracted characters from a word
    static Stack<Character> characters =  new Stack<Character>();

    // file writer
    static FileWriter myWriter;

    public static void main(String[] args) {
        // arraylist to store the reversed lines before writing them to a file
        ArrayList<String> reversedLines = new ArrayList<String>();

        // getting the input and output files from the users's command line arguments
        String inputFile = args[0];
        String outputFile = args[1];

        // scanner object to read from a file
        Scanner myReader;

        // counter to keep track of the number of lines in the text file
        // int lineCounter = 0;
        
        try {
            File myFile = new File(inputFile);
            myReader = new Scanner(myFile);

            // reading lines from text file, inserting them into the stack
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines.push(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // using while loop instead of the condiitonal statement
        while(!lines.isEmpty()){
            String currentLine = lines.pop();

            // reversing the current line
            String reversedLine = reverseSentence(currentLine);
            
            // adding the reversed line to the reversedLines arraylist
            reversedLines.add(reversedLine);
        }

        // creating the new file with the TFRT result
        resultantFile(reversedLines, outputFile);
    }

    // **************************************************
    // HELPER METHODS
    // **************************************************

    /**
     * method to write to the resultant File
     * @param lines an ArrayList with the reversed lines
     * @param outputFileName the name of the output file
     */

    public static void resultantFile(ArrayList<String> lines, String outputFileName){

        // keeping track of the original line numbers in the text file
        int lineCounter = lines.size();

        // creating the new file with the TFRT result
        File resultFile = new File(outputFileName);

        try {
            if (resultFile.createNewFile()) {
                System.out.println("File created: " + resultFile.getName());
                
                // writing the reversed lines to the created result.txt file
                myWriter = new FileWriter(resultFile);
                for (String line : lines){
                    myWriter.write(lineCounter + ". " + line + "\n");
                    lineCounter--;
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");

            } else {
                System.out.println("File already exists.");
            }
            } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * method for reversing a single word
     * @param str the string to be reversed
     * @return the reversed string
     */

    public static String reverseWord(String str){
        String reversedWord = "";

        // iterating and appending letters of <str> to the left of <reversedWord>
        for (int i = 0; i < str.length(); i++){
            reversedWord = str.charAt(i) + reversedWord;
        }
        // returning the reversed word
        return reversedWord;
    }
    
    /**
     * method for reversing a single sentence
     * @param str the sentence to be reversed
     * @return the reversed sentence
     */

    public static String reverseSentence(String str){
        String reversedSentence = "";

        // splitting the sentence into words using space as a delimiter
        String[] wordsInSentence = str.split(" ");

        // traversing the words and pushing them to the words stack
        for(String word : wordsInSentence){
            words.push(word);                     // pushing current word to the stack
        }
    
        for(int i = 0; i < wordsInSentence.length; i++){
            // extracting words from the stack and reversing them, concatenating them to the reversed sentence
            reversedSentence = reversedSentence + reverseWord(words.pop()) + " ";           // adding a space 'delimeter' after each word
        }
        // returning the reversed sentence
        return reversedSentence;
    }
}
