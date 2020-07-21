package com.mcss.microadmin.service;

import com.mcss.microadmin.data.dao.SequenceDAO;
import com.mcss.microadmin.data.entity.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SequenceService {

    private static Logger LOGGER = LoggerFactory.getLogger(SequenceService.class);

    @Autowired
    SequenceDAO sequenceDAO;

    public int getNextId(String sequence) {
        Sequence get = this.sequenceDAO.findById(sequence).get();
        Integer seq = get.getSequence() + 1;
        get.setSequence(seq);
        this.sequenceDAO.save(get);
        return seq;
    }

}
