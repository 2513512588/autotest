package com.runnersoftware.auto_test.service.Impl;

import com.runnersoftware.auto_test.service.TimerTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.*;

import java.util.Properties;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

@Slf4j
@Configuration
@EnableScheduling
public class TimerTaskServiceImpl implements TimerTaskService {

    /**
     * 接收邮件
     */
    @Scheduled(cron="0/10 * * * * ? ")
    public void timerTaskInfo() throws MessagingException {

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3");
        props.setProperty("mail.pop3.host", "pop.qq.com");
        props.setProperty("mail.pop3.port", "110");

        // 创建Session实例对象
        Session session = Session.getInstance(props);

        // 创建pop3协议的Store对象
        Store store = session.getStore("pop3");

        // 连接邮件服务器
        store.connect("2513512588@qq.com", "pgkyvgtdqxcedjih");

        // 获得收件箱
        Folder folder = store.getFolder("INBOX");
        // 以读写模式打开收件箱
        folder.open(Folder.READ_WRITE);

        // 获得收件箱的邮件列表
        Message[] messages = folder.getMessages();

        // 打印不同状态的邮件数量
        System.out.println("收件箱中共" + messages.length + "封邮件!");
        System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");
        System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");
        System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");

        System.out.println("------------------------开始解析邮件----------------------------------");


        int total = folder.getMessageCount();
        System.out.println("-----------------您的邮箱共有邮件：" + total + " 封--------------");
        // 得到收件箱文件夹信息，获取邮件列表
        Message[] msgs = folder.getMessages();
        System.out.println("\t收件箱的总邮件数：" + msgs.length);
        for (int i = 0; i < total; i++) {
            Message a = msgs[i];
            //   获取邮箱邮件名字及时间
            Address[] replyTo = a.getReplyTo();
            Pattern compile = compile("<?<=\\[><\\S+><?=\\]>");
            System.out.println("replyTo = " + replyTo[replyTo.length - 1].toString());
            System.out.println("compile= " + compile.matcher(replyTo[replyTo.length - 1].toString()));
            System.out.println("==============");

        }
        System.out.println("\t未读邮件数：" + folder.getUnreadMessageCount());
        System.out.println("\t新邮件数：" + folder.getNewMessageCount());
        System.out.println("----------------End------------------");

        // 关闭资源
        folder.close(false);
        store.close();
    }

}
