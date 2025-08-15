/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.usingqueryannotation.repositories;

import com.codingnomads.springdata.example.dml.usingqueryannotation.models.SoilType;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoilTypeRepo extends JpaRepository<SoilType, Long> {

    @Query("SELECT st FROM SoilType st WHERE st.name = :name")
    List<SoilType> getSoilTypeByName(@Param("name") String name);

    @Query("SELECT st FROM SoilType st WHERE st.dry = ?1")
    List<SoilType> getSoilTypesIsDry(boolean dry);

    @Query("SELECT st FROM SoilType st WHERE st.dry = true")
    List<SoilType> getDrySoilTypesSorted(Sort sort);

    @Query("SELECT st FROM SoilType st")
    Page<SoilType> getPagedSoilTypes(Pageable pageable);

    @Query(
            value = "SELECT st FROM SoilType st " +
                    "WHERE st.ph <= :phUpperBound AND st.ph >= :phLowerBound",
            countQuery = "SELECT count(st.id) FROM SoilType st WHERE st.ph <= :phUpperBound AND st.ph >= :phLowerBound"
    )
    Page<SoilType> getPagedSoilTypeWithPhInRange(
            @Param("phLowerBound") double phLowerBound,
            @Param("phUpperBound") double phUpperBound, Pageable pageable);
}
