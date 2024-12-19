<%-- 
    Document   : tugasan.jsp
    Created on : Jul 28, 2017, 11:11:04 PM
    Author     : mohd.faidzal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

    function refreshDiv() {
        var id = '${actionBean.idPermohonan}';
        var url = '${pageContext.request.contextPath}/utiliti/utilitiPemohonUtama?check&idPermohonan=' + id;
        $.get(url,
                function (data) {
                    $("#refresh").replaceWith($('#refresh', $(data)));
                }, 'html');
    }


</script>
        <script>
window.onload = function() {   $(function () {
   	var dataPoints = [];
   	$.getJSON("${pageContext.request.contextPath}/tugasan/mmkn?jumlahStat", function(data) {  
   		var chart = new CanvasJS.Chart("chartContainer",{
   			animationEnabled: true,
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	title:{
		text: "Statistik Pengambilan"
	},
	axisY: {
		title: "Permohonan"
	},
   			data: [{
   				type: "column",
   				dataPoints : data
   			}]
   		});
//                alert(data);
   		chart.render();
   	});
   });
   
      $(function () {
   	var dataPoints = [];
   	$.getJSON("${pageContext.request.contextPath}/tugasan/mmkn?jumlahStatLulus", function(data) {  
   		var chart = new CanvasJS.Chart("chartContainer1",{
   			animationEnabled: true,
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	title:{
		text: "Statistik Kelulusan"
	},
	axisY: {
		title: "Permohonan"
	},
   			data: [{
   				type: "column",
   				dataPoints : data
   			}]
   		});
//                alert(data);
   		chart.render();
   	});
   });

}
</script>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.stripes.tugasanutiliti.TugasanMMKActionBean">
    <s:hidden name="idPermohonan" id="idPermohonan" value="${actionBean.idPermohonan}"/>


    <div class="subtitle">
        <fieldset class="aras1">
            <br>
            <legend>Sila Masukkan ID Permohonan Untuk Carian</legend>
            <p>
               <label for="idPermohonan">ID Permohonan :</label>                   
            <input type="text" name="idPermohonan" onkeyup="this.value = this.value.toUpperCase();"/> 
            </p>
            
            <p>
                <label for="idPermohonan">Urusan :</label>                   
            <s:select name="kodUrusan" value="kod">
                        <s:options-collection collection="${actionBean.listKodUrusan}" label="nama" value="kod"/>
                    </s:select>
            </p>
            
            <p>
                <label for="idPermohonan"> &nbsp;</label>   
                <s:submit name="cari" value="Cari" class="btn"/>  
            </p>
                       

        </fieldset>
    </div>

    <fieldset class="aras1">
        <legend>
            Senarai Permohonan 
        </legend>

        <table style="width: 100%;">
            <tr>
                <td style="width: 50%;" valign="top">
                    <div class="content" id="refresh">
                        <display:table style="width: 100%;" class="tablecloth" name="${actionBean.listPermohonan}" cellpadding="0" cellspacing="0" id="line"
                                       requestURI="${pageContext.request.contextPath}/utiliti/tugasan">
                            <display:column title="Bil" sortable="true" style="vertical-align:baseline">
                                ${line_rowNum} </display:column>
                            <display:column title="Id Permohonan" style="vertical-align:baseline"><a href="${pageContext.request.contextPath}${line.kodPeringkat.url}&idPermohonan=${line.mohon.idPermohonan}">${line.mohon.idPermohonan}</a> </display:column>
                            <display:column property="mohon.kodUrusan.nama" title="Urusan" style="vertical-align:baseline">${line.mohon.kodUrusan.nama}</display:column>
                            <display:column property="kodPeringkat.nama" title="Peringkat" style="vertical-align:baseline">${line.kodPeringkat.nama}</display:column>

                        </display:table>
                    </div>
                </td>
                <td style="width: 50%;">
                    <div >
                        <fieldset class="aras1">

                            <table  style="width: 100%;">
                                <tr>
                                    <td style="width: 50%;">
                                        <div id="chartContainer" style="height: 370px; max-width: 920px; margin: 0px auto;"></div>       
                                    </td>
                                    <td style="width: 50%;">

                                        <div id="chartContainer1" style="height: 370px; max-width: 920px; margin: 0px auto;"></div>

                                    </td>
                                <tr>
                            </table>
                        </fieldset>
                    </div> 
                </td>
            <tr>
        </table>
    </fieldset>
            <script src="${pageContext.request.contextPath}/pub/graf/canvasjs.min.js"></script>

</s:form>
