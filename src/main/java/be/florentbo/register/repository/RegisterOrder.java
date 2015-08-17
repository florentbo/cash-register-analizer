package be.florentbo.register.repository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="kassaorders")
public class RegisterOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="serial")
    private long id;
    @Column(name="ordertijd")
    private LocalDateTime orderTime;

    @OneToMany(mappedBy="registerOrder", fetch=FetchType.LAZY)
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

    public static Builder getBuilder(LocalDateTime orderTime) {
        return new Builder(orderTime);
    }

    @Override
    public String toString() {
        return "RegisterOrder{" +
                "id=" + id +
                ", orderTime=" + orderTime +
                //", details=" + details +
                '}';
    }

    public static class Builder {

        private RegisterOrder built;

        public Builder(LocalDateTime orderTime) {
            built = new RegisterOrder();
            built.orderTime = orderTime;
        }

        public RegisterOrder build() {
            return built;
        }
    }
}

