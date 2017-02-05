package de.alferink.bee.beehive.measurement

import org.springframework.data.domain.Sort
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.format.annotation.DateTimeFormat

@RepositoryRestResource(collectionResourceRel = "aggregatedBeehiveMeasurements", path = "aggregatedBeehiveMeasurement")
interface AggregatedBeehiveMeasurementRepository extends PagingAndSortingRepository<AggregatedBeehiveMeasurement, String> {

    List<AggregatedBeehiveMeasurement> findByBeehiveId(@Param('beehiveId') String beehiveId)

    List<AggregatedBeehiveMeasurement> findByBeehiveIdAndDateBetween(
            @Param('beehiveId') String beehiveId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)@Param('from') Date from, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)@Param('till') Date till, Sort sort)
}