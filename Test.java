import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Test {
    // stack to store the extracted lines from a text file
    static Stack<String> lines = new Stack<String>();

    // stack to store the extracted words from a line
    static Stack<String> words = new Stack<String>();

    // stack to store the extracted characters from a word
    static Stack<Character> characters =  new Stack<Character>();

    public static void main(String[] args) {

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
        return reversedSentence;
    }
}