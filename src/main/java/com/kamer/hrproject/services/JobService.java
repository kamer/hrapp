package com.kamer.hrproject.services;

import com.kamer.hrproject.entities.Job;
import com.kamer.hrproject.repositories.JobRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    Logger logger = LoggerFactory.getLogger(JobService.class);

    @Autowired
    JobRepository jobRepository;

    /**
     * @return Created job.
     * @throws Exception if any error occurs
     */
    public Job createJob(Job job) throws Exception {
        try {
            Job createdJob;
            createdJob = jobRepository.save(job);
            logger.trace("New job[id=" + createdJob.getId() + "] created.");
            return createdJob;
        } catch (Exception e) {
            logger.debug("New job can't be created due to " + e.getMessage());
            throw e;
        }
    }

    /**
     * @param id Id of the job.
     * @return Found job.
     * @throws NotFoundException if job is not found.
     * @throws Exception         If any other exception occurs.
     */
    public Job getJob(Long id) throws Exception {
        try {
            Optional<Job> optionalJob = jobRepository.findById(id);
            if (optionalJob.isPresent()) {
                logger.trace("Job[id=" + optionalJob.get().getId() + "] is found.");
                return optionalJob.get();
            } else {
                throw new NotFoundException("Job[id=" + id + "] is not found.");
            }
        } catch (NotFoundException e) {
            logger.trace("Job[id=" + id + "] is not found.");
            throw e;
        } catch (Exception e) {
            logger.debug("Job cannot be retrieved due to " + e.getMessage());
            throw e;
        }
    }

    /**
     * @return List of jobs.
     * @throws NotFoundException if jobs are not found.
     * @throws Exception         If any other exception occurs.
     */
    public List<Job> getAllJobs() throws Exception {
        try {
            List<Job> jobs = jobRepository.findAll();
            if (jobs.size() > 0) {
                logger.trace("All jobs are retrieved.");
                return jobs;
            } else {
                logger.trace("No jobs are found.");
                throw new NotFoundException("No jobs are found.");
            }
        } catch (NotFoundException e) {
            logger.trace("No jobs are found.");
            throw e;
        } catch (Exception e) {
            logger.debug("Jobs cannot be retrieved due to " + e.getMessage());
            throw e;
        }
    }

    /**
     * @return true if
     * @throws NotFoundException if job doesn't exist.
     * @throws Exception         If any other exception occurs.
     */
    public Boolean deleteJob(Long id) throws Exception {
        try {
            jobRepository.delete(getJob(id));
            logger.trace("Job[id=" + id + "] is deleted successfully.");
            return true;
        } catch (NotFoundException e) {
            logger.debug("Job[id=" + id + "] doesn't exist.");
            throw e;
        } catch (Exception e) {
            logger.debug("Job[id=" + id + "] is cannot be removed due to" + e.getMessage());
            throw e;
        }
    }
}
