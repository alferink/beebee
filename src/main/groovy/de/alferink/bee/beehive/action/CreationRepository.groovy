package de.alferink.bee.beehive.action

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@Repository
interface CreationRepository extends PagingAndSortingRepository<Creation, String> {

}