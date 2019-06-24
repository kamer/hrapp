package com.kamer.hrproject.services;

import com.kamer.hrproject.entities.Application;
import com.kamer.hrproject.entities.Job;
import com.kamer.hrproject.repositories.ApplicationRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    ApplicationRepository applicationRepository;

    /**
     * @return Created application.
     *
     * TODO: 6/24/19
     */
    public Application createApplication(Application application) {
        try {
            Application createdApplication;
            createdApplication = applicationRepository.save(application);
            logger.trace("New application[id=" + createdApplication.getId() + "] created.");
            return createdApplication;
        } catch (Exception e) {
            logger.debug("New application can't be created due to " + e.getMessage());
            throw e;
        }
    }

    /**
     * @throws NotFoundException if application is not found.
     * @throws Exception         If any other exception occurs.
     */
    public Application getApplication(Long id) throws Exception {
        try {
            Optional<Application> optionalApplication = applicationRepository.findById(id);
            if (optionalApplication.isPresent()) {
                logger.trace("Application[id=" + optionalApplication.get().getId() + "] is found.");
                return optionalApplication.get();
            } else {
                throw new NotFoundException("Application[id=" + id + "] is not found.");
            }
        } catch (NotFoundException e) {
            logger.trace("Application[id=" + id + "] is not found.");
            throw e;
        } catch (Exception e) {
            logger.debug("Application cannot be retrieved due to " + e.getMessage());
            throw e;
        }
    }

    /**
     *
     * @return List of applications.
     * @throws NotFoundException if applications are not found.
     * @throws Exception         If any other exception occurs.
     */
    public List<Application> getAllApplications() throws Exception {
        try {
            List<Application> applications = applicationRepository.findAll();
            if (applications.size() > 0) {
                logger.trace("All applications are retrieved.");
                return applications;
            } else {
                logger.trace("No applications are found.");
                throw new NotFoundException("No applications are found.");
            }
        } catch (NotFoundException e) {
            logger.trace("No applications are found.");
            throw e;
        } catch (Exception e) {
            logger.debug("Applications cannot be retrieved due to " + e.getMessage());
            throw e;
        }
    }

    /**
     * @return Boolean.
     * @throws NotFoundException if application doesn't exist.
     * @throws Exception         If any other exception occurs.
     */
    public Boolean deleteApplication(Long id) throws Exception {
        try {
            applicationRepository.delete(getApplication(id));
            logger.trace("Application[id=" + id + "] is deleted successfully.");
            return true;
        } catch (NotFoundException e) {
            logger.debug("Application[id=" + id + "] doesn't exist.");
            throw e;
        } catch (Exception e) {
            logger.debug("Application[id=" + id + "] is cannot be removed due to" + e.getMessage());
            throw e;
        }
    }

}
