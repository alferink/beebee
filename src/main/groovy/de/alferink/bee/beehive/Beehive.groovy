package de.alferink.bee.beehive

import de.alferink.bee.MessageUtils
import de.alferink.bee.apiary.Apiary
import de.alferink.bee.beehive.action.BeehiveAction
import de.alferink.bee.beehive.action.BeehiveActionType
import de.alferink.bee.beehive.action.ChangeBeehiveAction
import de.alferink.bee.beehive.action.Creation
import de.alferink.bee.beehive.action.MoveBeehive
import groovy.transform.CompileStatic
import org.hibernate.annotations.GenericGenerator

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Transient
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@CompileStatic
@Entity
@EntityListeners(BeehiveEntityListener)
class Beehive {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id

    Date dissolved

    @NotNull
    @Size(min = 1, max = 255)
    String name

    String color

    HiveType hiveType

    @OneToMany(mappedBy = "beehive", fetch = FetchType.EAGER)
    List<ChangeBeehiveAction> beehiveActions

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "beehive_id")
    Creation creationAction = new Creation()

    @ManyToOne
    Apiary apiary

    @Transient
    List<BeehiveAction> getAllBeehiveActions() {
        List<BeehiveAction> actions = []
        actions.addAll(beehiveActions)
        actions = actions.sort { it.date }.reverse()
        actions.add(creationAction)
        actions
    }

    @Transient
    Date getCreated() {
        creationAction?.date
    }

    void updateApiary() {
        MoveBeehive lastMovement = (MoveBeehive) beehiveActions.findAll {
            it.actionType == BeehiveActionType.MOVE_BEEHIVE
        }.max { it.date }
        if (lastMovement) {
            apiary = lastMovement.moveTo
        } else {
            apiary = creationAction.apiary
        }

    }

    String getInstanceName(){
        MessageUtils.getInstanceNameMessage(this, name)
    }

    @Override
    String toString() {
        instanceName
    }
}
