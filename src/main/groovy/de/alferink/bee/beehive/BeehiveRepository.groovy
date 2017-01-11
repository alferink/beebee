package de.alferink.bee.beehive

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "beehive", path = "beehive")
interface BeehiveRepository extends PagingAndSortingRepository<Beehive, String> {

    List<Beehive> findByName(@Param('name') String name)
}