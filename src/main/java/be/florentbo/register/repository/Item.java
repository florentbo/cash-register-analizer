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

    public static Builder getBuilder(String itemName) {
        return new Builder(itemName);
    }

    public static class Builder {

        private Item built;

        public Builder(String itemName) {
            built = new Item();
            built.name = itemName;
        }

        public Item build() {
            return built;
        }
    }
}

