package hello;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="kassaorders")
public class RegisterOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="serial")
    private long id;
    @Column(name="ordertijd")
    private LocalDateTime orderTime;

    @OneToMany(mappedBy="registerOrder", fetch=FetchType.EAGER)
    private Set<RegisterOrderDetail> details;


    protected RegisterOrder() {}

    public long getId() {
        return id;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public Set<RegisterOrderDetail> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "RegisterOrder{" +
                "id=" + id +
                ", orderTime=" + orderTime +
                //", details=" + details +
                '}';
    }
}

