package de.alferink.bee.beehive

import de.alferink.bee.apiary.ApiaryRepository
import de.alferink.bee.beehive.action.CreationRepository
import de.alferink.bee.beehive.statistic.BeehiveStateService
import de.alferink.bee.beehive.statistic.BeehiveStatisticService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

import javax.validation.Valid
import java.time.LocalDate

@Controller
@RequestMapping(value = '/beehive')
class BeehiveController {

    @Autowired
    CreationRepository creationRepository

    @Autowired
    BeehiveRepository beehiveRepository

    @Autowired
    ApiaryRepository apiaryRepository

    @Autowired
    BeehiveStatisticService beehiveStatisticService

    @Autowired
    BeehiveStateService beehiveStateService

    @RequestMapping(value = ["/", "index"])
    public String index(Model model) {
        model.addAttribute("beehives", beehiveRepository.findAll());
        return "beehive/index";
    }

    @RequestMapping(value = "{id}")
    public String show(@PathVariable String id, @RequestParam(required = false) Integer actionsYear, Model model) {
        int currentYear = LocalDate.now().year

        Beehive beehive = beehiveRepository.findOne(id)
        model.addAttribute("beehive", beehive);
        model.addAttribute("beehiveState", beehiveStateService.createBeehiveState(beehive, new Date()));
        model.addAttribute("beehiveStatistic", beehiveStatisticService.createBeehiveStatistic(beehive, currentYear));
        model.addAttribute("beehiveStatisticLastYear", beehiveStatisticService.createBeehiveStatistic(beehive, currentYear - 1));

        List<Integer> beehiveActionYears = (beehive.allBeehiveActions.collect {
            it.year
        } as Set) as List
        beehiveActionYears = beehiveActionYears.sort().reverse()
        actionsYear = actionsYear ?: beehiveActionYears.max()
        model.addAttribute("actionsYear", actionsYear);
        model.addAttribute("actionsYears", beehiveActionYears);

        return "beehive/show";
    }

    @RequestMapping(value = "create")
    public String create(Beehive beehive, ModelMap model) {
        model.addAttribute("beehive", beehive);
        model.addAttribute("apiaries", apiaryRepository.findAll());
        return "beehive/create";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Valid Beehive beehive, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("apiaries", apiaryRepository.findAll());
            return "beehive/create";
        }

        beehiveRepository.save(beehive);
        return "redirect:index";
    }

    @RequestMapping(value = "edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("beehive", beehiveRepository.findOne(id));
        model.addAttribute("apiaries", apiaryRepository.findAll());
        return "beehive/edit";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid Beehive beehive, BindingResult bindingResult, Model model) {
        beehive.creationAction = creationRepository.findOne(beehive.creationAction.id)
        if (bindingResult.hasErrors()) {
            model.addAttribute("apiaries", apiaryRepository.findAll());
            return "beehive/edit";
        }
        beehiveRepository.save(beehive);
        return "redirect:${beehive.id}";
    }
}
