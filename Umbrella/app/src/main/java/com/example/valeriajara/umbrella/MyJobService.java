package com.example.valeriajara.umbrella;

import android.app.job.JobService;

/**
 * Created by micahrowell on 10/31/17.
 */

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(android.app.job.JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(android.app.job.JobParameters params) {
        return false;
    }
}