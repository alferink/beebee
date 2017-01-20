package de.alferink.bee.beehive

import de.alferink.bee.MessageUtils

enum QueenColor {
    WHITE,
    YELLOW,
    RED,
    GREEN,
    BLUE,
    NONE

    String getInstanceName(){
        MessageUtils.getInstanceNameMessage(this)
    }

    @Override
    String toString() {
        instanceName
    }
}