package example.command.grouppersons;

import example.command.grouppersons.constant.GroupConstant;
import example.command.Command;
import example.command.CommandResult;
import example.exception.IncorrectDataException;
import example.exception.ServiceException;
import example.model.Person;
import example.service.PersonService;
import example.util.pages.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class WelcomeCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectDataException {
        PersonService personService = new PersonService();
        List<Person> clients = personService.findAll();
        if (!clients.isEmpty()) {
            request.setAttribute(GroupConstant.LISTGROUP, clients);
        }
        return new CommandResult(Page.WELCOME_PAGE.getPage(), false);
    }

}
