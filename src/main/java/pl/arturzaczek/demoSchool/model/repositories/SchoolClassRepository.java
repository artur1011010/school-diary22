package pl.arturzaczek.demoSchool.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arturzaczek.demoSchool.model.entities.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
}
