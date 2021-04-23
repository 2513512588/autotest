package com.runnersoftware.auto_test.service;

public interface TestService {



    Boolean comile(Object expect, String code, Object...args) throws Exception;
}
