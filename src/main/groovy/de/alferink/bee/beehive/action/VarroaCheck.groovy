package de.alferink.bee.beehive.action

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Transient
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@CompileStatic
@Entity
class VarroaCheck extends ChangeBeehiveAction {

    @NotNull
    @Min(0L)
    Integer quantity

    @NotNull
    @Min(1L)
    Integer days

    @Override
    @Transient
    BeehiveActionType getActionType() {
        BeehiveActionType.VARROA_CHECK
    }
}
