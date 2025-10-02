import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;


     // Thực hiện Check-In cho nhân viên.

    public Attendance checkIn(Long employeeId) throws IllegalStateException {
        LocalDate today = LocalDate.now();

        // Kiểm tra xem nhân viên đã check-in hôm nay chưa
        if (attendanceRepository.findByEmployeeIdAndWorkDate(employeeId, today).isPresent()) {
            throw new IllegalStateException("Bạn đã Check-In hôm nay rồi.");
        }

        //Tạo bản ghi Check-In mới
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setWorkDate(today);
        attendance.setCheckInTime(LocalDateTime.now());

        return attendanceRepository.save(attendance);
    }


     // Thực hiện Check-Out cho nhân viên.

    public Attendance checkOut(Long employeeId) throws IllegalStateException {
        LocalDate today = LocalDate.now();

        //Tìm bản ghi Check-In của ngày hôm nay
        Attendance attendance = attendanceRepository.findByEmployeeIdAndWorkDate(employeeId, today)
                .orElseThrow(() -> new IllegalStateException("Bạn chưa Check-In hôm nay."));

        // Kiểm tra nếu đã Check-Out rồi
        if (attendance.getCheckOutTime() != null) {
            throw new IllegalStateException("Bạn đã Check-Out hôm nay rồi.");
        }

        // Cập nhật thời gian Check-Out
        attendance.setCheckOutTime(LocalDateTime.now());

        return attendanceRepository.save(attendance);
    }
}