/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin;

import java.sql.SQLException;
import org.h2.tools.Server;

/**
 *
 * @author oscardanielrangelmartinez
 */
public class DB {
    
    public static void main(String[] args) throws SQLException {
        Server.createTcpServer("-tcpAllowOthers").start();
    }
    
}
