/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Java;
 
 import java.util.StringTokenizer;

public class WordCountThread implements Runnable {
    private String text;
    private int wordCount;

    public WordCountThread(String text) {
        this.text = text;
    }

    public int getWordCount() {
        return wordCount;
    }

    @Override
    public void run() {
        StringTokenizer tokenizer = new StringTokenizer(text);
        wordCount = tokenizer.countTokens();
    }
}
