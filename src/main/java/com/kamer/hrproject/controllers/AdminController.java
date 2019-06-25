package com.kamer.hrproject.controllers;

import com.kamer.hrproject.entities.Application;
import com.kamer.hrproject.entities.Job;
import com.kamer.hrproject.services.ApplicationService;
import com.kamer.hrproject.services.JobService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    JobService jobService;

    @Autowired
    ApplicationService applicationService;

    @GetMapping("")
    public ModelAndView getAllApplications() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("admin-page");
            modelAndView.addObject("jobApplications", applicationService.getAllApplications());
            modelAndView.setStatus(HttpStatus.OK);
        } catch (NotFoundException e) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            modelAndView.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        } finally {
            return modelAndView;
        }
    }

    @GetMapping("/add-job")
    public ModelAndView addJob() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("add-job");
            modelAndView.addObject("job", new Job());
            modelAndView.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            modelAndView.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        } finally {
            return modelAndView;
        }
    }

    @PostMapping(value = {"/add-job"})
    public ModelAndView processForm(@ModelAttribute(value="job") Job job) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-job");
        System.out.println(job);
        try {
            modelAndView.addObject("application", jobService.createJob(job));
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

    @GetMapping(value = {"/applications/{id}"})
    public ModelAndView processForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("application-details");
        try {
            modelAndView.addObject("jobApplication", applicationService.getApplication(id));
            modelAndView.setStatus(HttpStatus.OK);
        } catch (NotFoundException e) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            modelAndView.setStatus(HttpStatus.SERVICE_UNAVAILABLE);
        } finally {
            return modelAndView;
        }
    }

    @GetMapping("/jobs")
    public ModelAndView seeAllJobListings() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin-jobs");
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

}
