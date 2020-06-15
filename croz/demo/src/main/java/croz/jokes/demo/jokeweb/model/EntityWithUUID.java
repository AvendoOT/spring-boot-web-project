package croz.jokes.demo.jokeweb.model;

import org.hibernate.annotations.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Superclass that specifies the type of id every
 * entry in database needs to have. UUID is
 * generated randomly.
 * @author Leon Kranjcevic
 */
@MappedSuperclass
public class EntityWithUUID {

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue
    private UUID id;

    public EntityWithUUID() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

}
