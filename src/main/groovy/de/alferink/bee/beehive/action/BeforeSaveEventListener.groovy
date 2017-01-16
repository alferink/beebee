package de.alferink.bee.beehive.action

import de.alferink.bee.beehive.Beehive
import de.alferink.bee.beehive.BeehiveRepository
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener
import org.springframework.stereotype.Component

// http://docs.spring.io/spring-data/rest/docs/2.5.6.RELEASE/reference/html/#events.application-listener
@Component
@RepositoryEventHandler
class BeforeSaveEventListener extends AbstractRepositoryEventListener<Creation> {

    BeehiveRepository beehiveRepository

    public void onAfterCreate(Creation creation) {
        Beehive beehive = creation.beehive
        beehive.updateApiary()
        beehiveRepository.save(beehive)

    }
}
