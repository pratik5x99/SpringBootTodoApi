package org.example.todoapispring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {
    private static List<TodoItem> todoList;
    public ToDoController() {
        todoList = new ArrayList<>();
        todoList.add(new TodoItem(1, false, "Item 1", 1));
        todoList.add(new TodoItem(2, true, "Item 2", 2));
    }

    @GetMapping("/todos")
    public List<TodoItem> getTodos(){
        return todoList;
    }

    @PostMapping("/todos")
    public TodoItem createTodo(@RequestBody TodoItem newTodoItem){

        System.out.println("Controller reached");

        System.out.println("ID: " + newTodoItem.getId());
        System.out.println("Completed: " + newTodoItem.isCompleted());
        System.out.println("Title: " + newTodoItem.getTitle());
        System.out.println("User ID: " + newTodoItem.getUserId());

        todoList.add(newTodoItem);

        System.out.println("Todo added successfully");

        return newTodoItem;

    }

}
