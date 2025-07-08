package com.sp.hsbiegapi.repositories.locatiRepositories;

import com.sp.hsbiegapi.models.locModels.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData,Long> {
}
