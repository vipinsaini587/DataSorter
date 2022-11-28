package com.DataSorter.DataSorter.Service;

import com.DataSorter.DataSorter.Dto.ImsiDto;
import com.DataSorter.DataSorter.Dto.CellTowers;
import com.DataSorter.DataSorter.Dto.FindLocationCollection;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class ImsiLocationService {
    public FindLocationCollection imsiClassData(ImsiDto imsiDto){
        FindLocationCollection findLocationCollection = new FindLocationCollection();
        findLocationCollection.setRadioType("lte");
        findLocationCollection.setCellTowers(getCelltowers(imsiDto));
        return findLocationCollection;
    }
    private List<CellTowers> getCelltowers(ImsiDto imsiDto) {
       // ImsiDto imsiDto = new ImsiDto();
        List<CellTowers> list = new ArrayList<>();
        CellTowers cellTowers = new CellTowers();
        cellTowers.setCellId(imsiDto.getCellId());
        cellTowers.setLocationAreaCode(imsiDto.getLocationAreaCode());
        cellTowers.setMobileCountryCode(imsiDto.getMobileCountryCode());
        cellTowers.setMobileNetworkCode(imsiDto.getMobileNetworkCode());
        list.add(cellTowers);
        return list;
    }
    public ResponseEntity findLocationMCC(ImsiDto imsiDto)throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        FindLocationCollection findLocationCollection = imsiClassData(imsiDto);
        HttpEntity he = new HttpEntity<>(findLocationCollection);
        System.out.println(he);
        ResponseEntity responseEntity = restTemplate.exchange("https://apiv2.combain.com/?key=3221cc94055c5adb0cbf", HttpMethod.POST,he,String.class);
        return responseEntity;
    }
}