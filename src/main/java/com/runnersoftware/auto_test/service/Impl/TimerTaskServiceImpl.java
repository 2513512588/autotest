package com.runnersoftware.auto_test.service.Impl;

import com.runnersoftware.auto_test.model.Bugs;
import com.runnersoftware.auto_test.service.BugsService;
import com.runnersoftware.auto_test.service.TimerTaskService;
import com.runnersoftware.auto_test.utils.EmailProperties;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

import java.io.IOException;
import java.util.Properties;


@Slf4j
@Configuration
@EnableScheduling
public class TimerTaskServiceImpl implements TimerTaskService {

    @Autowired
    private BugsService bugsService;

    /**
     * 接收邮件
     */
    @Scheduled(cron="0/10 * * * * ? ")
    public void timerTaskInfo() throws MessagingException, IOException {

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3");
        props.setProperty("mail.pop3.host", "pop.qq.com");
        props.setProperty("mail.pop3.port", "110");

        // 创建Session实例对象
        Session session = Session.getInstance(props);

        // 创建pop3协议的Store对象
        Store store = session.getStore("pop3");

        // 连接邮件服务器
        store.connect(EmailProperties.USERNAME, EmailProperties.PASSWORD);

        // 获得收件箱
        Folder folder = store.getFolder("INBOX");
        // 以读写模式打开收件箱
        folder.open(Folder.READ_WRITE);

        SearchTerm sender = new FromTerm(new InternetAddress(EmailProperties.SENDER));
        Message[] msgs = folder.search(sender);
        // 得到收件箱文件夹信息，获取邮件列表
        for (int i = 0; i < msgs.length; i++) {
            Message msg = msgs[i];
            System.out.println("msg.getSubject() = " + msg.getSubject());
            String email = msg.getFrom()[0].toString();
            if (EmailProperties.SENDER.equals(email)){
                //只查看未读邮件
                if (!msg.getFlags().contains(Flags.Flag.SEEN)){
                    Document document = Jsoup.parse(msg.getContent().toString());
                    Bugs bugs = Bugs.buildDefault(document.text());
                    bugsService.insert(bugs);
                    //邮件已读
                    msg.setFlag(Flags.Flag.SEEN, true);
                }
            }

        }
        // 关闭资源
        folder.close(false);
        store.close();
    }



}
