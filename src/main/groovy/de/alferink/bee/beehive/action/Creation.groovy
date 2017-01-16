package de.alferink.bee.beehive.action

import de.alferink.bee.BaseEntity
import de.alferink.bee.apiary.Apiary
import de.alferink.bee.beehive.Beehive

import groovy.transform.CompileStatic
import org.hibernate.validator.constraints.Length

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Transient
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past

@CompileStatic
@Entity
@EntityListeners(CreationUpdateApiaryEntityListener)
class Creation extends BaseEntity implements BeehiveAction {

    Creation() {
        rating = new Rating(enabled: false)
        addRemove = new AddRemove(enabled: false)
    }

    @Past
    @NotNull
    Date date

    @Length(max = 4000)
    String notes

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="rating_id")
    Rating rating

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="addremove_id")
    AddRemove addRemove

    Boolean koeniginGesehen
    Boolean weiselrichtig

    @OneToOne(mappedBy = "creationAction")
    Beehive beehive

    @ManyToOne(fetch = FetchType.EAGER)
    Apiary apiary

    @Override
    @Transient
    BeehiveActionType getActionType() {
        BeehiveActionType.CREATION
    }
}
