<%-- 
    Document   : dashboard
    Created on : Aug 15, 2017, 2:46:12 AM
    Author     : nwahida
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<%-- --%>
<html>
    <head>
        <!--Load the AJAX API-->
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">

            // Load the Visualization API and the controls package.
            google.charts.load('current', {'packages': ['corechart', 'controls']});

            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawDashboard);

function getdata(){
    var a=9;
      var url = '${pageContext.request.contextPath}/mmkn/tasklist?countLulus';
         var ab=       $.get(url,
                        function(data) {
                            alert(data);
                            var dddd = data;
                            return dddd;
                        });
                        console.log("ddd"+ab);
                        alert("aaaa"+ab);
                        return a;
}

function setT(a){
       var url = "${pageContext.request.contextPath}/mmkn/tasklist?countLulus";
         var abc;
         $.get(url,
                        function (data) {
                            console.log(data);
                            if (data)
                            {
                                alert("data"+data);
                                abc = data;
                            } else
                            {


                            }
                        });
                        
                       return abc;

}

            // Callback that creates and populates a data table,
            // instantiates a dashboard, a range slider and a pie chart,
            // passes in the data and draws it.
            function drawDashboard() {
                var as = setT('1');
                alert("sas"+as);
                var y = 0;
                var t = 8;
                ;
                alert("y:");
            

                // Create our data table.
                alert("yyyyy"+y);
                var data = google.visualization.arrayToDataTable([
                    ['Label', 'Keputusan'],
                    ['Tolak', setT()],
                    ['Lulus', getdata()]

                ]);
                
                  

                // Create a dashboard.
                var dashboard = new google.visualization.Dashboard(
                        document.getElementById('dashboard_div'));

                // Create a range slider, passing some options
                var donutRangeSlider = new google.visualization.ControlWrapper({
                    'controlType': 'NumberRangeFilter',
                    'containerId': 'filter_div',
                    'options': {
                        'filterColumnLabel': 'Keputusan'
                    }
                });

                // Create a pie chart, passing some options
                var pieChart = new google.visualization.ChartWrapper({
                    'chartType': 'PieChart',
                    'containerId': 'chart_div',
                    'options': {
                        'width': 300,
                        'height': 300,
                        'pieSliceText': 'value',
                        'legend': 'right'
                    }
                });

                // Establish dependencies, declaring that 'filter' drives 'pieChart',
                // so that the pie chart will only display entries that are let through
                // given the chosen slider range.
                dashboard.bind(donutRangeSlider, pieChart);

                // Draw the dashboard.
                dashboard.draw(data);
            }

        </script>
    </head>
</html>



<%-- buat kotak    --%>   
<style>
    .box {
        width: 170px;
        height: 70px;
        padding: 20px;
        border: 8px groove black;
        background: #A6C9E2;
        display: inline-block;
    }
    .box2 {
        width: 170px;
        height: 70px;
        padding: 20px;
        border: 8px groove black;
        background: #66ff66;
        display: inline-block;
    }
    .box3 {
        width: 200px;
        height: 70px;
        padding: 20px;
        border: 8px groove black;
        background: #FFF1E8;
        display: inline-block;
    }
    h1 {
        color: maroon;
        margin-left: 40px;
    }
</style>


<img alt="" id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="listUtil"/>

<s:form id="senarai_tugasan" beanclass="etanah.view.dashboard.tasklist.EmmknTaskListActionBean" name="form2">
    <s:hidden name="btn"/>
    <s:errors/>
    <s:messages/>  
    <div class="h1">
        <legend><strong><font size="6">
                DASHBOARD MMKN </strong></font>
        </legend> 
        &nbsp;
        <h3 style="background:red;"></h3>
        &nbsp;           

        &nbsp;
        &nbsp;
    </div>
    &nbsp;


    <fieldset class="aras1">
        <div class="box">
            <h3> MMKN Seksyen 4 </h3> 
            <p>Lihat perincian <a target="_blank" href="${pageContext.request.contextPath}/mmkn/tasklist">>></a></p>
        </div>

        <div class="box2">
            <h3> MMKN Seksyen 8 </h3> 
            <p>Lihat perincian <a target="_blank" href="https://www.quackit.com">>></a></p>
        </div>

        <div class="box3">
            <h3> MMKN Seksyen MyEtapp </h3> 
            <p>Lihat perincian <a target="_blank" href="https://www.quackit.com">>></a></p>
        </div>
    </fieldset>




    &nbsp;
    &nbsp;


    <div class="carta">

        <fieldset class="aras1">
            <legend>
                Carta dan Graf Keputusan
            </legend>

            <!--Div that will hold the dashboard-->
            <p align="center"><div id="dashboard_div">

                <!--Divs that will hold each control and chart-->
                <div id="filter_div"></div>
                <div id="chart_div"></div>
            </div></p>



        </fieldset>
    </div> 
    &nbsp; 
    &nbsp;

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Tugasan MMKN
            </legend>
            <br/>


            <p align="right" id="refresh"><s:text name="idMohon"  value="" size="30"/>                  
                <s:submit class="btn" name="cari" value="Cari"/></p>   

            <s:hidden name="idMmkn" />
            <c:set var="row_num" value="${actionBean.__pg_start}"/>
            <div class="content" align="center"><img src="${pageContext.request.contextPath}/pub/images/loading_img.gif" id="loading-img"/>&nbsp;
                <display:table class="tablecloth" name="${actionBean.dashboard}"
                               id="line" pagesize="20" style="width:95%">

                    <c:set var="row_num" value="${row_num+1}"/>              
                    <display:column title="Bil" class="bil${line_rowNum}">                        
                        ${row_num}
                    </display:column>
                    <display:column title="No Fail MMKN">
                        ${line.noFailMmkn} 
                    </display:column>

                    <display:column title="Id Mohon" style="vertical-align:baseline"><a href="${pageContext.request.contextPath}${line.kodPeringkat.url}&idPermohonan=${line.idMohon}">${line.idMohon}</a>

                    </display:column>
                    <display:column title="Keputusan" >
                        ${line.kodKpsn}
                    </display:column>

                    <display:column title="Kod Peringkat">                        
                        ${line.kodPeringkat.kod}
                    </display:column>         

                </display:table>
            </div>


        </fieldset>            

    </div>
</s:form>