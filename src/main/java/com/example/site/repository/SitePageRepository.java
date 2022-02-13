package com.example.site.repository;

import com.example.site.entity.SitePage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitePageRepository extends CrudRepository<SitePage, Long> {
}
