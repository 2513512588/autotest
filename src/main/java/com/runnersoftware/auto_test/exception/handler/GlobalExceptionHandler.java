package com.runnersoftware.auto_test.exception.handler;

import com.runnersoftware.auto_test.exception.AutoTestException;
import com.runnersoftware.auto_test.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AutoTestException.class)
    public R error(AutoTestException e) {
        log.debug("操作失败! ====> " + e.getMessage());
        return R.error().message(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("系统出现问题");
    }

}
