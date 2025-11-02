package dev.reno.controller;

import dev.reno.model.RequestJobDto;
import dev.reno.model.ResponseJobDto;
import dev.reno.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/submit")
    ResponseEntity<ResponseJobDto> submitJob(@RequestBody RequestJobDto requestJobDto) {
        return jobService.submitJob(requestJobDto);
    }
}