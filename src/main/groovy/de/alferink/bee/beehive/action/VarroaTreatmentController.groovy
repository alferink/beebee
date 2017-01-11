package de.alferink.bee.beehive.action

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value = 'beehive/{beehiveId}/varroatreatment')
class VarroaTreatmentController extends BeehiveActionController<VarroaTreatment> {

    Class getBeehiveActionClass() {
        VarroaTreatment
    }
}
