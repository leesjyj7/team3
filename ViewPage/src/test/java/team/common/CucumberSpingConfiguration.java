package team.common;


import team.ViewPageApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { ViewPageApplication.class })
public class CucumberSpingConfiguration {
    
}
