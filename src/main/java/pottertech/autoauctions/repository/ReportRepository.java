package pottertech.autoauctions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pottertech.autoauctions.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findOneById(Long id);
}
