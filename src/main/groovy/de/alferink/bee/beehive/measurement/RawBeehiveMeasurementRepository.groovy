package de.alferink.bee.beehive.measurement

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.format.annotation.DateTimeFormat

@RepositoryRestResource(collectionResourceRel = "rawBeehiveMeasurements", path = "rawBeehiveMeasurement")
interface RawBeehiveMeasurementRepository extends PagingAndSortingRepository<RawBeehiveMeasurement, String> {

    List<RawBeehiveMeasurement> findByBeehiveId(@Param('beehiveId') String beehiveId)
}