package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;

@Entity
public class Player extends User {
    public enum Level{NOVICE,BEGINNER,INTERMEDIATE,ADVANCED,BRUTAL}
    @JsonIgnore
    private int score;
    private Level level;
    public Level getLevel() {
        return level;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setLevel(Level level) {
        this.level = level;
    }
}
