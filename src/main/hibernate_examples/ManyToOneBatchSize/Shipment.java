package ManyToOneBatchSize;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "shipments")
@Getter
@Setter
class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cargoName;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "shipment")
    @BatchSize(size = 10)
    private List<Item> childList;

    public void addItem(Item item) {
        if (childList == null) {
            childList = new ArrayList();
        }
        childList.add(item);
        item.setShipment(this);
    }
}