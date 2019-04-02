
package com.example.WebProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.WebProject.entity.CommentRate;
import com.example.WebProject.entity.CommentRateIndentity;


public interface CommentRateRepository extends CrudRepository<CommentRate, CommentRateIndentity> {
}
