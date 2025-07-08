package com.sp.hsbiegapi.exceptions.employeeExceptions;

import com.sp.hsbiegapi.exceptions.AppExceptions;

public class EmployeeAlreadyExistsException extends AppExceptions {
    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
