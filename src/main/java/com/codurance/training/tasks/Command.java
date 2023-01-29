package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Command{
    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final PrintWriter output;

    private long lastId = 0;

    void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            output.println(project.getKey());
            for (Task task : project.getValue()) {
                output.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            output.println();
        }
    }
    void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }
    private void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }
    private void addTask(String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            output.printf("Could not find a project with the name \"%s\".", project);
            output.println();
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }

    void check(String idString) {
        setDone(idString, true);
    }
    void uncheck(String idString) {
        setDone(idString, false);
    }
    private void setDone(String idString, boolean done) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        output.printf("Could not find a task with an ID of %d.", id);
        output.println();
    }

    void help() {
        output.println("Commands:");
        output.println("  show");
        output.println("  add project <project name>");
        output.println("  add task <project name> <task description>");
        output.println("  check <task ID>");
        output.println("  uncheck <task ID>");
        output.println();
    }
    void error(String command) {
        output.printf("I don't know what the command \"%s\" is.", command);
        output.println();
    }
    private long nextId() {
        return ++lastId;
    }
}