
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Tìm kiếm bản ghi check-in trong ngày hôm nay của nhân viên
    Optional<Attendance> findByEmployeeIdAndWorkDate(Long employeeId, LocalDate workDate);

    // Tìm bản ghi check-in gần nhất chưa check-out
    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId AND a.checkOutTime IS NULL ORDER BY a.checkInTime DESC")
    Optional<Attendance> findLatestCheckInWithoutCheckout(Long employeeId);
}
