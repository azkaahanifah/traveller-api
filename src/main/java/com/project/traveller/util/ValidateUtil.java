package com.project.traveller.util;

import com.project.traveller.entity.MountainInformationEntity;
import com.project.traveller.exception.BusinessException;
import com.project.traveller.model.request.MountainInformationRequest;
import com.project.traveller.repository.MountainInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.project.traveller.util.ErrorMessage.*;

@Component
public class ValidateUtil {

    @Autowired
    private MountainInformationRepository repository;

    public void validateDuplicateName(MountainInformationRequest request) {
        MountainInformationEntity checkToDB = repository.findByName(request.getMountainName());
        if (null != checkToDB) {
            throw new BusinessException(ERROR_DUPLICATE_NAME);
        }
    }

    public void validateMandatoryField(MountainInformationRequest request) {
        if (null != request && (null == request.getMountainName() || request.getMountainName().isEmpty())) {
            throw new BusinessException(ERROR_MANDATORY_FILED);
        }
    }
}
