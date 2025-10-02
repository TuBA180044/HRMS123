package com.yourproject.hr.controller;

import com.yourproject.hr.entity.Attendance;
import com.yourproject.hr.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // lấy employeeId từ thông tin người dùng đang login (Security Context)
    private Long getCurrentEmployeeId() {
        //nơi sẽ tích hợp với Spring Security để lấy ID nhân viên đang login
        return 1L; //lấy ID thực tế
    }

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn() {
        try {
            Long employeeId = getCurrentEmployeeId();
            Attendance attendance = attendanceService.checkIn(employeeId);
            return ResponseEntity.ok(attendance);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/check-out")
    public ResponseEntity<?> checkOut() {
        try {
            Long employeeId = getCurrentEmployeeId();
            Attendance attendance = attendanceService.checkOut(employeeId);
            return ResponseEntity.ok(attendance);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}