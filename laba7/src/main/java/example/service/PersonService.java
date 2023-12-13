package example.service;

import example.exception.RepositoryException;
import example.exception.ServiceException;
import example.model.Person;
import example.repository.RepositoryCreator;
import example.repository.specification.PersonRepository;

import java.util.List;

public class PersonService {
    public List<Person> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            PersonRepository personRepository = repositoryCreator.getPersonRepository();
            return personRepository.findAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void save(Person person) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            PersonRepository personRepository = repositoryCreator.getPersonRepository();
            Person newp = new Person(person);
            personRepository.save(newp);
            System.out.println("adding");

        } catch (RepositoryException exception) {
           // throw new ServiceException(exception.getMessage(), exception);
        }
    }

}
