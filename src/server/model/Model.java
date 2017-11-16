/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model;

/**
 *
 * @author philip
 */
public class Model {
    private String word;
    private char [] wordSoFar;
    private int lettersRevealed;
    public int guessesLeft;
    private int score = 0;
        
    public Model(){
        // TODO: Take words ranodmly out of list
        changeWord("FIRST");
    }
    
    public void changeWord(String word){
        this.word = word;
        wordSoFar = new char[word.length()];
        for(int i = 0; i < wordSoFar.length; i++){
            wordSoFar[i] = "_".charAt(0);
        }
        this.guessesLeft = word.length();
        lettersRevealed = 0;
    }
    public String guessLetter(char Char){
        for(int i = 0; i < word.length(); i++){
            if(this.word.charAt(i) == Char){
                this.wordSoFar[i] = Char;
                this.lettersRevealed++;
            }
        }
        return new String(wordSoFar);
    }
    public boolean ifWon(){
        if(lettersRevealed == word.length()){
            return true;
        }
        else 
            return false;
    }
    public boolean ifLose(){
        if(guessesLeft == 0){
            return true;
        }
        else
            return false;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public String getWord(){
        return this.word;
    }
    public void won(){
        this.score++;
    }
    public void decrementGuess(){
        this.guessesLeft--;
    }
    
}
