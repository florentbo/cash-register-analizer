package be.florentbo.register.repository;

import javax.persistence.*;

@Entity
@Table(name="kassaorder_detail")
public class RegisterOrderDetail {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="orderid", nullable=false, updatable=false)
    private RegisterOrder registerOrder;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @JoinColumn(name="productid", nullable=false, updatable=false)
    private Item product;

    @Column(name="aantal")
    private int quantity;

    public Item getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public static Builder getBuilder(String product, int quantity) {
        return new Builder(product,quantity);
    }

    public static class Builder {

        private RegisterOrderDetail built;

        public Builder(String productName, int quantity) {
            built = new RegisterOrderDetail();
            built.product = Item.getBuilder(productName).build();
            built.quantity = quantity;
        }

        public RegisterOrderDetail build() {
            return built;
        }
    }
}

