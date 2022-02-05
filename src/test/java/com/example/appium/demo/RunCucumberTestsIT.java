package com.example.appium.demo;

import org.springframework.test.context.ContextConfiguration;

import com.example.appium.demo.config.SpringConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.example.appium.demo",
    plugin = {"pretty", "json:target/cucumber-report/cucumber.json"}
)
@CucumberContextConfiguration
@ContextConfiguration(classes = SpringConfig.class)
public class RunCucumberTestsIT extends AbstractTestNGCucumberTests {

}
