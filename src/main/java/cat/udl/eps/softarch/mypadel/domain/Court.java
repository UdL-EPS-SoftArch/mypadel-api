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
    

}
