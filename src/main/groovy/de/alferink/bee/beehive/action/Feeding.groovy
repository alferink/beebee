package de.alferink.bee.beehive.action

import de.alferink.bee.beehive.BeeFeed
import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Transient
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@CompileStatic
@Entity
class Feeding extends BeehiveAction {

    @NotNull
    BeeFeed feed // KAT

    @NotNull
    @Min(0L)
    BigDecimal quantity // kg

    @Override
    @Transient
    BeehiveActionType getActionType() {
        BeehiveActionType.FEEDING
    }
}
