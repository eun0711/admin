package com.example.admin.service;

import com.example.admin.model.entity.Partner;
import com.example.admin.model.network.Header;
import com.example.admin.model.network.request.PartnerApiRequest;
import com.example.admin.model.network.response.PartnerApiResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner>{

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(partner -> response(partner))
                .orElseGet(()-> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        return Optional.ofNullable(request.getData())
                .map(body ->{
                    return baseRepository.findById(body.getId());
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(partner -> {

                    PartnerApiRequest body = request.getData();
                    partner.setStatus(body.getStatus())
                            .setName(body.getName())
                            .setStatus(body.getStatus())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                    ;
                    return partner;

                })
                .map(changeItem -> baseRepository.save(changeItem))
                .map(newItem -> response(newItem))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<PartnerApiResponse> response(Partner partner){

        PartnerApiResponse body = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .address(partner.getAddress())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unregisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();

        return Header.OK(body);
    }
}

