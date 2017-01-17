package de.alferink.bee.beehive.statistic

import de.alferink.bee.beehive.action.AddRemove
import de.alferink.bee.beehive.action.Rating

class BeehiveStatistic {

    Integer year

    BigDecimal honeyQuantity

    Integer numberOfTreatmentsFormicAcid
    Boolean treatmentsWithOxalicAcid

    Rating averageRating
    AddRemove addRemoveSum
}
