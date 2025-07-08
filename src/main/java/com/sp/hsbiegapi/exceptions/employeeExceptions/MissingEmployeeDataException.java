package com.sp.hsbiegapi.exceptions.employeeExceptions;

import com.sp.hsbiegapi.exceptions.AppExceptions;

public class MissingEmployeeDataException extends AppExceptions {
    public MissingEmployeeDataException(String message) {
        super(message);
    }
}
