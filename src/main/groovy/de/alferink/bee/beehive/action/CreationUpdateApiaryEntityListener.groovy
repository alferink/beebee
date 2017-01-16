package de.alferink.bee.beehive.action

import de.alferink.bee.AppContext
import de.alferink.bee.beehive.Beehive
import de.alferink.bee.beehive.BeehiveRepository

import javax.persistence.PostPersist
import javax.persistence.PostUpdate
import javax.transaction.Transactional

class CreationUpdateApiaryEntityListener {

    @PostPersist
    @PostUpdate
    @Transactional
    public void updateApiary(Creation creation) {
        Thread t = Thread.start {
            BeehiveRepository beehiveRepository = AppContext.applicationContext.getBean(BeehiveRepository)
            Beehive beehive = creation.beehive
            beehive.updateApiary()
            beehiveRepository.save(beehive)
        }
    }
}
