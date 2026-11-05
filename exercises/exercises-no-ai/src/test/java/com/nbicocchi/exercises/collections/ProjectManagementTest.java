package com.nbicocchi.exercises.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProjectManagementTest {
    Project project;
    Task task;
    Employee employee;

    @BeforeEach
    void setup() {
        project = new Project("Archangel 12", LocalDate.of(1962, 3, 4), LocalDate.of(1964, 7, 4));
        task = new Task("Titanium alloy testing", 2000);
        employee = new Employee("John", "Doe", "ABC-12345-XX");
    }

    @Test
    void newProjectMustHaveEmptySetOfTasks() {
        assertNotNull(project.getTasks());
    }

    @Test
    void newTaskMustHaveUUID() {
        assertNotNull(task.getUuid());
    }

    @Test
    void newTaskMustHaveProjectAndTeamUnassigned() {
        assertNull(task.getProject());
        assertNull(task.getTeamLeader());
    }

    @Test
    void addingTaskKeepsObjectModelOK() {
        project.addTask(task);
        assertTrue(project.getTasks().contains(task));
        assertEquals(task.getProject(), project);
    }

    @Test
    void settingProjectKeepsObjectModelOK() {
        task.setProject(project);
        assertTrue(project.getTasks().contains(task));
        assertEquals(task.getProject(), project);
    }

    @Test
    void removingTaskKeepsObjectModelOK() {
        project.removeTask(task);
        assertFalse(project.getTasks().contains(task));
        assertNull(task.getProject());
    }

    @Test
    void unsettingProjectKeepsObjectModelOK() {
        Project sr71 = new Project("SR71", LocalDate.of(1962, 3, 4), LocalDate.of(1964, 7, 4));
        task.setProject(project);
        task.setProject(sr71);
        assertFalse(project.getTasks().contains(task));
        assertTrue(sr71.getTasks().contains(task));
        assertEquals(task.getProject(), sr71);
    }

    @Test
    void settingTaskTeamLeaderOK() {
        task.setTeamLeader(employee);
        assertEquals(employee, task.getTeamLeader());
    }
}