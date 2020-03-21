package org.startup.onecheck.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "check_products")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Check {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column(name = "total_price")
    private int totalPrice;

    @ManyToMany
    @JoinTable(name = "check_to_product",
            joinColumns = @JoinColumn(name = "id_check"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "id_basket", referencedColumnName = "id")
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

}
