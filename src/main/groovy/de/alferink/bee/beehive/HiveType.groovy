package de.alferink.bee.beehive

import de.alferink.bee.MessageUtils

enum HiveType {
    SEGEBERGER_BEUTE,
    LIEBIG_BEUTE_12_DN

    String getInstanceName(){
        MessageUtils.getInstanceNameMessage(this)
    }

    @Override
    String toString() {
        instanceName
    }
}