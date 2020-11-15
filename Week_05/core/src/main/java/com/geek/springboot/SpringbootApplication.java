package com.geek.springboot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner, ApplicationContextAware {
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(String.join("\r\n" , applicationContext.getBeanDefinitionNames()));
        jdbcDemo();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;

    private void jdbcDemo() {
        //开启事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try{
            template.query("SHOW PROCESSLIST" , rs -> {
                if (rs.next()) {
                    System.out.println("id:" + rs.getString(1) + ",user:" + rs.getString(2) + ",host:" + rs.getString(3)
                            + ",db:" + rs.getString(4) + ",command:" + rs.getString(5) + ",time:" + rs.getString(6) + ",state:" + rs.getString(7));
                }
            });
            //提交事务
            dataSourceTransactionManager.commit(transactionStatus);
        }catch(Exception e){
            //回滚事务
            dataSourceTransactionManager.rollback(transactionStatus);
        }
    }
}
