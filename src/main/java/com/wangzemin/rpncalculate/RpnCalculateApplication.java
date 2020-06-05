package com.wangzemin.rpncalculate;

import com.wangzemin.rpncalculate.calculator.Calculator;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@EnableAspectJAutoProxy
@SpringBootApplication
public class RpnCalculateApplication {

    @Autowired
    private Calculator calculator;

    public static void main(String[] args) {
        SpringApplication.run(RpnCalculateApplication.class, args);
    }

	/**
	 * 模拟主函数输入和输出, 实现比较粗糙.
	 */
	@PostConstruct
    public void inputThread() {
        Thread thread = new Thread(() -> {
            sleep(); // wait for spring boot.
            System.out.println("\nPlease Begin your input:");
            Scanner sc = new Scanner(System.in);
            while (true) {
                String input = sc.nextLine();
                calculator.calculate(input);
            }
        });
        thread.start();
    }

    @SneakyThrows
    private void sleep() {
        Thread.sleep(100);
    }

}
