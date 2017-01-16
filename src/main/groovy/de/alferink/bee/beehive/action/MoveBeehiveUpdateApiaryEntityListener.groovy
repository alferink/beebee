package de.alferink.bee.beehive.action

import de.alferink.bee.AppContext
import de.alferink.bee.beehive.Beehive
import de.alferink.bee.beehive.BeehiveRepository

import javax.persistence.PostPersist
import javax.persistence.PostUpdate
import javax.transaction.Transactional

class MoveBeehiveUpdateApiaryEntityListener {

    @PostPersist
    @PostUpdate
    @Transactional
    public void updateApiary(MoveBeehive moveBeehive) {
        Thread t = Thread.start {
            BeehiveRepository beehiveRepository = AppContext.applicationContext.getBean(BeehiveRepository)
            Beehive beehive = moveBeehive.beehive
            beehive.updateApiary()
            beehiveRepository.save(beehive)
        }
    }
}
