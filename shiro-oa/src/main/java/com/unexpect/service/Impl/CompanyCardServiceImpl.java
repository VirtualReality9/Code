package com.unexpect.service.Impl;

import com.unexpect.mapper.CompanyCardMapper;
import com.unexpect.pojo.CompanyCard;
import com.unexpect.service.CompanyCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CompanyCardServiceImpl implements CompanyCardService {

    @Autowired
    CompanyCardMapper companyCardMapper;


    @Override
    public List<CompanyCard> queryCompanyCard() {
        return companyCardMapper.queryCompanyCard();
    }

    @Override
    public Integer addCompanyCard(String ccContent) {
        return companyCardMapper.addCompanyCard(ccContent);
    }

    @Override
    public Integer updateCompanyCard() {
        return null;
    }

    @Override
    public Integer deleteCompanyCard(Integer ccId) {
        return companyCardMapper.deleteCompanyCard(ccId);
    }
}
