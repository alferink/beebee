package de.alferink.bee.beehive.action

import de.alferink.bee.BaseEntity
import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.Transient
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@CompileStatic
@Entity
class Rating extends BaseEntity {

    Boolean enabled

    // allgemeiner Befund
    @Min(0L)
    @Max(4L)
    Integer egg // Ei

    @Min(0L)
    @Max(4L)
    Integer larva // Made

    @Min(0L)
    @Max(4L)
    Integer cellCapped // verdeckelt

    @Min(0L)
    @Max(4L)
    Integer wabensitz

    @Min(0L)
    @Max(4L)
    Integer sanftmut

    @Min(0L)
    @Max(4L)
    Integer volksstaerke
}
