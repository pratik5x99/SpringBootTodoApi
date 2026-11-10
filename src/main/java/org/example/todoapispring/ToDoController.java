package org.example.todoapispring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<TodoItem>> getTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping("/todos")
    // one way to send status code @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TodoItem> createTodo(@RequestBody TodoItem newTodoItem){
        todoList.add(newTodoItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodoItem);

    }

}
