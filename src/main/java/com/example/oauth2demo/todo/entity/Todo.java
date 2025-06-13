package com.example.oauth2demo.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Todo {

    /**
     * Default constructor for JPA.
     */
    public Todo() {
    }

    public Todo(String note) {
        this.note = note;
    }

    public Todo(String note, boolean completed) {
        this.note = note;
        this.completed = completed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String note;
    private boolean completed;

    @PrePersist
    private void prePersist() {
        // No need to check for null, as 'completed' is primitive boolean and defaults to false
    }

    @Override
    public String toString() {
        return "Todo{id=" + id + ", note='" + note + "', completed=" + completed + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Todo todo = (Todo) o;
        if (id == null || todo.id == null)
            return false;
        return id.equals(todo.id);
    }

    /**
     * Returns the hash code based on the id.
     * If id is null (transient object), returns 0.
     * This is consistent with equals.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Gets the ID of the Todo.
     * 
     * @return the ID of the Todo
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the Todo.
     * 
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the note of the Todo.
     * 
     * @return the note of the Todo
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note of the Todo.
     * 
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }
    /**
     * Checks if the Todo is completed.
     * 
     * @return true if the Todo is completed, false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completed status of the Todo.
     * 
     * @param completed the completed status to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
