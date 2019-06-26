package com.kamer.hrproject.controllers;

import com.kamer.hrproject.entities.Application;
import com.kamer.hrproject.entities.Job;
import com.kamer.hrproject.services.ApplicationService;
import com.kamer.hrproject.services.FileUploadService;
import com.kamer.hrproject.services.JobService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Blob;
import java.util.List;

@Controller
public class GuestController {

    @Autowired
    JobService jobService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    FileUploadService fileUploadService;

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
            System.out.println("jobid -> " + job.getId());
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
    public ModelAndView processForm(@RequestParam("file") MultipartFile resumeFile, @PathVariable("id") Long id, @ModelAttribute(value="application") Application application, @ModelAttribute(value = "job") Job job) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("apply");
        Blob resumeFileBlob = fileUploadService.handleUpload(resumeFile);
        application.setResume(resumeFileBlob);
        try {
            application.setJob(jobService.getJob(id));
            Application tempApplication = applicationService.createApplication(application);
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