package com.project.traveller.service;

import com.project.traveller.entity.MountainInformationEntity;
import com.project.traveller.model.request.MountainInformationRequest;
import com.project.traveller.model.response.MountainInformationResponse;

import java.util.List;

public interface IMountainInformationService {

    MountainInformationEntity create(MountainInformationRequest request);

    MountainInformationResponse get(Long id);

    List<MountainInformationResponse> getMountainInformations();

    MountainInformationEntity update(MountainInformationRequest request);

    String delete(Long id);
}
