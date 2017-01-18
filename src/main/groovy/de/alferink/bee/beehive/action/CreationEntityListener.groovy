package de.alferink.bee.beehive.action

import de.alferink.bee.AppContext
import de.alferink.bee.beehive.BeehiveService

import javax.persistence.PostPersist
import javax.persistence.PostUpdate
import javax.transaction.Transactional

class CreationEntityListener {

    @PostPersist
    @PostUpdate
    @Transactional
    public void updateApiary(Creation creation) {

        String beehiveId = creation.beehive?.id
        Thread t = Thread.start {
            if(beehiveId) {
                BeehiveService beehiveService = AppContext.applicationContext.getBean(BeehiveService)
                beehiveService.updateApiary(beehiveId)
            }
        }
    }
}
