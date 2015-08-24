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

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public static Builder getBuilder(LocalDateTime orderTime) {
        return new Builder(orderTime);
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

