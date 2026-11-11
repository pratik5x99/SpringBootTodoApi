package org.example.todoapispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDoController {



    private TodoService todoService;

    private static List<TodoItem> todoList;

    public ToDoController(@Qualifier("anotherTodoService") TodoService todoService) {
        this.todoService = todoService;
        todoList = new ArrayList<>();
        todoList.add(new TodoItem(1, false, "Item 1", 1));
        todoList.add(new TodoItem(2, true, "Item 2", 2));

    }


    @GetMapping
    public ResponseEntity<List<TodoItem>> getTodos(@RequestParam(required = false) Boolean isCompleted){
        System.out.println("Incoming request params: " + isCompleted + " " + todoService.doSomething());
        return ResponseEntity.ok(todoList);
    }

    @PostMapping
    // one way to send status code @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TodoItem> createTodo(@RequestBody TodoItem newTodoItem){
        todoList.add(newTodoItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodoItem);

    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodo(@PathVariable("todoId") Long todoId){

        for(TodoItem todoItem : todoList){
            if(todoId == todoItem.getId()){
                return ResponseEntity.ok(todoItem);
            }
        }
        // along with 404 status code, try to send a json (message: todo not found)

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo Not Found");
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable("todoId") Long todoId){
        for(TodoItem todoItem : todoList){
            if(todoId == todoItem.getId()){
                todoList.remove(todoItem);
                return ResponseEntity.status(HttpStatus.OK).body(todoList);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo Not Found");
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable("todoId") Long todoId, @RequestParam(required = false) Boolean completed, @RequestParam(required = false) String title, @RequestParam(required = false) Integer userId){
        for(TodoItem todoItem : todoList){
            if(todoId == todoItem.getId()){
                if(completed!=null){
                    todoItem.setCompleted(completed);
                }
                if(title!=null){
                    todoItem.setTitle(title);
                }
                if(userId!=null){
                    todoItem.setUserId(userId);
                }
                return ResponseEntity.status(HttpStatus.OK).body(todoList);
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo Not Found");
    }
}
