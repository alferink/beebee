package de.alferink.bee.beehive.action

import de.alferink.bee.BaseEntity
import de.alferink.bee.beehive.Beehive
import groovy.transform.CompileStatic
import org.hibernate.validator.constraints.Length

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past
import java.time.ZoneId

@CompileStatic
@Entity
abstract class ChangeBeehiveAction extends BaseEntity implements BeehiveAction {

    ChangeBeehiveAction() {
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

    @ManyToOne
    Beehive beehive

    @Transient
    abstract BeehiveActionType getActionType()

    @Transient
    Integer getYear() {
        date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().year
    }
}
