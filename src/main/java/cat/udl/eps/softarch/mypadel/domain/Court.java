package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.*;

@Entity
public class Court extends UriEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "available", nullable = false)
    private boolean available = true;

    @Column(name = "isIndoor", nullable = false)
    private boolean isIndoor;


    @Override
    public String getId() {
        return String.valueOf(id);
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


}
