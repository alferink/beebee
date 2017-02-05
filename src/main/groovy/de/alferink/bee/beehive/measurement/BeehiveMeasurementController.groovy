package de.alferink.bee.beehive.measurement

import de.alferink.bee.DateConverterUtils
import de.alferink.bee.beehive.Beehive
import de.alferink.bee.beehive.BeehiveRepository
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import java.time.LocalDate

@CompileStatic
@Controller
@RequestMapping(value = 'beehive/{beehiveId}/beehivemeasurements')
class BeehiveMeasurementController {

    @Autowired
    BeehiveRepository beehiveRepository

    @Autowired
    AggregatedBeehiveMeasurementRepository aggregatedBeehiveMeasurementRepository

    @Autowired
    AggregatedBeehiveMeasurementService aggregatedBeehiveMeasurementService

    @RequestMapping(value = ["/" ,"index"])
    public String show(
            @PathVariable String beehiveId, @Param('from') Date from, @Param('till') Date till, Model model) {
        Beehive beehive = beehiveRepository.findOne(beehiveId)
        List<Date> availableMonths = getAvailableMonths(beehive)
        from = from ?: availableMonths.last()
        till = till ?: datePlusOneMonth(from)

        List<AggregatedBeehiveMeasurement> aggregatedBeehiveMeasurements = aggregatedBeehiveMeasurementRepository.findByBeehiveIdAndDateBetween(beehiveId, from, till)
        aggregatedBeehiveMeasurements.each {
            it.beehive = null
        }
        model.addAttribute("aggregatedBeehiveMeasurementsMap", new JsonSlurper().parseText(JsonOutput.toJson(aggregatedBeehiveMeasurements)));
        model.addAttribute("aggregatedBeehiveMeasurements", aggregatedBeehiveMeasurements);
        model.addAttribute("beehive", beehive);
        model.addAttribute("filter", new Filter(from: from, till: till));
        model.addAttribute("availableMonths", availableMonths);

        return "beehiveMeasurement/show";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@PathVariable String beehiveId) {
        Beehive beehive = beehiveRepository.findOne(beehiveId)
        aggregatedBeehiveMeasurementService.updateAggregatedBeehiveMeasurements()
        return "redirect:index?beehiveId=$beehiveId";
    }

    private List<Date> getAvailableMonths(Beehive beehive) {
        LocalDate minDate = LocalDate.of(2016, 1, 1)
        LocalDate now = LocalDate.now()
        List<Date> dates = []
        LocalDate localDate = minDate
        while (localDate.isBefore(now)) {
            dates.add(DateConverterUtils.toDate(localDate))
            localDate = localDate.plusMonths(1)
        }
        dates
    }

    private Date datePlusOneMonth(Date date) {
        LocalDate localDate = DateConverterUtils.toLocalDate(date)
        localDate = localDate.plusMonths(1)
        DateConverterUtils.toDate(localDate)
    }
}

class Filter {
    Date from
    Date till
}