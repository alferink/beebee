package de.alferink.bee.beehive.action

import de.alferink.bee.BaseEntity
import de.alferink.bee.beehive.HoneyType
import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Transient
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@CompileStatic
@Entity
class HoneyHarvest extends BeehiveAction {

    @NotNull
    HoneyType honeyType // sorte

    @NotNull
    @Min(0L)
    BigDecimal quantity

    @Min(0L)
    BigDecimal waterContent

    @Override
    @Transient
    BeehiveActionType getActionType() {
        BeehiveActionType.HONEY_HARVEST
    }
}
