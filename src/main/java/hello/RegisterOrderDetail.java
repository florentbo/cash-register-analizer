package hello;

import javax.persistence.*;

@Entity
@Table(name="kassaorder_detail")
public class RegisterOrderDetail {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="orderid", nullable=false, updatable=false)
    private RegisterOrder registerOrder;

    @ManyToOne(optional=false)
    @JoinColumn(name="productid", nullable=false, updatable=false)
    private Item product;

    public RegisterOrder getRegisterOrder() {
        return registerOrder;
    }

    public Item getProduct() {
        return product;
    }
}

