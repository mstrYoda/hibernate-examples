package ManyToOneBatchSize;

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

        List<ManyToOneBatchSize.Shipment> shipments = entitymanager.createQuery("select s from ManyToOneBatchSize.Shipment s").getResultList();

        for (ManyToOneBatchSize.Shipment shipment : shipments) {
            System.out.println(
                    String.format("Child item size for shipment id:%d is %d", shipment.getId(), shipment.getChildList().size()));
        }

        entitymanager.close();
        emfactory.close();
    }

    private static void initData(EntityManager entitymanager) {
        for (int i = 1; i <= 3; i++) {
            ManyToOneBatchSize.Shipment shipment = new ManyToOneBatchSize.Shipment();
            shipment.setCargoName("test shipment " + i);

            for (int j = 1; j <= 3; j++) {
                ManyToOneBatchSize.Item item = new ManyToOneBatchSize.Item();
                item.setItemName("test item " + j);
                shipment.addItem(item);
            }

            entitymanager.getTransaction().begin();
            entitymanager.persist(shipment);
            entitymanager.getTransaction().commit();
        }
    }
}
