package de.alferink.bee.beehive.action

import de.alferink.bee.AppContext
import de.alferink.bee.beehive.Beehive
import de.alferink.bee.beehive.BeehiveRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import javax.persistence.PostUpdate
import javax.transaction.Transactional

@Controller
@RequestMapping(value = 'beehive/{beehiveId}/varroatreatment')
class VarroaTreatmentController extends BeehiveActionController<VarroaTreatment> {

    Class getBeehiveActionClass() {
        VarroaTreatment
    }
}
