package de.alferink.bee.beehive

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BeehiveService {

    @Autowired
    BeehiveRepository beehiveRepository

    void updateApiary(String beehiveId) {
        Beehive beehive = beehiveRepository.findOne(beehiveId)
        beehive.updateApiary()
        beehiveRepository.save(beehive)
    }
}
