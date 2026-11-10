package org.example.todoapispring;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {
    private static List<TodoItem> todos;
    public ToDoController() {
        todos = new ArrayList<>();
        todos.add(new TodoItem(1, false, "Item 1", 1));
        todos.add(new TodoItem(2, true, "Item 2", 2));
    }



}
