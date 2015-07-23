package hello;

import javax.persistence.*;

@Entity
@Table(name="kassaorders")
public class RegisterOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="serial")
    private long id;
    //ordertijd
    @Column(name="ordertijd")
    private String orderTime;


    protected RegisterOrder() {}

    public long getId() {
        return id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    @Override
    public String toString() {
        return "RegisterOrder{" +
                "id=" + id +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}

