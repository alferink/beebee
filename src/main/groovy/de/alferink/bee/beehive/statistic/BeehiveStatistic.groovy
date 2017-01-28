package de.alferink.bee.beehive.statistic

import de.alferink.bee.beehive.action.AddRemove
import de.alferink.bee.beehive.action.Rating

class BeehiveStatistic {

    Integer year

    BigDecimal honeyQuantity
    BigDecimal feedQuantity

    Integer numberOfTreatmentsFormicAcid
    Boolean treatmentsWithOxalicAcid

    AverageRating averageRating
}
