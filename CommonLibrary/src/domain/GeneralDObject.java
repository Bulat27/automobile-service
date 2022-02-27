/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dragon
 */
public interface GeneralDObject extends Serializable{
    
    String getTableName();
    GeneralDObject getNewRecord(ResultSet rs) throws SQLException;
    String getInsertionColumns();
    String getAtrPlaceHolders();
    void setPreparedStatementParameters(PreparedStatement ps) throws SQLException;
    String getPKWhereCondition();
    String getWhereCondition();
    String getUpdateColumnsWithPlaceHolders();
}
