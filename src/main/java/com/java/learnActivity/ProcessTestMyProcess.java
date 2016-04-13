package com.java.learnActivity;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.explorer.conf.ActivitiEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ActivitiEngineConfiguration.class })
public class ProcessTestMyProcess {
	private String filename = "C:/Users/Administrator/Desktop/BookOrderProcess.bpmn20.xml";

	// @Rule
	// public ActivitiRule activitiRule = new ActivitiRule();

	@Autowired
	public RepositoryService repositoryService;

	@Autowired
	public RuntimeService runtimeService;

	@Test
	public void startProcess() throws FileNotFoundException {
		repositoryService.createDeployment().addInputStream("process.bpmn20.xml", new FileInputStream(filename)).deploy();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("BookOrderProcess", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " " + processInstance.getProcessDefinitionId());
	}
}
