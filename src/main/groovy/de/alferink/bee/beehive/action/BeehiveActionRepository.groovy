package de.alferink.bee.beehive.action

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "beehiveAction", path = "beehiveAction")
interface BeehiveActionRepository extends PagingAndSortingRepository<BeehiveAction, String> {

}