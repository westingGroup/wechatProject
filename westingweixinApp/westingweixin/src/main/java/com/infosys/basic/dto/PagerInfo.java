package com.infosys.basic.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

public class PagerInfo<T> implements Serializable {

    private static final long serialVersionUID = -3080742314500190394L;

    public static final Long DEFAULT_PAGE_SIZE = 20L;

    private List<T> records = new ArrayList<T>();

    private Long totalRecords = 0L;

    private Long totalPage = 0L;

    private Long currentPage = 1L;

    private int isCanUp = 0;

    private int isCanDown = 0;

    private Long pageSize = DEFAULT_PAGE_SIZE;

    private String orderProperty = "";

    private String order = "";

    private boolean countTotal = true;

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        if (currentPage <= 0) {
            this.currentPage = 1L;
        } else {
            this.currentPage = currentPage;
        }
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        if (pageSize <= 0) {
            this.pageSize = 1L;
        } else {
            this.pageSize = pageSize;
        }
    }

    public boolean isCountTotal() {
        return countTotal;
    }

    public void setCountTotal(boolean countTotal) {
        this.countTotal = countTotal;
    }

    /**
     * 鐠侊紕鐣荤拋鏉跨秿閻ㄥ嫭?妞ゅ灚鏆�
     */
    public Long getTotalPages() {
        if (getTotalRecords() == 0) {
            return 1L;
        }
        Long div = getTotalRecords() / getPageSize();
        Long sub = getTotalRecords() % getPageSize();
        if (sub == 0) {
            return div;
        } else {
            return div + 1;
        }
    }

    /**
     * 閺勵垰鎯佺拋鍓х枂娴滃棙甯撴惔蹇撶潣閹�
     * 
     * @return
     */
    public boolean isOrderBySetted() {
        return StringUtils.isNotBlank(this.order) && StringUtils.isNotBlank(this.orderProperty);
    }

    /**
     * 閺嶈宓佽ぐ鎾冲妞や絻骞忛崣鏍唶瑜版洖绱戞慨瀣娇
     * 
     * @return
     */
    public Long getFirstResult() {
        return (getCurrentPage() - 1) * getPageSize();
    }

    /**
     * 閺嶈宓佽ぐ鎾冲妞や絻骞忛崣鏍唶瑜版洖绱戞慨瀣娇(for mysql)
     * 
     * @return
     */
    public Long getFirstResultExt() {
        Long firstPage = getFirstResult();
        return firstPage <= 0 ? 0 : (firstPage - 1);
    }

    public String getOrderProperty() {
        return orderProperty;
    }

    public void setOrderProperty(String orderProperty) {
        this.orderProperty = orderProperty;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        String lowcaseOrderDir = StringUtils.lowerCase(order);
        // 濡�鐓rder鐎涙顑佹稉鑼畱閸氬牊纭堕崐?
        String[] orderDirs = StringUtils.split(lowcaseOrderDir, ',');
        for (String orderDirStr : orderDirs) {
            if (!StringUtils.equals(Sort.DESC, orderDirStr) && !StringUtils.equals(Sort.ASC, orderDirStr)) {
                throw new IllegalArgumentException("" + orderDirStr + "");
            }
        }
        this.order = lowcaseOrderDir;
    }

    /**
     * 閼惧嘲绶遍幒鎺戠碍閸欏倹鏆�
     * 
     * @return
     */
    public List<Sort> getSort() {
        String[] orderBys = StringUtils.split(this.orderProperty, ',');
        String[] orderDirs = StringUtils.split(this.order, ',');
        Validate.isTrue(orderBys.length == orderDirs.length, "");

        List<Sort> orders = new ArrayList<Sort>();
        for (int i = 0; i < orderBys.length; i++) {
            orders.add(new Sort(orderBys[i], orderDirs[i]));
        }
        return orders;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public int getIsCanUp() {
        return isCanUp;
    }

    public void setIsCanUp(int isCanUp) {
        this.isCanUp = isCanUp;
    }

    public int getIsCanDown() {
        return isCanDown;
    }

    public void setIsCanDown(int isCanDown) {
        this.isCanDown = isCanDown;
    }

    /**
     * 婢跺秴鍩梡ager閻ㄥ嫬鐔�張顑夸繆閹垽绱漷otalRecords,currentPage,pageSize,orderProperty,
     * order,countTotal
     * 
     * @param pager
     * @return
     */
    public static <X, M> PagerInfo<M> cloneFromPager(PagerInfo<X> pager) {
        PagerInfo<M> result = new PagerInfo<M>();
        result.setCountTotal(pager.isCountTotal());
        result.setCurrentPage(pager.getCurrentPage());
        result.setOrder(pager.getOrder());
        result.setOrderProperty(pager.getOrderProperty());
        result.setPageSize(pager.getPageSize());
        result.setTotalRecords(pager.getTotalRecords());
        return result;
    }

    /**
     * 婢跺秴鍩梡ager閻ㄥ嫬鐔�張顑夸繆閹垽绱漷otalRecords,currentPage,pageSize,orderProperty,
     * order,countTotal, 闁插秵鏌婄拋鍓х枂records閿涘otalRecords鐏炵偞?
     * 
     * @param pager
     * @return
     */
    public static <X> PagerInfo<X> cloneFromPager(PagerInfo<X> pager, long totalRecords, List<X> records) {
        PagerInfo<X> result = cloneFromPager(pager);
        result.setTotalRecords(totalRecords);
        result.setRecords(records);
        return result;
    }

    public static class Sort {
        public static final String ASC = "asc";

        public static final String DESC = "desc";

        private final String property;

        private final String dir;

        public Sort(String property, String dir) {
            this.property = property;
            this.dir = dir;
        }

        public String getProperty() {
            return property;
        }

        public String getDir() {
            return dir;
        }
    }
}
