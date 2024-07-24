package org.scoula.todo.command;

import org.scoula.lib.cli.command.Command;
import org.scoula.lib.cli.ui.Input;
import org.scoula.todo.dao.TodoDAO;
import org.scoula.todo.domain.Todo;

public class UpdateTodoCommand implements Command {
    TodoDAO dao = TodoDAO.getInstance();

    @Override
    public void execute() {
        System.out.println("Todo 수정");
        String id = Input.read("수정할 Todo ID: ");
        Todo todo = dao.get(id);
        String title = Input.read("제목", todo.getTitle());
        todo.setTitle(title);
        String desc = Input.read("설명", todo.getDesc());
        todo.setDesc(desc);
//        boolean done = Input.read("설명", String.valueOf(todo.isDone())).equalsIgnoreCase("true");
        boolean done = Input.read("완료여부", String.valueOf(todo.isDone())).equalsIgnoreCase("true");
        todo.setDone(done);
        dao.update(todo);
    }

}
