package de.alferink.bee.apiary

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "apiary", path = "apiary")
interface ApiaryRepository extends PagingAndSortingRepository<Apiary, String> {

    List<Apiary> findByName(@Param('name') String name)
}