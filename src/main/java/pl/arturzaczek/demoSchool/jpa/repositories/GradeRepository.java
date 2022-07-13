package pl.arturzaczek.demoSchool.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arturzaczek.demoSchool.jpa.entities.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
