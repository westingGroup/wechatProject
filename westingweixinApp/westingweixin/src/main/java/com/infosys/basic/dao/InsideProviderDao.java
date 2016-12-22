package com.infosys.basic.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.infosys.basic.dao.base.BaseDao;
import com.infosys.basic.dto.DemanderDto;
import com.infosys.basic.dto.DemanderModel;
import com.infosys.basic.dto.PagerInfo;
import com.infosys.basic.entity.InsideProvider;

@Repository("insideProviderDao")
public class InsideProviderDao extends BaseDao<InsideProvider> implements IInsideProviderDao {

    @Override
    public InsideProvider loadByUsername(String username) {
        return (InsideProvider) super.queryObject("from InsideProvider where username=? and status=1", username);
    }
    
    @Override
    public InsideProvider loadByUsernameExcludeId(String username ,int id) {
        return (InsideProvider) super.queryObject("from InsideProvider where username=? and status=1 and id !=?", new Object[]{username,id});
    }

    @Override
    public List<InsideProvider> list() {
        return super.list("from InsideProvider");
    }

    public long getDemanderTotalByConditions(DemanderModel demanderModel) {
        StringBuffer sb = new StringBuffer();
        long num = 0L;
        sb.append("select count(*) as total from t_insideprovider c where 1=1 ");
        if (StringUtils.isNotBlank(demanderModel.getStatus())) {
            sb.append(" and status = ").append(demanderModel.getStatus());
        }
        if (StringUtils.isNotBlank(demanderModel.getUsername())) {
            sb.append(" and username like '%").append(demanderModel.getUsername()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderModel.getPhone())) {
            sb.append(" and phone like '%").append(demanderModel.getPhone()).append("%'");
        }
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        for (Map<String, Object> valuesMap : userListInDB) {
            num = new BigDecimal(valuesMap.get("total").toString()).intValue();
        }
        sb = null;
        return num;
    }

    @Override
    public PagerInfo<DemanderDto> listInsideProviderByKeyword(DemanderModel demanderModel) {
        PagerInfo<DemanderDto> demanderDtoPager = demanderModel.getPager();
        demanderDtoPager.setTotalRecords(this.getDemanderTotalByConditions(demanderModel)); // 总数
        StringBuffer sb = new StringBuffer(
                " SELECT id,IFNULL(DATE_FORMAT(birthDate,'%Y-%m-%d'),'') birthDate ,IFNULL(password,'') password,IFNULL(phone,'') phone,"
                        + "IFNULL(username,'') username,IF(sex=0,'男','女') sex,IFNULL(remark,'') remark,"
                        + "CASE STATUS WHEN 0 THEN '停用' WHEN 1 THEN '正常' ELSE '其他' END AS status FROM t_insideprovider where 1=1 ");
        if (StringUtils.isNotBlank(demanderModel.getStatus())) {
            sb.append(" and status = ").append(demanderModel.getStatus());
        }
        if (StringUtils.isNotBlank(demanderModel.getUsername())) {
            sb.append(" and username like '%").append(demanderModel.getUsername()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderModel.getPhone())) {
            sb.append(" and phone like '%").append(demanderModel.getPhone()).append("%'");
        }

        sb.append(" order by id asc limit ")
                .append((demanderDtoPager.getCurrentPage() - 1) * demanderDtoPager.getPageSize()).append(",")
                .append(demanderDtoPager.getPageSize());
        String sql = sb.toString();
        List<Map<String, Object>> userListInDB = this.listBySql(sql, null);
        List<DemanderDto> DemanderDtoList = new ArrayList<DemanderDto>();
        for (Map<String, Object> valuesMap : userListInDB) {
            DemanderDto demanderDto = new DemanderDto();
            demanderDto.setId(Integer.valueOf(valuesMap.get("id").toString()));
            demanderDto.setBirthDate(valuesMap.get("birthDate").toString());
            demanderDto.setPassword(valuesMap.get("password").toString());
            demanderDto.setSex(valuesMap.get("sex").toString());
            demanderDto.setUsername(valuesMap.get("username").toString());
            demanderDto.setPhone(valuesMap.get("phone").toString());
            demanderDto.setRemark(valuesMap.get("remark").toString());
            demanderDto.setStatus(valuesMap.get("status").toString());
            DemanderDtoList.add(demanderDto);
            demanderDto = null;
        }
        demanderDtoPager.setRecords(DemanderDtoList);
        sb = null;
        return demanderDtoPager;
    }

}
