package be.florentbo.register.repository;

import javax.persistence.*;

@Entity
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="item_name")
    private String name;

    public String getName() {
        return name;
    }
}

