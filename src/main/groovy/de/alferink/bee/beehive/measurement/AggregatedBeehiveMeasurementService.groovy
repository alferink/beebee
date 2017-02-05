package de.alferink.bee.beehive.measurement

import de.alferink.bee.BaseEntity
import de.alferink.bee.DateConverterUtils
import de.alferink.bee.beehive.Beehive
import de.alferink.bee.beehive.BeehiveRepository
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.LocalDate

@CompileStatic
@Service
class AggregatedBeehiveMeasurementService extends BaseEntity {

    @Autowired
    BeehiveRepository beehiveRepository

    @Autowired
    RawBeehiveMeasurementRepository rawBeehiveMeasurementRepository

    @Autowired
    AggregatedBeehiveMeasurementRepository aggregatedBeehiveMeasurementRepository

    void updateAggregatedBeehiveMeasurements() {
        aggregatedBeehiveMeasurementRepository.deleteAll()

        beehiveRepository.findAll().each { beehive ->
            updateAggregatedBeehiveMeasurements(beehive)
        }
    }

    void updateAggregatedBeehiveMeasurements(Beehive beehive) {
        List<RawBeehiveMeasurement> beehiveMeasurements = rawBeehiveMeasurementRepository.findByBeehiveId(beehive.id) as List

        Map<RawBeehiveMeasurementGroup, List<RawBeehiveMeasurement>> groupBy = beehiveMeasurements.groupBy {
            RawBeehiveMeasurementGroup.createForDay(it)
        }

        def average = { list -> (((Collection<? extends Number>) list).sum() as Number) / ((Collection) list).size() }

        groupBy.each { RawBeehiveMeasurementGroup group, List<RawBeehiveMeasurement> measurements ->
            AggregatedBeehiveMeasurement aggregatedMeasurement = new AggregatedBeehiveMeasurement()
            aggregatedMeasurement.date = group.utilsDate
            aggregatedMeasurement.period = AggregationPeriod.DAILY
            aggregatedMeasurement.temperatureOutsideMin = measurements.min { it.temperatureOutside }.temperatureOutside
            aggregatedMeasurement.temperatureOutsideMax = measurements.max { it.temperatureOutside }.temperatureOutside
            aggregatedMeasurement.temperatureOutside = average(measurements.temperatureOutside)
            aggregatedMeasurement.humidityOutside = average(measurements.humidityOutside)
            aggregatedMeasurement.temperatureInside = average(measurements.temperatureInside)
            aggregatedMeasurement.weight = measurements.min { it.weight }.weight
            aggregatedMeasurement.beehive = beehive

            aggregatedBeehiveMeasurementRepository.save(aggregatedMeasurement)
        }
    }
}

@EqualsAndHashCode
class RawBeehiveMeasurementGroup {
    LocalDate date

    static RawBeehiveMeasurementGroup createForDay(RawBeehiveMeasurement rawBeehiveMeasurement) {
        RawBeehiveMeasurementGroup group = new RawBeehiveMeasurementGroup()
        group.date = DateConverterUtils.toLocalDate(rawBeehiveMeasurement.date)
        group.date.atStartOfDay()
        group
    }

    Date getUtilsDate() {
        DateConverterUtils.toDate(date)
    }
}
