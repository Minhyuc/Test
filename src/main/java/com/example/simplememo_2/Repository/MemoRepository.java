package com.example.simplememo_2.Repository;

import com.example.simplememo_2.Entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
