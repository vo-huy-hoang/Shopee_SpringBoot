package org.example.shopee.repository;

import org.example.shopee.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
//    List<Comment> findCommentByContent

}
