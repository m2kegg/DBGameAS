package ru.samsung.itschool.dbgame;

public class Result {
        String name;
        int score;
        Result(String name, int score)
        {
        	this.name = name;
        	this.score = score;
        }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
