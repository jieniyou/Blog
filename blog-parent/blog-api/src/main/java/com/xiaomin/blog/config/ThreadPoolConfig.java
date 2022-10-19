package com.xiaomin.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/22 23:41
 */
@Configuration
@EnableAsync //�������߳�
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // ���ú����߳���
        executor.setCorePoolSize(5);
        // ��������߳���
        executor.setMaxPoolSize(20);
        //���ö��д�С
        executor.setQueueCapacity(Integer.MAX_VALUE);
        // �����̻߳�Ծʱ�䣨�룩
        executor.setKeepAliveSeconds(60);
        // ����Ĭ���߳�����
        executor.setThreadNamePrefix("�����ǲ�����Ŀ");
        // �ȴ���������������ٹر��̳߳�
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //ִ�г�ʼ��
        executor.initialize();
        return executor;
    }
}