
<%@page import="Connection.Manager.Tables.UserBillingManager"%>
<%@page import="Connection.Testing.Beans.UserBilling"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="menu.css">
    </head>
    <body>

        <div class="ui menu">
            <a class="active item">
                <i class="home icon"></i> Home
            </a>
            <a class="item">
                <i class="mail icon"></i> Messages
            </a>
            <div class="right menu">
                <div class="item">
                    <div class="ui icon input">
                        <input placeholder="Search..." type="text">
                        <i class="search link icon"></i>
                    </div>
                </div>
            </div>
        </div>


        <table style="width:300px">
            <tr>
                <th>id</th>
                <th>user Id</th>		
                <th>Type</th>
                <th>Unit</th>		
                <th>Payment Date</th>
            </tr>
            <%
                UserBilling userData[] = UserBillingManager.getAllRow();
                if (userData != null) {
                    for (int i = 0; i < userData.length; i++) {
            %>

            <tr>
                <td><%=i%></td>
                <td><%=userData[i].getUserId()%></td>		
                <td><%=userData[i].getType()%></td>
                <td><%=userData[i].getUnit()%></td>		
                <td><%=userData[i].getPdate()%></td>
            </tr> 
            <%
                    }
                }
            %>
        </table>
    </body>
</html>
