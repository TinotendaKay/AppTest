/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

/**
 *
 * @author tinotenda
 */
public class ListRequester
{
    private  String orderBy;// order by field
    private String groupBy;
    private  short orderDir;// 1 = ascending, 0 descending
    private  int limit;

    public String getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

    public short getOrderDir()
    {
        return orderDir;
    }

    public void setOrderDir(short orderDir)
    {
        this.orderDir = orderDir;
    }

    public int getLimit()
    {
        return limit;
    }

    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public String getGroupBy()
    {
        return groupBy;
    }

    public void setGroupBy(String groupBy)
    {
        this.groupBy = groupBy;
    }
    
    
    
}
