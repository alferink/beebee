package de.alferink.bee.beehive.statistic

import de.alferink.bee.beehive.Beehive
import de.alferink.bee.beehive.action.BeehiveActionType
import org.springframework.stereotype.Service

@Service
class BeehiveStateService {

    BeehiveState createBeehiveState(Beehive beehive, Date date) {

        BeehiveState beehiveState = new BeehiveState()
        def actions = beehive.allBeehiveActions.findAll {
            it.date <= date
        }

        beehiveState.broodChamber = actions.findAll {
            it.addRemove?.enabled
        }.sum {
            (it.addRemove.broodChamberAdded ?: 0) - (it.addRemove.broodChamberRemoved ?: 0)
        }
        beehiveState.honeyChamber = actions.findAll {
            it.addRemove?.enabled
        }.sum {
            (it.addRemove.honeyChamberAdded ?: 0) -(it.addRemove.honeyChamberRemoved ?: 0)
        }

        beehiveState.queen = actions.findAll {
            it.actionType == BeehiveActionType.QUEEN_INSTALLATION
        }.max {
            it.date
        }?.queen

        beehiveState
    }
}
