package com.dao;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import com.po.*;
public class wtProvider {
	private static final String TABLE = "weiboAndtianya";

    public  String insert(final List<weiboAndtianya> list){
        if(list!=null){
            StringBuffer sb = new StringBuffer("insert into ").append(TABLE).append(" values ");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].belongto},#'{'list[{0}].url}, #'{'list[{0}].time}, #'{'list[{0}].context}),#'{'list[{0}].type}");
            for (int i=0; i<list.size(); i++) {
                sb.append(mf.format(new Object[]{i}));  
                if (i < list.size() - 1) {  
                    sb.append(",");  
                }  
            }
            System.out.println(sb.toString());
            return sb.toString();
        }
        System.out.println("Failed");
        return null;
    }
   
}