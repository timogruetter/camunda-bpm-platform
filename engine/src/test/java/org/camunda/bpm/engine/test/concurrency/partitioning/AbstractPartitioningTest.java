/*
 * Copyright © 2013-2018 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.test.concurrency.partitioning;

import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.RequiredHistoryLevel;
import org.camunda.bpm.engine.test.concurrency.ConcurrencyTestCase;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;

import java.util.Map;

/**
 * @author Tassilo Weidner
 */

@RequiredHistoryLevel(ProcessEngineConfiguration.HISTORY_FULL)
public abstract class AbstractPartitioningTest extends ConcurrencyTestCase {

  protected CommandExecutor commandExecutor;

  protected void setUp() throws Exception {
    super.setUp();

    this.commandExecutor = processEngineConfiguration.getCommandExecutorTxRequired();

    processEngine.getProcessEngineConfiguration().setSkipHistoryOptimisticLockingExceptions(true);
  }

  final protected BpmnModelInstance PROCESS_WITH_USERTASK = Bpmn.createExecutableProcess("process")
    .startEvent()
      .userTask()
    .endEvent().done();

  protected ProcessInstance deployAndStartProcess(BpmnModelInstance bpmnModelInstance) {
    return deployAndStartProcess(bpmnModelInstance, null);
  }

  protected ProcessInstance deployAndStartProcess(BpmnModelInstance bpmnModelInstance, Map<String, Object> variablesMap) {
    deploymentId = repositoryService.createDeployment()
      .addModelInstance("process.bpmn", bpmnModelInstance)
      .deploy().getId();

    String processDefinitionKey = bpmnModelInstance.getDefinitions().getRootElements().iterator().next().getId();
    return runtimeService.startProcessInstanceByKey(processDefinitionKey, variablesMap);
  }

}
