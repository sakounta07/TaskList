package com.codurance.training.tasks;

public final class Task {
    private Id id;
    private Description description;
    private Done done;

    public Task(long id, String description, boolean done) {
        this.id = getId();
        this.description = getDescription();
        this.done = getDone();

        public Id getId() {
        return id;
    }
     public Description getDescription() {
        return description;
    }
    public Done isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    }

}
