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
package org.camunda.bpm.engine.rest.dto.task;

import org.camunda.bpm.engine.rest.dto.LinkableDto;
import org.camunda.bpm.engine.task.Comment;

import java.util.Date;

public class CommentDto extends LinkableDto {

  private String id;
  private String userId;
  private Date time;
  private String taskId;
  private String message;
  private Date removalTime;
  private String rootProcessInstanceId;

  public CommentDto() {
  }

  public String getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getRemovalTime() {
    return removalTime;
  }

  public void setRemovalTime(Date removalDate) {
    this.removalTime = removalDate;
  }

  public String getRootProcessInstanceId() {
    return rootProcessInstanceId;
  }

  public void setRootProcessInstanceId(String rootProcessInstanceId) {
    this.rootProcessInstanceId = rootProcessInstanceId;
  }

  public static CommentDto fromComment(Comment comment) {
    CommentDto dto = new CommentDto();
    dto.id = comment.getId();
    dto.userId = comment.getUserId();
    dto.time = comment.getTime();
    dto.taskId = comment.getTaskId();
    dto.message = comment.getFullMessage();
    dto.removalTime = comment.getRemovalTime();
    dto.rootProcessInstanceId = comment.getRootProcessInstanceId();

    return dto;
  }
}
