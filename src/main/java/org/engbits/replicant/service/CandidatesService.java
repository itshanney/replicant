package org.engbits.replicant.service;

import org.engbits.replicant.dao.CandidatesDao;
import org.engbits.replicant.model.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Service that handles all functionality for {@link Candidate} data
 *
 * @author replicant.team
 * @since 0.1.0
 */
@Service
public class CandidatesService {

    private static final Logger LOG = LoggerFactory.getLogger(CandidatesService.class);

    private final CandidatesDao candidatesDao;

    /**
     * Constructor to make a new service with all injected, required components
     * @param candidatesDao {@link CandidatesDao} to handle {@link Candidate} persistence
     */
    @Inject
    public CandidatesService(final CandidatesDao candidatesDao) {
        this.candidatesDao = candidatesDao;
    }

    /**
     * Creates a new {@link Candidate} by persisting it
     * @param candidate {@link Candidate} that is to be created
     * @return {@link Candidate} that was persisted with all auto-populated properties defined
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Candidate createCandidate(final Candidate candidate) {
        LOG.debug("Creating new Candidate: {}", candidate);
        candidatesDao.insert(candidate);

        return candidate;
    }

    /**
     * Edits a {@link Candidate} by updating all values supplied in the object
     * @param candidate {@link Candidate} representing the new object to update
     * @return {@link Candidate} with all edits completed
     */
    @Transactional(propagation =  Propagation.REQUIRES_NEW)
    public Candidate editCandidate(final Candidate candidate) {
        LOG.debug("Editing Candidate: {}", candidate);
        candidatesDao.update(candidate);

        return candidate;
    }

    /**
     * Gets a {@link Candidate} with the given ID of the Candidate, or null if not found
     * @param candidateId ID of the {@link Candidate} to query
     * @return {@link Candidate} that with the given ID, or null if not found
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Candidate getCandidateById(final Long candidateId) {
        LOG.debug("Getting Candidate by ID: ()", candidateId);

        return candidatesDao.selectById(candidateId);
    }

    /**
     * Gets all of the {@link Candidate}s in the system
     * @return {@link List} of all known {@link Candidate}s
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Candidate> getCandidates() {
        return candidatesDao.selectAll();
    }

}
