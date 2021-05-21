package com.runnersoftware.auto_test.service;


import java.io.File;

public interface ExecuteCmdService {

    String execCmd(String cmd, File file) throws Exception;

}
