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
@RequestMapping(value = 'beehive/{beehiveId}/beehiveactions')
class BeehiveActionController<T extends ChangeBeehiveAction> {

    @Autowired
    BeehiveActionRepository beehiveActionRepository

    @Autowired
    ApiaryRepository apiaryRepository

    @Autowired
    BeehiveRepository beehiveRepository

    @RequestMapping(value = "{id}")
    public String show(@PathVariable String id, Model model) {
        BeehiveAction beehiveAction = beehiveActionRepository.findOne(id)
        model.addAttribute("beehiveAction", beehiveAction);

        List<BeehiveAction> actions = beehiveAction.beehive.allBeehiveActions
        int index = actions.indexOf(beehiveAction)
        if (index > 0) {
            model.addAttribute("nextBeehiveAction", actions.get(index - 1));
        }
        if (index < actions.size() - 1) {
            model.addAttribute("lastBeehiveAction", actions.get(index + 1));
        }

        return "beehiveAction/show";
    }

    @RequestMapping(value = "create")
    public String create(T beehiveAction, @PathVariable String beehiveId, Model model) {
        beehiveAction.beehive = beehiveRepository.findOne(beehiveId)
        model.addAttribute("beehiveAction", beehiveAction);
        model.addAttribute("apiaries", apiaryRepository.findAll());
        return "beehiveAction/create";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Valid T beehiveAction, BindingResult bindingResult, Model model) {
        //beehiveAction.beehive = beehiveRepository.findOne(beehiveId)
        if (bindingResult.hasErrors()) {
            String validationKey = model.asMap().keySet().find {
                it.startsWith('org.springframework.validation.BindingResult')
            }
            model.addAttribute("org.springframework.validation.BindingResult.beehiveAction", model."$validationKey")
            model.addAttribute("beehiveAction", beehiveAction);
            model.addAttribute("apiaries", apiaryRepository.findAll());
            return "beehiveAction/create";
        }
        beehiveActionRepository.save(beehiveAction);
        return "redirect:/beehive/${beehiveAction.beehive.id}";
    }

    @RequestMapping(value = "edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("beehiveAction", beehiveActionRepository.findOne(id));
        model.addAttribute("apiaries", apiaryRepository.findAll());
        return "beehiveAction/edit";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid T beehiveAction, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String validationKey = model.asMap().keySet().find {
                it.startsWith('org.springframework.validation.BindingResult')
            }
            model.addAttribute("org.springframework.validation.BindingResult.beehiveAction", model."$validationKey")
            model.addAttribute("beehiveAction", beehiveAction);
            model.addAttribute("apiaries", apiaryRepository.findAll());
            return "beehiveAction/edit";
        }
        beehiveActionRepository.save(beehiveAction);
        return "redirect:/beehive/${beehiveAction.beehive.id}";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable String id, Model model) {
        BeehiveAction beehiveAction = beehiveActionRepository.findOne(id)
        beehiveActionRepository.delete(beehiveAction)
        return "redirect:/beehive/${beehiveAction.beehive.id}";
    }
}
