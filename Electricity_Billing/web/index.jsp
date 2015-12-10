

<%@page import="Connection.Manager.Tables.BillTypeManager"%>
<%@page import="Connection.Testing.Beans.BillType"%>
<%@page import="Connection.Manager.Tables.BillingManager"%>
<%@page import="Connection.Testing.Beans.BillingControl"%>
<%@page import="Connection.Testing.Beans.UserBilling"%>
<%@page import="Connection.Manager.Tables.UserBillingManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="css/style.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="cssfiles/menu.css">
        <script language="JavaScript" src="datepick/ts_picker.js"></script>
        <title>Billing Site</title>

        <style>

            *,
            *:before,
            *:after {
                box-sizing: border-box;
            }
            form {
                border: 1px solid #c6c7cc;
                border-radius: 5px;
                font: 14px/1.4 "Helvetica Neue", Helvetica, Arial, sans-serif;
                overflow: hidden;
                width: 280px;
            }
            fieldset {
                border: 0;
                margin: 0;
                padding: 0;
            }
            input {
                border-radius: 5px;
                font: 14px/1.4 "Helvetica Neue", Helvetica, Arial, sans-serif;
                margin: 0;
            }
            .account-info {
                padding: 20px 20px 0 20px;
            }
            .account-info label {
                color: #395870;
                display: block;
                font-weight: bold;
                margin-bottom: 20px;
            }
            .account-info input {
                background: #fff;
                border: 1px solid #c6c7cc;
                box-shadow: inset 0 1px 1px rgba(0, 0, 0, .1);
                color: #636466;
                padding: 6px;
                margin-top: 6px;
                width: 100%;
            }
            .account-action {
                background: #f0f0f2;
                border-top: 1px solid #c6c7cc;
                padding: 20px;
            }
            .account-action .btn {
                background: linear-gradient(#49708f, #293f50);
                border: 0;
                color: #fff;
                cursor: pointer;
                font-weight: bold;
                float: left;
                padding: 8px 16px;
            }
            .account-action label {
                color: #7c7c80;
                font-size: 12px;
                float: left;
                margin: 10px 0 0 20px;
            }
            table,th,td{
                border:1px solid black;
                border-collapse:collapse;
            }
            th,td{
                padding:5px;
            }
        </style>


    </head>
    <body>
        <div class="ui menu">
            <a class="active item"> <i class="home icon"></i>Home </a>
            <a class="item" href="billSetup.jsp"><i class="mail icon"></i> Bill Setup  </a>
            <div class="right menu">
                <div class="item">
                    <div class="ui icon input"> User : <%= session.getAttribute("username")%>
                        <input placeholder="Search..." type="text">
                        <i class="search link icon"></i>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">

                    <div class="row clearfix">
                        <div class="col-md-8 column">
                            <div class="row clearfix">
                                <div class="col-md-8 column">

                                    <form method="post" action="insert">
                                        <fieldset class="account-info">
                                            <label>
                                                Bill Type :
                                                <select name="type">
                                                    <%
                                                        BillType[] type = BillTypeManager.getAllRow();
                                                        for (int i = 0; i < type.length; i++) {
                                                    %>
                                                    <option value=<%=type[i].getBillType()%> > <%=type[i].getBillType()%></option> 
                                                    <% } %>
                                                </select> 
                                            </label>
                                            <label>
                                                Client Id :<input type="text" name="cId"/>
                                            </label>
                                            <label>
                                                Unit :<input type="text" name="unit"/>
                                            </label>
                                            <label> Data :<input type="Text" name="timestamp" value="">
                                                <a href="javascript:show_calendar('document.tstest.timestamp', document.tstest.timestamp.value);">
                                                    <img src="datepick/cal.gif" width="16" height="16" border="0" alt="Click">
                                                </a>
                                            </label>
                                        </fieldset>
                                        <fieldset class="account-action">
                                            <input class="btn" type="submit" name="submit" value="submit">

                                        </fieldset>
                                    </form>                                    
                                </div>
                                <div class="col-md-4 column">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 column">
                            <table class="table table-bordered table-condensed">
                                <thead>
                                    <tr>
                                        <th>#id</th>
                                        <th>Type</th>
                                        <th>Unit</th>
                                        <th>start_date</th>
                                        <th>end_date</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <%

                                        BillingControl[] bcontrol = BillingManager.getAllRow();
                                        String sDate = " ";
                                        String check="NULL";
                                        String eDate = " ";

                                        if (bcontrol != null) {
                                            for (int i = 0; i < bcontrol.length; i++) {
                                                if ((bcontrol[i].getSdate().equals(check))) {
                                                    sDate = " ";
                                                }
                                                else{
                                                    sDate=bcontrol[i].getSdate();
                                                }
                                                if ((bcontrol[i].getEdate().equals(check))) {
                                                    eDate = " ";
                                                }
                                                else{
                                                    eDate=bcontrol[i].getEdate();
                                                }

                                    %>

                                    <tr>
                                        <td><%=bcontrol[i].getId()%></td>		
                                        <td><%=bcontrol[i].getType()%></td>
                                        <td><%=bcontrol[i].getUnit()%></td>
                                        <td><%=sDate + " "%></td>
                                        <td><%=eDate + " "%></td>

                                    </tr> 


                                    <%
                                            }
                                        }
                                    %>


                                </tbody>
                            </table>
                        </div>
                    </div>
                    <hr style=" color: #293f50">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-condensed">
                                <thead>
                                    <tr>
                                        <th>#id</th>
                                        <th>user_Id</th>
                                        <th>Type</th>
                                        <th>Unit</th>
                                        <th>Total_Money</th>
                                        <th>Payment Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        UserBilling userData[] = UserBillingManager.getAllRow();
                                        if (userData != null) {
                                            for (int i = 0; i < userData.length; i++) {
                                    %>
                                    <tr>
                                        <td><%=i%></td>
                                        <td><%=userData[i].getUserId()%></td>		
                                        <td><%=userData[i].getType()%></td>
                                        <td><%=userData[i].getTotalUnit()%></td>	
                                        <td><%=userData[i].getCost()%></td>
                                        <td><%=userData[i].getPdate()%></td>
                                    </tr>
                                    <%
                                            }
                                        }
                                    %>
                                </tbody>
                            </table>
                            <ul class="pagination pagination-sm">
                                <li>
                                    <a href="#">Prev</a>
                                </li>
                                <li>
                                    <a href="#">1</a>
                                </li>

                                <li>
                                    <a href="#">Next</a>
                                </li>
                            </ul>
                            <hr>
                        </div>
                    </div>
                </div>  

                </body>
                </html>
