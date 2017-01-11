package de.alferink.bee.beehive

import de.alferink.bee.BaseEntity
import groovy.transform.CompileStatic
import org.hibernate.annotations.GenericGenerator

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@CompileStatic
@Entity
class Queen extends BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id

    Integer year

    QueenColor color
    BeeBreed breed

    String zeichen
    String belegstelle
}
