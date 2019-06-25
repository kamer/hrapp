package com.kamer.hrproject;

import com.kamer.hrproject.entities.Application;
import com.kamer.hrproject.entities.Job;
import com.kamer.hrproject.repositories.ApplicationRepository;
import com.kamer.hrproject.repositories.JobRepository;
import com.kamer.hrproject.services.ApplicationService;
import com.kamer.hrproject.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.DatabaseMetaData;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    JobService jobService;

    @Override
    public void run(String... args) throws Exception {

        List<Job> jobs = Arrays.asList(
                new Job("Junior PHP Developer", "PHP tabanlı web yazılım projelerinin geliştirilmesi ve mevcut yazılımların güncellenmesi çalışmalarında yer alacak en az 1 yıl tecrübeli, Yenibosna ofisimizde tam zamanlı çalışabilecek, Junior PHP Developer çalışma arkadaşı aramaktayız.", 2 ,new Date()),
                new Job("Android Developer", "We are looking for Android Developers with the ambition to be game changers in the industry by creating innovative and exciting products and who would love to be a key stakeholder in our agile environment.", 1, new Date()),
                new Job(" Full Stack Web Tasarım Uzmanı", "PHP Programlama diline hakim ve yeniliklerini takip eden,\n" +
                        "Codeigniter, Symfony, Laravel Frameworklerinden bir tanesine aşina\n" +
                        "ya da PHP Laravel Framework'e ve OOP Standartlarına uygun kod yazabilen,\n" +
                        "Daha önceden takım çalışmasında bulunmuş kişiler aranmaktadır.", 4, new Date())
        );

        jobs.forEach( element -> {
            try {
                jobService.createJob(element);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });





    }
}
