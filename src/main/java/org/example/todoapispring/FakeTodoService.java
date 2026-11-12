package org.example.todoapispring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("fakeTodoService")
public class FakeTodoService implements TodoService {

    @Override
    @TimeMonitor
    public String doSomething(){

        for(int i=0;i<1e9;i++){}
        return "Something";
    }
}
