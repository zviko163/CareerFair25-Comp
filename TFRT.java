
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

        Scanner myReader;
        try {
            File myFile = new File("testfile.txt");
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
        resultantFile(reversedLines);
    }

    // method to provide resultant File
    public static void resultantFile(ArrayList<String> lines){

        // creating the new file with the TFRT result
        File resultFile = new File("result.txt");

        try {   
            if (resultFile.createNewFile()) {
                System.out.println("File created: " + resultFile.getName());
                
                // writing the reversed lines to the created result.txt file
                myWriter = new FileWriter(resultFile);
                for (String line : lines){
                    myWriter.write(line + "\n");
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
        // return resultFile;
    }

    // method for reversing a single word
    public static String reverseWord(String str){
        String reversedWord = "";

        // iterating and appending letters of <str> to the left of <reversedWord>
        for (int i = 0; i < str.length(); i++){
            reversedWord = str.charAt(i) + reversedWord;
        }
        // returning the reversed word
        return reversedWord;
    }
    
    // method for reversing a single sentence
    public static String reverseSentence(String str){
        String reversedSentence = "";    
        // splitting the sentence into words using space as a delimiter
        String[] wordsInSentence = str.split(" ");

        // traversing the words and pushing them to the words stack
        for(String word : wordsInSentence){
            // pushing current word to the stack
            words.push(word);
            System.out.println(word);
        }
    
        for(int i = 0; i < wordsInSentence.length; i++){
            // extracting words from the stack and reversing them, concatenating them to the reversed sentence
            reversedSentence = reversedSentence + reverseWord(words.pop()) + " ";           // adding a space 'delimeter' after each word
        }
        // returning the reversed sentence
        return reversedSentence;
    }
}
