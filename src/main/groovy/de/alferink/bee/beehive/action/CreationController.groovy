package de.alferink.bee.beehive.action

import de.alferink.bee.apiary.ApiaryRepository
import de.alferink.bee.beehive.BeehiveRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import javax.validation.Valid

@Controller
@RequestMapping(value = 'beehive/{beehiveId}/creation')
class CreationController {

    @Autowired
    CreationRepository creationRepository

    @Autowired
    ApiaryRepository apiaryRepository

    @Autowired
    BeehiveRepository beehiveRepository

    @RequestMapping(value = "{id}")
    public String show(@PathVariable String id, Model model) {
        Creation creation = creationRepository.findOne(id)
        model.addAttribute("beehiveAction", creation);

        List<BeehiveAction> actions = creation.beehive.allBeehiveActions
        model.addAttribute("nextBeehiveAction", actions.size() > 1 ? actions.get(actions.size()-2) : null)

        return "beehiveAction/show";
    }

    @RequestMapping(value = "edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("beehiveAction", creationRepository.findOne(id));
        model.addAttribute("apiaries", apiaryRepository.findAll());
        return "beehiveAction/edit";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid Creation beehiveAction, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String validationKey = model.asMap().keySet().find {
                it.startsWith('org.springframework.validation.BindingResult')
            }
            model.addAttribute("org.springframework.validation.BindingResult.beehiveAction", model."$validationKey")
            model.addAttribute("beehiveAction", beehiveAction);
            model.addAttribute("apiaries", apiaryRepository.findAll());
            return "beehiveAction/edit";
        }
        creationRepository.save(beehiveAction);
        return "redirect:/beehive/${beehiveAction.beehive.id}";
    }
}
