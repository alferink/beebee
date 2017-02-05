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
class AggregatedBeehiveMeasurement extends BaseEntity {

    @NotNull
    Date date

    @NotNull
    AggregationPeriod period

    BigDecimal temperatureOutsideMin
    BigDecimal temperatureOutsideMax

    BigDecimal temperatureOutside
    BigDecimal humidityOutside

    BigDecimal temperatureInside

    BigDecimal weight
    BigDecimal weightDifference

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEEHIVE_ID")
    Beehive beehive
}
