package pl.arturzaczek.demoSchool.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arturzaczek.demoSchool.jpa.entities.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
}
