package ManyToOneBatchSize;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "items")
@Getter
@Setter
class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}