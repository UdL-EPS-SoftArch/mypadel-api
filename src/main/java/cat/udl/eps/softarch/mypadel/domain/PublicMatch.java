package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.Entity;

@Entity
public class PublicMatch extends Match{

    private Level level;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
