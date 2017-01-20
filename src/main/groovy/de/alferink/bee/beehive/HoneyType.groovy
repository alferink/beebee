package de.alferink.bee.beehive

import de.alferink.bee.MessageUtils

enum HoneyType {

    SPRING_HONEY,
    SUMMER_HONEY

    String getInstanceName(){
        MessageUtils.getInstanceNameMessage(this)
    }

    @Override
    String toString() {
        instanceName
    }
}