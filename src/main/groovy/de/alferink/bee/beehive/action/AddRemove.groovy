package de.alferink.bee.beehive.action

import de.alferink.bee.BaseEntity
import groovy.transform.CompileStatic

import javax.persistence.Entity

@CompileStatic
@Entity
class AddRemove extends BaseEntity {

    Boolean enabled

    // gegeben genommen
    Integer broodChamberAdded
    Integer honeyChamberAdded
    Integer mittelwaendeAdded
    Integer leerwabenAdded
    Integer brutwabenAdded
    Integer futterwabenAdded
    Integer drohnenwabenAdded

    Integer broodChamberRemoved
    Integer honeyChamberRemoved
    Integer mittelwaendeRemoved
    Integer leerwabenRemoved
    Integer brutwabenRemoved
    Integer futterwabenRemoved
    Integer drohnenwabenRemoved
}
