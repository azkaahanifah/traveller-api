package com.project.traveller.util;

import com.project.traveller.entity.MountainInformationEntity;
import com.project.traveller.exception.BusinessException;
import com.project.traveller.model.request.MountainInformationRequest;
import com.project.traveller.repository.MountainInformationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.project.traveller.util.ErrorMessage.*;

@Component
public class ValidateUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ValidateUtil.class);

    @Autowired
    private MountainInformationRepository repository;

    public void validateDuplicateName(MountainInformationRequest request) {
        MountainInformationEntity checkToDB = repository.findByName(request.getMountainName());
        if (null != checkToDB) {
            LOG.debug("Validation failed: Cannot duplicate field mountain_name");
            throw new BusinessException(ERROR_DUPLICATE_NAME);
        }
    }

    public void validateMandatoryField(MountainInformationRequest request) {
        if (null != request && (null == request.getMountainName() || request.getMountainName().isEmpty())) {
            LOG.debug("Validation failed: Field mountain_name cannot be null");
            throw new BusinessException(ERROR_MANDATORY_FILED);
        }
    }
}
