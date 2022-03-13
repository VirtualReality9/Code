package com.unexpect.service;

import com.unexpect.pojo.CompanyCard;

import java.util.List;
import java.util.Map;

public interface CompanyCardService {

        public List<CompanyCard> queryCompanyCard();

        public Integer addCompanyCard(String ccContent);

        public Integer updateCompanyCard();

        public Integer deleteCompanyCard(Integer ccId);

}
