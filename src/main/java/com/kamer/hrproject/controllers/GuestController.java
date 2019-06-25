package com.kamer.hrproject.controllers;
import com.kamer.hrproject.entities.Job;
import com.kamer.hrproject.services.JobService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class GuestController {

    @Autowired
    JobService jobService;

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



}
