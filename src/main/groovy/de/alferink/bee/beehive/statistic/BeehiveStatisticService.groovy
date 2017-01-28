package de.alferink.bee.beehive.statistic

import de.alferink.bee.apiary.Apiary
import de.alferink.bee.beehive.Beehive
import de.alferink.bee.beehive.VarroaTreatmentDrug
import de.alferink.bee.beehive.action.BeehiveActionType
import de.alferink.bee.beehive.action.ChangeBeehiveAction
import de.alferink.bee.beehive.action.Rating
import org.springframework.stereotype.Service

import java.time.LocalDate
import java.time.ZoneId

@Service
class BeehiveStatisticService {

    List<BeehiveStatistics> createBeehiveStatistics(Apiary apiary, IntRange years) {

        apiary.beehives.collect { Beehive beehive ->
            new BeehiveStatistics(
                    beehive: new BeehiveInfo(name: beehive.name, color: beehive.color),
                    beehiveStatistics: years.collect { Integer year ->
                        createBeehiveStatistic(beehive, year)
                    }
            )
        }
    }

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
        beehiveStatistic.feedQuantity = actions.findAll {
            it.actionType == BeehiveActionType.FEEDING
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

        beehiveStatistic.averageRating = createAverageRating(actions)

        beehiveStatistic
    }

    AverageRating createAverageRating(List<ChangeBeehiveAction> actions) {

        AverageRating averageRating = new AverageRating()

        List<Rating> ratings = actions.collect { it.rating }.findAll { it.enabled }

        List<Rating> eggRatings = ratings.findAll { it.egg != null }
        averageRating.egg = eggRatings.size() == 0 ? null : eggRatings.sum { it.egg } / eggRatings.size()

        List<Rating> larvaRatings = ratings.findAll { it.larva != null }
        averageRating.larva = larvaRatings.size() == 0 ? null : larvaRatings.sum { it.larva } / larvaRatings.size()

        List<Rating> cellCappedRatings = ratings.findAll { it.cellCapped != null }
        averageRating.cellCapped = cellCappedRatings.size() == 0 ? null : cellCappedRatings.sum { it.cellCapped } / cellCappedRatings.size()

        List<Rating> wabensitzRatings = ratings.findAll { it.wabensitz != null }
        averageRating.wabensitz = wabensitzRatings.size() == 0 ? null : wabensitzRatings.sum { it.wabensitz } / wabensitzRatings.size()

        List<Rating> sanftmutRatings = ratings.findAll { it.sanftmut != null }
        averageRating.sanftmut = sanftmutRatings.size() == 0 ? null : sanftmutRatings.sum { it.sanftmut } / sanftmutRatings.size()

        List<Rating> volksstaerkeRatings = ratings.findAll { it.volksstaerke != null }
        averageRating.volksstaerke = volksstaerkeRatings.size() == 0 ? null : volksstaerkeRatings.sum { it.volksstaerke } / volksstaerkeRatings.size()

        averageRating
    }
}
