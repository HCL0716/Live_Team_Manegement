package com.live.liveteam.common.result;

import java.util.List;


import com.github.pagehelper.PageInfo;
import com.live.liveteam.common.constant.BizConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页对象
 * 
 * @author linyuanbao
 *
 */
@ApiModel(value = "PageVO(分页对象)")
public class PageVO<T> {

    @ApiModelProperty(value = "每页数量", required = true)
    private Integer pageSize = BizConstant.PAGE_SIZE_20;// 每页显示记录数

    @ApiModelProperty(value = "当前页", required = true)
    private Integer currentPage = 1;// 当前页码

    @ApiModelProperty(value = "总记录数", required = true)
    private Integer totalCount = 0;// 总记录数

    @ApiModelProperty(value = "数据", required = true)
    private List<T> data;// 查询结果

    public PageVO() {}

    public PageVO(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public PageVO(Integer currentPage, Integer pageSize) {
        if (currentPage != null) {
            this.currentPage = currentPage;
        }
        if (pageSize != null) {
            this.pageSize = pageSize;
        }
    }


    public void setPageInfo(PageInfo<T> pageinfo){


    }
    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        //总记录数
        this.totalCount = totalCount;
        //总页数   数据总数/每页长度
        Integer totalPage = getTotalPage();
        //如果当前页小于0，或者数据总页数等于0
        if (currentPage < 0 || totalPage == 0) {
            //当前页是第一页
            currentPage = 1;
            //如果当前页大于总页数
        } else if (currentPage > totalPage) {
            //当前页等于总页数
            currentPage = totalPage;
        }
    }

    @ApiModelProperty(value = "总页数", required = true)
    public Integer getTotalPage() {
        //设置默认页数为0
        Integer totalPage = 0;
        //如果数据库记录为0
        if (this.totalCount == 0) {
            //直接返回页数为0
            return totalPage;
        }
        //页数= 总数/每行页数
        totalPage = this.totalCount / pageSize;
        //如果总页数为0  或者总记录数跟每页行数取余不等于0，页数+1，因为不等于0代表有多的行，一行没展示完，自然要加1
        if (totalPage == 0 || totalCount % pageSize != 0) {
            totalPage = totalPage + 1;
        }
        //返回总页数
        return totalPage;
    }

    @ApiModelProperty(hidden = true)
    public Integer getStartIndex() {
        if (currentPage > 0) {
            return pageSize * (currentPage - 1);
        } else {
            return 0;
        }
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
