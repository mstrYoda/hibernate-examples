package OneToManyJoinTable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Hibernate_Jpa");
        EntityManager entitymanager = emfactory.createEntityManager();

        initData(entitymanager);

        OneToManyJoinTable.Shipment shipment = entitymanager.find(OneToManyJoinTable.Shipment.class, 1);
        System.out.println(shipment.getChildList().size());

        entitymanager.close();
        emfactory.close();
    }

    private static void initData(EntityManager entitymanager) {
        entitymanager.getTransaction().begin();
        OneToManyJoinTable.Shipment shipment = new OneToManyJoinTable.Shipment();
        shipment.setCargoName("test cargo");

        OneToManyJoinTable.Item item = new OneToManyJoinTable.Item();
        item.setItemName("test item");

        shipment.addItem(item);
        entitymanager.persist(shipment);
        entitymanager.getTransaction().commit();
    }

}
