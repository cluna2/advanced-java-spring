package com.codingnomads.springdata.example.dml.usingqueryannotation;

import com.codingnomads.springdata.example.dml.usingqueryannotation.models.SoilType;
import com.codingnomads.springdata.example.dml.usingqueryannotation.repositories.SoilTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoilTypeService {

    @Autowired
    SoilTypeRepo soilTypeRepo;

    public void saveSoils() {

        SoilType st1 = SoilType.builder()
                .name("type1")
                .dry(true)
                .ph(7.0)
                .build();
        soilTypeRepo.save(st1);
        SoilType st2 = SoilType.builder()
                .name("type2")
                .dry(false)
                .ph(11.0)
                .build();
        soilTypeRepo.save(st2);
        SoilType st3 = SoilType.builder()
                .name("type3")
                .dry(false)
                .ph(5.5)
                .build();
        soilTypeRepo.save(st3);
        SoilType st4 = SoilType.builder()
                .name("type4")
                .dry(true)
                .ph(6.6)
                .build();
    }

    public void getDrySoils() {
        System.out.println("--Getting Dry soil types--");
        List<SoilType> drySoils = soilTypeRepo.getSoilTypesIsDry(true);
        drySoils.forEach(System.out::println);

        Sort descendingName = Sort.by(Sort.Order.desc("name"));
        List<SoilType> drySoilsSorted = soilTypeRepo.getDrySoilTypesSorted(descendingName);
        drySoilsSorted.forEach(System.out::println);

        Pageable pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Order.asc("name")));
        Page<SoilType> page;
        System.out.println("/ PAGED DRY SOILS /");
        do {
            page = soilTypeRepo.getPagedSoilTypes(pageRequest);
            System.out.println("PAGE : " + page.getNumber());
            page.getContent().forEach(System.out::println);
            pageRequest = pageRequest.next();
        } while (page.hasNext());

    }

    public void getSoilsPhRanges() {
        System.out.println("--Geting soil types in ph ranges--");
        Pageable pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Order.asc("ph")));
        Page<SoilType> page;
        do {
            page = soilTypeRepo.getPagedSoilTypeWithPhInRange(0.0, 14.0, pageRequest);
            System.out.println("PAGE: " + page.getNumber());
            page.getContent().forEach(st -> System.out.println(st.getPh()));
            pageRequest = pageRequest.next();
        } while (page.hasNext());
    }


}
