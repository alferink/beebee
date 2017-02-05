package de.alferink.bee.apiary

import de.alferink.bee.beehive.statistic.BeehiveStatisticService
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import javax.validation.Valid
import java.time.LocalDate

@Controller
@RequestMapping(value = ['/apiary'])
class ApiaryController {

    @Autowired
    ApiaryRepository apiaryRepository

    @Autowired
    BeehiveStatisticService beehiveStatisticService

    @RequestMapping(value = ["/", "index"])
    public String index(Model model) {
        model.addAttribute("apiaries", apiaryRepository.findAll());
        return "apiary/index";
    }

    @RequestMapping(value = "{id}")
    public String show(@PathVariable String id, Model model) {
        Apiary apiary = apiaryRepository.findOne(id)
        model.addAttribute("apiary", apiary);

        int currentYear = LocalDate.now().year
        model.addAttribute("beehiveStatistics", new JsonSlurper().parseText(JsonOutput.toJson(beehiveStatisticService.createBeehiveStatistics(apiary, (currentYear - 5..currentYear)))));

        return "apiary/show";
    }

    @RequestMapping(value = "create")
    public String create(Apiary apiary, Model model) {
        model.addAttribute("apiary", apiary);
        return "apiary/create";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Valid Apiary apiary, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "apiary/create";
        }
        apiaryRepository.save(apiary);
        return "redirect:index";
    }

    @RequestMapping(value = "edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("apiary", apiaryRepository.findOne(id));
        return "apiary/edit";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid Apiary apiary, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "apiary/edit";
        }
        apiaryRepository.save(apiary);
        return "redirect:index";
    }
}
