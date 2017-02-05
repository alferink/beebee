package de.alferink.bee.beehive.measurement

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import java.text.SimpleDateFormat

@Component
class MeasurementTask {

    private static final Logger log = LoggerFactory.getLogger(MeasurementTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    AggregatedBeehiveMeasurementService aggregatedBeehiveMeasurementService

    @Scheduled(fixedRate = 60000l)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));

//        log.info("Start updateAggregatedBeehiveMeasurements", dateFormat.format(new Date()));
//        aggregatedBeehiveMeasurementService.updateAggregatedBeehiveMeasurements()
//        log.info("End updateAggregatedBeehiveMeasurements", dateFormat.format(new Date()));
    }
}
