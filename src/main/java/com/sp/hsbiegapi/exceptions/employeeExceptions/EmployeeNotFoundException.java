package com.sp.hsbiegapi.exceptions.employeeExceptions;

import com.sp.hsbiegapi.exceptions.AppExceptions;

public class EmployeeNotFoundException extends AppExceptions {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
