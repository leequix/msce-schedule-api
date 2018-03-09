package xyz.leequix.mscescheduleapi.mscescheduleapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import xyz.leequix.mscescheduleapi.mscescheduleapi.service.parser.ScheduleParserService;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class MSCEScheduleApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MSCEScheduleApiApplication.class, args);
	}
}
