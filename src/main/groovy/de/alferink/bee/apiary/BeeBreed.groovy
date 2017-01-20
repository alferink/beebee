package de.alferink.bee.apiary

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