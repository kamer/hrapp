package com.kamer.hrproject.controllers;

import com.kamer.hrproject.entities.Application;
import com.kamer.hrproject.entities.Job;
import com.kamer.hrproject.services.ApplicationService;
import com.kamer.hrproject.services.JobService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GuestController {

    @Autowired
    JobService jobService;

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/")
    public ModelAndView seeAllJobListings() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        try {
            List<Job> jobList = jobService.getAllJobs();
            modelAndView.addObject("jobs", jobList);
            modelAndView.setStatus(HttpStatus.OK);
        } catch (NotFoundException e) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            modelAndView.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        } finally {
            return modelAndView;
        }
    }

    @GetMapping(value = {"/jobs/{id}"})
    public ModelAndView seeJobDetails(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("job-details");
        try {
            Job job = jobService.getJob(id);
            modelAndView.addObject("job", job);
            modelAndView.setStatus(HttpStatus.OK);
        } catch (NotFoundException e) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            modelAndView.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        } finally {
            return modelAndView;
        }
    }

    @GetMapping(value = {"/apply/{id}"})
    public ModelAndView applyPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("apply");
        try {
            Job job = jobService.getJob(id);
            modelAndView.addObject("job", job);
            modelAndView.addObject("application", new Application());
            modelAndView.setStatus(HttpStatus.OK);
        } catch (NotFoundException e) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            modelAndView.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        } finally {
            return modelAndView;
        }
    }

    @PostMapping(value = {"/apply/{id}"})
    public ModelAndView processForm(@PathVariable("id") Long id, @ModelAttribute(value="application") Application application) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("apply");
        try {
            application.setJob(jobService.getJob(id));
            Application tempApplication = applicationService.createApplication(application);
            System.out.println(tempApplication);
            modelAndView.addObject("application", tempApplication);
            modelAndView.addObject("job", application.getJob());
            modelAndView.addObject("status", "success");
            modelAndView.setStatus(HttpStatus.OK);
        } catch (NotFoundException e) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            modelAndView.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        } finally {
            return modelAndView;
        }
    }

}