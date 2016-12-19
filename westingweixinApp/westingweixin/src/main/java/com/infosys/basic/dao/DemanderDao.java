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
import com.infosys.basic.entity.Demander;

@Repository("demanderDao")
public class DemanderDao extends BaseDao<Demander> implements IDemanderDao {

    @Override
    public Demander loadByOpenid(String openid) {
        return (Demander) super.queryObject("from Demander where openid=?", openid);
    }

    @Override
    public List<Demander> list() {
        return super.list("from Demander");
    }

    public long getDemanderTotalByConditions(DemanderModel demanderModel) {
        StringBuffer sb = new StringBuffer();
        long num = 0L;
        sb.append("select count(*) as total from t_demander c where 1=1 ");
        if (StringUtils.isNotBlank(demanderModel.getStatus())) {
            if (demanderModel.getStatus().equals(com.infosys.basic.util.Constants.T_USER_STATUS_PASS_STR)) {
                sb.append(" and (status = ").append(demanderModel.getStatus()).append(" or status =")
                        .append(com.infosys.basic.util.Constants.T_USER_STATUS_DELETE).append(")");
            } else {
                sb.append(" and status = ").append(demanderModel.getStatus());
            }
        }
        if (StringUtils.isNotBlank(demanderModel.getLinkname())) {
            sb.append(" and linkname like '%").append(demanderModel.getLinkname()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderModel.getLinkphone())) {
            sb.append(" and linkphone like '%").append(demanderModel.getLinkphone()).append("%'");
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
    public PagerInfo<DemanderDto> listDemanderByKeyword(DemanderModel demanderModel) {
        PagerInfo<DemanderDto> demanderDtoPager = demanderModel.getPager();
        demanderDtoPager.setTotalRecords(this.getDemanderTotalByConditions(demanderModel)); // 总数
        StringBuffer sb = new StringBuffer("SELECT id,IFNULL(DATE_FORMAT(birthDate,'%Y-%m-%d'),'') birthDate ,"
                + "IFNULL(business,'') business,IFNULL(company,'') company,IFNULL(linkname,'') linkname,"
                + "IFNULL(linkphone,'') linkphone,IFNULL(openid,'') openid,IFNULL(qualification,'') qualification,"
                + "IFNULL(remark,'') remark,"
                + "CASE status WHEN 0 THEN '停用' WHEN 1 THEN '注册' WHEN 11 THEN '通过' WHEN 10 THEN '拒绝' ELSE '其他' END AS status FROM t_demander WHERE 1=1 ");
        if (StringUtils.isNotBlank(demanderModel.getStatus())) {
            if (demanderModel.getStatus().equals(com.infosys.basic.util.Constants.T_USER_STATUS_PASS_STR)) {
                sb.append(" and (status = ").append(demanderModel.getStatus()).append(" or status =")
                        .append(com.infosys.basic.util.Constants.T_USER_STATUS_DELETE).append(")");
            } else {
                sb.append(" and status = ").append(demanderModel.getStatus());
            }
        }
        if (StringUtils.isNotBlank(demanderModel.getLinkname())) {
            sb.append(" and linkname like '%").append(demanderModel.getLinkname()).append("%'");
        }
        if (StringUtils.isNotBlank(demanderModel.getLinkphone())) {
            sb.append(" and linkphone like '%").append(demanderModel.getLinkphone()).append("%'");
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
            demanderDto.setBusiness(valuesMap.get("business").toString());
            demanderDto.setCompany(valuesMap.get("company").toString());
            demanderDto.setLinkname(valuesMap.get("linkname").toString());
            demanderDto.setLinkphone(valuesMap.get("linkphone").toString());
            demanderDto.setOpenid(valuesMap.get("openid").toString());
            demanderDto.setQualification(valuesMap.get("qualification").toString());
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
