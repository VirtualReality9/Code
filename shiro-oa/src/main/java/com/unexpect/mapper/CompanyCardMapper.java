package com.unexpect.mapper;

import com.unexpect.pojo.CompanyCard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CompanyCardMapper {

    public List<CompanyCard> queryCompanyCard();

    public Integer addCompanyCard(String ccContent);

    public Integer updateCompanyCard();

    public Integer deleteCompanyCard(Integer ccId);

}
