package de.alferink.bee.beehive

import de.alferink.bee.MessageUtils

enum BeeBreed {

    CARNICA,
    BUCKFAST,
    LIQUSTICA,
    MELLIFERA

    String getInstanceName(){
        MessageUtils.getInstanceNameMessage(this)
    }

    @Override
    String toString() {
        instanceName
    }
}