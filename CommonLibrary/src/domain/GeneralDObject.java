/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Dragon
 */
public interface GeneralDObject extends Serializable{
    
    public String getTableName();
    public GeneralDObject getNewRecord(ResultSet rs) throws SQLException;
}