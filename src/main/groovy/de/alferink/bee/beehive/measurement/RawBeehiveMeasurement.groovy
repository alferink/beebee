package de.alferink.bee.beehive.measurement

import de.alferink.bee.BaseEntity
import de.alferink.bee.beehive.Beehive
import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull

@CompileStatic
@Entity
class RawBeehiveMeasurement extends BaseEntity {

    @NotNull
    Date date

    BigDecimal temperatureOutside
    BigDecimal temperatureInside

    BigDecimal humidityOutside

    BigDecimal weight

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEEHIVE_ID")
    Beehive beehive
}
