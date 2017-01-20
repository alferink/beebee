package de.alferink.bee.beehive

import de.alferink.bee.MessageUtils

enum BeeFeed {
    FUTTERSIRUP,
    FUTTERTEIG,
    ZUCKER_3_2,
    ZUCKER_1_1,
    EIWEISSFUTTERTEIG

    String getInstanceName(){
        MessageUtils.getInstanceNameMessage(this)
    }

    @Override
    String toString() {
        instanceName
    }
}