package de.alferink.bee.beehive.action

import de.alferink.bee.beehive.VarroaTreatmentDrug
import de.alferink.bee.beehive.VarroaTreatmentType
import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Transient
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@CompileStatic
@Entity
class VarroaTreatment extends BeehiveAction {

    @NotNull
    VarroaTreatmentDrug medicine

    @NotNull
    VarroaTreatmentType method

    @NotNull
    @Min(0L)
    BigDecimal quantity

    String lotNumber

    Integer infestationAfterTreatment

    @Override
    @Transient
    BeehiveActionType getActionType() {
        BeehiveActionType.VARROA_TREATMENT
    }

}
