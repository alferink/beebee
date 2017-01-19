package de.alferink.bee.beehive.action

import de.alferink.bee.beehive.Beehive

interface BeehiveAction {

    Date getDate()
    String getNotes()
    Rating getRating()
    AddRemove getAddRemove()
    Beehive getBeehive()

    BeehiveActionType getActionType()
    Integer getYear()
}