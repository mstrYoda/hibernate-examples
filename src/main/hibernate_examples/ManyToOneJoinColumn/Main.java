package ManyToOneJoinColumn;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Hibernate_Jpa");
        EntityManager entitymanager = emfactory.createEntityManager();

        initData(entitymanager); // run this for first time only

        List<ManyToOneJoinColumn.Shipment> shipments = entitymanager.createQuery("select s from ManyToOneJoinColumn.Shipment s").getResultList();

        for (ManyToOneJoinColumn.Shipment shipment : shipments) {
            System.out.println(
                    String.format("Child item size for shipment id:%d is %d", shipment.getId(), shipment.getChildList().size()));
        }

        entitymanager.close();
        emfactory.close();
    }

    private static void initData(EntityManager entitymanager) {
        entitymanager.getTransaction().begin();
        ManyToOneJoinColumn.Shipment shipment = new ManyToOneJoinColumn.Shipment();
        shipment.setCargoName("test cargo");

        ManyToOneJoinColumn.Item item = new ManyToOneJoinColumn.Item();
        item.setItemName("test item");

        shipment.addItem(item);
        entitymanager.persist(shipment);
        entitymanager.getTransaction().commit();
    }
}
