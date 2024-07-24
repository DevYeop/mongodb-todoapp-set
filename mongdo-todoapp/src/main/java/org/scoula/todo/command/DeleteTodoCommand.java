package org.scoula.todo.command;

import org.scoula.lib.cli.command.Command;
import org.scoula.lib.cli.ui.Input;
import org.scoula.todo.dao.TodoDAO;

public class DeleteTodoCommand implements Command {
    TodoDAO dao = TodoDAO.getInstance();

    @Override
    public void execute() {
        System.out.println("Todo 삭제");
        String id = Input.getLine("삭제할 Todo ID: ");
        dao.delete(id);
    }
}
