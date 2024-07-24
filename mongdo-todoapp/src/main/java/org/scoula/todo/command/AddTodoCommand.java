package org.scoula.todo.command;

import org.scoula.lib.cli.command.Command;
import org.scoula.lib.cli.ui.Input;
import org.scoula.todo.dao.TodoDAO;
import org.scoula.todo.domain.Todo;

public class AddTodoCommand implements Command {
    TodoDAO dao = TodoDAO.getInstance();

    @Override
    public void execute() {

        System.out.println("Todo 추가");
        String title = Input.getLine("제목: ");
        String desc = Input.getLine("설명: ");
        Todo todo = Todo.builder()
                .title(title)
                .desc(desc)
                .build();
        dao.add(todo);
    }
}
