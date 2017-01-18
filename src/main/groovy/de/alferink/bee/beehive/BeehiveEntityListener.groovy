package de.alferink.bee.beehive

import de.alferink.bee.AppContext

import javax.persistence.PostPersist
import javax.persistence.PostUpdate
import javax.transaction.Transactional

class BeehiveEntityListener {

    @PostPersist
    @PostUpdate
    @Transactional
    public void updateApiary(Beehive beehive) {

        String beehiveId = beehive?.id
        Thread t = Thread.start {
            if(beehiveId) {
                BeehiveService beehiveService = AppContext.applicationContext.getBean(BeehiveService)
                beehiveService.updateApiary(beehiveId)
            }
        }
    }
}
