package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.*;

@Entity
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "available", nullable = false)
    private boolean available;

    @Column(name = "isIndoor", nullable = false)
    private boolean isIndoor;

    public Court(boolean isIndoor) {
        this.available = true;
        this.isIndoor = isIndoor;
    }

    public Court(boolean available, boolean isIndoor) {
        this.available = available;
        this.isIndoor = isIndoor;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isIndoor() {
        return isIndoor;
    }

    public void setIndoor(boolean indoor) {
        isIndoor = indoor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Court)) return false;

        Court court = (Court) o;

        if (getId() != court.getId()) return false;
        if (isAvailable() != court.isAvailable()) return false;
        return isIndoor() == court.isIndoor();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (isAvailable() ? 1 : 0);
        result = 31 * result + (isIndoor() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Court{" +
                "id=" + id +
                ", available=" + available +
                ", isIndoor=" + isIndoor +
                '}';
    }
}
