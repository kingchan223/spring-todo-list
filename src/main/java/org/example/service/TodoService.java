package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    //todo리스트에 아이템 추가
    public TodoEntity add(TodoRequest request){
        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setOrder(request.getOrder());
        todoEntity.setTitle(request.getTitle());
        todoEntity.setCompleted(request.getCompleted());

        return this.todoRepository.save(todoEntity);
    }
    
    //todo리스트   목록중 특정 아이템 조회
    public TodoEntity searchById(Long id){
        return this.todoRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException((HttpStatus.NOT_FOUND)));
    }

    //todo리스트  전체 목록 조회
    public List<TodoEntity> searchAll(){
        return todoRepository.findAll();
    }

    //todo리스트에 특정 아이템 수정
    public TodoEntity update(Long id, TodoRequest request){
        TodoEntity todoEntity = this.searchById(id);
        if(request.getTitle()!=null){
            todoEntity.setTitle(request.getTitle());
        }
        if(request.getOrder()!=null){
            todoEntity.setOrder(request.getOrder());
        }
        if(request.getCompleted()!=null){
            todoEntity.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoEntity);
    }

    //todo리스트에 특정 아이템 삭제
    public void delete(Long id){
       todoRepository.deleteById(id);
    }

    //todo리스트   전체 목록 삭제
    public void deleteAll(){
        todoRepository.deleteAll();
    }
}
