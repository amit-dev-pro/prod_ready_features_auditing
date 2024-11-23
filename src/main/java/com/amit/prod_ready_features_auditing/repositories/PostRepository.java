package com.amit.prod_ready_features_auditing.repositories;

import com.amit.prod_ready_features_auditing.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
