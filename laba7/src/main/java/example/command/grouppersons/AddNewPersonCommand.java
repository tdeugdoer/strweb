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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.apache.logging.log4j.util.Strings.isEmpty;

public class AddNewPersonCommand  implements Command {
    private static final Logger logger = LogManager.getLogger(AddNewPersonCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectDataException {
        PersonService personService = new PersonService();

        Optional<String> newName = of(request).map(httpServletRequest -> httpServletRequest.getParameter(GroupConstant.NEWNAME));
        Optional<String> newPhone = of(request).map(httpServletRequest -> httpServletRequest.getParameter(GroupConstant.NEWPHONE));
        Optional<String> newEmail = of(request).map(httpServletRequest -> httpServletRequest.getParameter(GroupConstant.NEWEMAIL));
        if (isEmpty(newName.get()) || isEmpty(newPhone.get()) || isEmpty(newEmail.get())) {
            logger.info("missing parameter for new person in addition mode");
            request.setAttribute(GroupConstant.ERROR_MESSAGE, GroupConstant.ERROR_MESSAGE_TEXT);
        } else {
            Person newperson = new Person(newName.get(), newPhone.get(), newEmail.get());
            personService.save(newperson);
        }
        List<Person> clients = personService.findAll();
        if (!clients.isEmpty()) {
            request.setAttribute(GroupConstant.LISTGROUP, clients);
        }
        return new CommandResult(Page.WELCOME_PAGE.getPage(), false);
    }

}
