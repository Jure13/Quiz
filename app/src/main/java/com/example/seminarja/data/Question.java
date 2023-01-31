package com.example.seminarja.data;

import java.util.ArrayList;

public class Question {
    String question;
    ArrayList<String> answer;
    ArrayList<Boolean> boolAnswer;

    public Question(String question, ArrayList<String> answer, ArrayList<Boolean> boolAnswer){
        this.question = question;
        this.answer = answer;
        this.boolAnswer = boolAnswer;
    }

    public Question(){

    }
    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public ArrayList<Boolean> getBoolAnswer() {
        return boolAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public void setBoolAnswer(ArrayList<Boolean> boolAnswer) {
        this.boolAnswer = boolAnswer;
    }
}
