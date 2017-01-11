package de.alferink.bee.beehive

import de.alferink.bee.apiary.ApiaryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.propertyeditors.CustomDateEditor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import javax.validation.Valid
import java.text.SimpleDateFormat

@Controller
@RequestMapping(value = '/beehive')
class BeehiveController {

    @Autowired
    BeehiveRepository beehiveRepository

    @Autowired
    ApiaryRepository apiaryRepository

    @RequestMapping(value = ["/", "index"])
    public String index(Model model) {
        model.addAttribute("beehives", beehiveRepository.findAll());
        return "beehive/index";
    }

    @RequestMapping(value = "{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("beehive", beehiveRepository.findOne(id));
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
        if (bindingResult.hasErrors()) {
            model.addAttribute("apiaries", apiaryRepository.findAll());
            return "beehive/edit";
        }
        beehiveRepository.save(beehive);
        return "redirect:index";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
