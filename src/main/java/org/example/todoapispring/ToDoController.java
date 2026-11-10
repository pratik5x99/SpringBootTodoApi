package org.example.todoapispring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDoController {
    private static List<TodoItem> todoList;
    public ToDoController() {
        todoList = new ArrayList<>();
        todoList.add(new TodoItem(1, false, "Item 1", 1));
        todoList.add(new TodoItem(2, true, "Item 2", 2));
    }

    @GetMapping
    public ResponseEntity<List<TodoItem>> getTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping
    // one way to send status code @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TodoItem> createTodo(@RequestBody TodoItem newTodoItem){
        todoList.add(newTodoItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodoItem);

    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoItem> getTodo(@PathVariable("todoId") Long todoId){

        for(TodoItem todoItem : todoList){
            if(todoId == todoItem.getId()){
                return ResponseEntity.ok(todoItem);
            }
        }
        // along with 404 status code, try to send a json (message: todo not found)
        return ResponseEntity.notFound().build();
    }

}
