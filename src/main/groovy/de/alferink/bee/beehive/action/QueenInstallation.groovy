package de.alferink.bee.beehive.action

import de.alferink.bee.beehive.Queen
import groovy.transform.CompileStatic

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne
import javax.persistence.Transient

@CompileStatic
@Entity
class QueenInstallation extends ChangeBeehiveAction {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Queen queen = new Queen()

    @Override
    @Transient
    BeehiveActionType getActionType() {
        BeehiveActionType.QUEEN_INSTALLATION
    }
}
