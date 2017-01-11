package de.alferink.bee.beehive.action

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value = 'beehive/{beehiveId}/varroacheck')
class VarroaCheckController extends BeehiveActionController<VarroaCheck> {

    Class getBeehiveActionClass() {
        VarroaCheck
    }
}
