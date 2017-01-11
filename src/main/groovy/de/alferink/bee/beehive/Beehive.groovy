package de.alferink.bee.beehive

import de.alferink.bee.beehive.action.BeehiveAction
import groovy.transform.CompileStatic
import org.hibernate.annotations.GenericGenerator

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@CompileStatic
@Entity
class Beehive {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id

    Date created

    Date dissolved

    @NotNull
    @Size(min=1, max=255)
    String name

    HiveType hiveType

    @OneToMany(mappedBy = "beehive", fetch = FetchType.EAGER)
    List<BeehiveAction> beehiveActions
}
