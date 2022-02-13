package com.example.admin.repository;

import com.example.admin.entity.AdminPage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPageRepository extends CrudRepository<AdminPage, Long> {
}
