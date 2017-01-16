package de.alferink.bee.beehive.action

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Transient

@CompileStatic
@Entity
class Review extends ChangeBeehiveAction {

    Review() {
        rating.enabled = true
        addRemove.enabled = true
    }

    @Override
    @Transient
    BeehiveActionType getActionType() {
        BeehiveActionType.REVIEW
    }
}
