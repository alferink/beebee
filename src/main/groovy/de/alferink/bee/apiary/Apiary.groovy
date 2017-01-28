package de.alferink.bee.apiary

import de.alferink.bee.MessageUtils
import de.alferink.bee.beehive.Beehive
import groovy.transform.CompileStatic
import org.hibernate.annotations.GenericGenerator

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@CompileStatic
@Entity
class Apiary {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id

    @NotNull
    @Size(min=1, max=255)
    String name

    @OneToMany(mappedBy = "apiary")
    @OrderBy("name ASC")
    List<Beehive> beehives

    String getInstanceName(){
        MessageUtils.getInstanceNameMessage(this, name)
    }

    @Override
    String toString() {
        instanceName
    }
}
