package com.project.traveller.util;

import com.project.traveller.entity.MountainInformationEntity;
import com.project.traveller.model.request.MountainInformationRequest;
import com.project.traveller.model.response.MountainInformationResponse;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    /**
     * Used this method for save or update process
     * So, I change this method more flexible
     */
    public static MountainInformationEntity mapperToEntity(MountainInformationEntity entity, MountainInformationRequest request) {
        if (isNewProduct(request)) {
            entity = new MountainInformationEntity();
        }

        entity.setMountainName(request.getMountainName());
        entity.setDescription(request.getDescription());
        entity.setLocation(request.getLocation());
        entity.setMdpl(request.getMdpl());
        entity.setRate(request.getRate());

        return entity;
    }

    public static MountainInformationResponse mapperToDTO(MountainInformationEntity fromDB) {
        return MountainInformationResponse.builder()
                .id(fromDB.getId())
                .mountainName(fromDB.getMountainName())
                .location(fromDB.getLocation())
                .description(fromDB.getDescription())
                .rate(fromDB.getRate())
                .elevation(fromDB.getMdpl())
                .build();
    }

    private static boolean isNewProduct(MountainInformationRequest request) {
        return null == request.getId();
    }
}
