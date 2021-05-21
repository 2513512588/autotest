package com.runnersoftware.auto_test.service.Impl;



import com.runnersoftware.auto_test.service.ExecuteCmdService;
import org.springframework.stereotype.Service;


import java.io.*;



@Service
public class ExecuteCmdServiceImpl implements ExecuteCmdService {


    /**
     * 执行系统命令, 返回执行结果
     * @param cmd 需要执行的命令
     *
     */
    @Override
    public String execCmd(String cmd, File file) throws Exception {
        StringBuilder result = new StringBuilder();

        Process process = null;
        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;

        try {
            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = Runtime.getRuntime().exec(cmd, null, file);
            //
            //            // 方法阻塞, 等待命令执行完成（成功会返回0）
            process.waitFor();
//            process.getOutputStream().close();
            // 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
            bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GBK"));

            // 读取输出
            String line = null;
            while ((line = bufrIn.readLine()) != null) {
                result.append(line).append('\n');
            }
            while ((line = bufrError.readLine()) != null) {
                result.append(line).append('\n');
            }

        } finally {
            closeStream(bufrIn);
            closeStream(bufrError);

            // 销毁子进程
            if (process != null) {
                process.destroy();
            }
        }

        // 返回执行结果
        return result.toString();
    }

    private void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                // nothing
            }
        }
    }
}