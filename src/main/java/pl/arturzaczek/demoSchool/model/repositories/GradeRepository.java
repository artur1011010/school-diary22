package pl.arturzaczek.demoSchool.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arturzaczek.demoSchool.model.entities.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
