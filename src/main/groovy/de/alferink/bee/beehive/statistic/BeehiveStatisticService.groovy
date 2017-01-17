package de.alferink.bee.beehive.statistic

import de.alferink.bee.beehive.Beehive
import de.alferink.bee.beehive.VarroaTreatmentDrug
import de.alferink.bee.beehive.action.BeehiveActionType
import org.springframework.stereotype.Service

import java.time.LocalDate
import java.time.ZoneId

@Service
class BeehiveStatisticService {

    BeehiveStatistic createBeehiveStatistic(Beehive beehive, Integer year) {

        def actions = beehive.beehiveActions.findAll {
            LocalDate date = it.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            date.year == year
        }

        BeehiveStatistic beehiveStatistic = new BeehiveStatistic()
        beehiveStatistic.year = year
        beehiveStatistic.honeyQuantity = actions.findAll {
            it.actionType == BeehiveActionType.HONEY_HARVEST
        }.sum {
            it.quantity
        }
        beehiveStatistic.numberOfTreatmentsFormicAcid = actions.count {
            it.actionType == BeehiveActionType.VARROA_TREATMENT &&
                    it.medicine == VarroaTreatmentDrug.AMEISENSAEURE_60
        }
        beehiveStatistic.treatmentsWithOxalicAcid = actions.find {
            it.actionType == BeehiveActionType.VARROA_TREATMENT &&
                    it.medicine == VarroaTreatmentDrug.OXALSAEURE
        } != null

        beehiveStatistic
        /*
    Rating averageRating
    AddRemove addRemoveSum
         */
    }
}
