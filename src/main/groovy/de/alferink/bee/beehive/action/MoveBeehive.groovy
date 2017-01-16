package de.alferink.bee.beehive.action

import de.alferink.bee.apiary.Apiary

import groovy.transform.CompileStatic

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.Transient
import javax.validation.constraints.NotNull

@CompileStatic
@Entity
@EntityListeners(MoveBeehiveUpdateApiaryEntityListener)
class MoveBeehive extends ChangeBeehiveAction {

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Apiary moveTo

    @Override
    @Transient
    BeehiveActionType getActionType() {
        BeehiveActionType.MOVE_BEEHIVE
    }
}
