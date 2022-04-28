package com.example.todolist;

public class Task_properties {
    private String Task_Name;
    private boolean checked;

    public String getTask_Name() {
        return Task_Name;
    }

    public void setTask_Name(String task_Name) {
        Task_Name = task_Name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Task_properties(String task_Name, boolean checked) {
        Task_Name = task_Name;
        this.checked = checked;
    }
}
