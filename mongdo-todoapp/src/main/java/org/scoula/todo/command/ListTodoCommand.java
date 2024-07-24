package org.scoula.todo.command;

import org.scoula.lib.cli.command.Command;
import org.scoula.todo.dao.TodoDAO;
import org.scoula.todo.domain.Todo;

import java.util.List;

public class ListTodoCommand implements Command {
    TodoDAO dao = TodoDAO.getInstance();

    @Override
    public void execute() {
        System.out.println("Todo 목록 보기");
        List<Todo> list = dao.getList();
        for (Todo todo : list) {
            System.out.printf("[%s] %s, %s, %s",
                    todo.getId(), todo.getTitle(), todo.getDesc(), todo.isDone());

            System.out.println("");
        }
    }

}
