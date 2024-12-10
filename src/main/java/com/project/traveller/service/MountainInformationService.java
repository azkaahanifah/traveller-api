package com.project.traveller.service;

import com.project.traveller.entity.MountainInformationEntity;
import com.project.traveller.model.request.MountainInformationRequest;
import com.project.traveller.exception.BusinessException;
import com.project.traveller.model.response.MountainInformationResponse;
import com.project.traveller.repository.MountainInformationRepository;
import com.project.traveller.util.MapperUtil;
import com.project.traveller.util.ValidateUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static com.project.traveller.util.ErrorMessage.*;
import static com.project.traveller.util.MapperUtil.mapperToDTO;
import static com.project.traveller.util.MapperUtil.mapperToEntity;

@Service
public class MountainInformationService implements IMountainInformationService {

    private static final Logger LOG = LoggerFactory.getLogger(MountainInformationService.class);

    private static final String SUCCESS_PROCESS = "Success";

    @Autowired
    private MountainInformationRepository repository;

    @Autowired
    private ValidateUtil validateUtil;

    @Override
    public MountainInformationEntity create(MountainInformationRequest request) {
        HttpServletRequest httpServletRequest = getCurrentHttpRequest();
        LOG.debug("Process request for endpoint: {} with payload: {}, at: {}", httpServletRequest.getRequestURI(), request, LocalDateTime.now());

        validateRequest(request);

        //Save to DB
        MountainInformationEntity informationEntity = mapperToEntity(new MountainInformationEntity(), request);
        repository.saveAndFlush(informationEntity);

        return informationEntity;
    }

    @Override
    public MountainInformationResponse get(Long id) {
        HttpServletRequest httpServletRequest = getCurrentHttpRequest();
        LOG.debug("Process request for endpoint: {} with id: {}, at: {}", httpServletRequest.getRequestURI(), id, LocalDateTime.now());

        MountainInformationEntity fromDB = findById(id);
        return mapperToDTO(fromDB);
    }

    /**
     * I used Java Lambda and Java Stream in this method
     * So, we can sort them by field Created At
     */
    @Override
    public List<MountainInformationResponse> getMountainInformations() {
        HttpServletRequest httpServletRequest = getCurrentHttpRequest();
        LOG.debug("Process request for endpoint: {} at: {}", httpServletRequest.getRequestURI(), LocalDateTime.now());

        List<MountainInformationEntity> findAll = repository.
                findAll().stream()
                .sorted(Comparator.comparing(MountainInformationEntity::getCreatedAt))
                .toList();

        return findAll.stream().map(MapperUtil::mapperToDTO).toList();
    }

    @Override
    public MountainInformationEntity update(MountainInformationRequest request) {
        HttpServletRequest httpServletRequest = getCurrentHttpRequest();
        LOG.debug("Process request for endpoint: {} with payload: {}, at: {}", httpServletRequest.getRequestURI(), request, LocalDateTime.now());

        MountainInformationEntity entity = findById(request);

        validateRequest(entity, request);

        //Save to DB
        MountainInformationEntity informationEntity = mapperToEntity(entity, request);
        repository.saveAndFlush(informationEntity);

        return informationEntity;
    }

    @Override
    public String delete(Long id) {
        HttpServletRequest httpServletRequest = getCurrentHttpRequest();
        LOG.debug("Process request for endpoint: {} with id: {}, at: {}", httpServletRequest.getRequestURI(), id, LocalDateTime.now());

        MountainInformationEntity entity = findById(id);
        repository.delete(entity);

        return SUCCESS_PROCESS;
    }

    private MountainInformationEntity findById(MountainInformationRequest request) {
        if (null == request.getId()) {
            LOG.debug("Request failed: ID is required");
            throw new BusinessException(ERROR_REQUIRED_ID);
        }

        return findById(request.getId());
    }

    private MountainInformationEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessException(ERROR_NOT_FOUND));
    }

    public void validateRequest(MountainInformationRequest request) {
        LOG.debug("Validating request: {}", request);

        validateUtil.validateMandatoryField(request);
        validateUtil.validateDuplicateName(request);
    }

    public void validateRequest(MountainInformationEntity entity, MountainInformationRequest request) {
        LOG.debug("Validating request: {}", request);

        validateUtil.validateMandatoryField(request);
        validateMountainName(entity, request);
    }

    public void validateMountainName(MountainInformationEntity entity, MountainInformationRequest request) {
        if (!entity.getMountainName().equalsIgnoreCase(request.getMountainName())) {
            validateUtil.validateDuplicateName(request);
        }
    }
}
