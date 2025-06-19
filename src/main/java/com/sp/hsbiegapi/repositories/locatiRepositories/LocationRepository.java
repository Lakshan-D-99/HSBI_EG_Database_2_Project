package com.sp.hsbiegapi.repositories.locatiRepositories;

import com.sp.hsbiegapi.models.locModels.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
}
