package com.tyrael.laundry.reports.service;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.repository.filerep.KettleFileRepository;
import org.pentaho.di.repository.filerep.KettleFileRepositoryMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mark Martinez, created Mar 9, 2016
 *
 * The sole purpose of this class is to call the main kettle job. All
 * actual extraction is handled in kettle jobs & transformations.
 */
@Service
public class ExtractionService {

    private static final Logger LOG = LoggerFactory.getLogger(ExtractionService.class);

    @Autowired
    private Environment env;

    public void runKettle() {
        try {
            System.setProperty("KETTLE_JNDI_ROOT", env.getProperty("kettle.jndi.root"));
            System.setProperty("reports_root", "./tls-reports-kettle");
            System.setProperty("default.effectiveDateTime.MMddyyyyHHmmss", "01011900000000");
            System.setProperty("default.effectiveDateTime.dbmask", "MMddyyyyHH24MIss");

            KettleEnvironment.init();

            Repository repository = new KettleFileRepository();
            KettleFileRepositoryMeta repositoryMeta = new KettleFileRepositoryMeta("id", "name", "desc", "./tls-reports-kettle");
            repository.init(repositoryMeta);
            JobMeta jobMeta = null;

            jobMeta = new JobMeta("./tls-reports-kettle/main.kjb", repository);

            org.pentaho.di.job.Job job = new org.pentaho.di.job.Job(repository, jobMeta);
            String[] arg = new String[2];
            arg[0] = "kahit ano";
            arg[1] = "kahit ano";
            job.setArguments(arg);
            job.start();
            job.waitUntilFinished();
            org.pentaho.di.core.Result result = job.getResult();

            LOG.debug("Extraction result={}", result);
        } catch (KettleException e) {
            LOG.error("Error in running kettle", e);
        }
    }

}