<%-- 
    Document   : charting_kelulusan
    Created on : Dec 30, 2009, 9:29:55 AM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<script type="text/javascript">

    function ReplaceAll(Source,stringToFind,stringToReplace){
    var temp = Source;
    var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
         return temp;
    }

    function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        <%--var stageId = "g_charting_keputusan";--%>
        stageId = strIDStage;
        //alert("nama:" + strNama);
        //alert ("jawatan:" + strJawatan);
        //alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }
    
    function RunProgram1(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        strIDStage = "g_charting_semak";
        //alert(strIDStage);
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");
        <%--var stageId = "g_charting_keputusan";--%>
        stageId = strIDStage;
        //alert("nama:" + strNama);
        //alert ("jawatan:" + strJawatan);
        //alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");
        //objShell.run("C:\Program Files\EpiWeb\ArcMap_ActiveX_Initializer\InititializeArcMap.exe");
        //objShell.run("C:\Program Files\eTanahStrata\InititializeStrata.exe")
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }

<%--    function RunProgram2(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
        alert(" user id:" +strUserID + " nama: " +strNama+ " jawatan:" +strJawatan+ " pmohonan id: " +strIDPermohonan+ " stage id: " +strIDStage);
        strNama = ReplaceAll(strNama," ","_");
        strJawatan = ReplaceAll(strJawatan," ","_");        
        stageId = strIDStage;
        alert("nama:" + strNama);
        alert ("jawatan:" + strJawatan);
        alert ("stageid:" + stageId);
        var objShell = new ActiveXObject("WScript.Shell");
        var sysVar = objShell.Environment("System");        
        objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " +  strJawatan + " " + strIDPermohonan + " " + stageId);
    }--%>
</script>


<stripes:form beanclass="etanah.view.stripes.pembangunan.ChartingKelulusanActionBean">
    <s:messages/>
    <s:errors/>
   <div class="subtitle">
       <fieldset class="aras1">
           <legend>Charting Kelulusan/Penolakan</legend>
               <p>
                   <label>ID Permohonan:</label>
                   ${actionBean.permohonan.idPermohonan}&nbsp;
               </p>
               <p>
                   <label>Keputusan: </label>
                   ${actionBean.keputusan}&nbsp;
               </p>
       </fieldset>
   </div>

   <p>
        <label>&nbsp;</label>
        <c:if test = "${actionBean.stageId eq 'g_charting_keputusan'}"> 
        <s:button name="lakarPelan" id="lakarPelan" value="Charting" class="btn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
        </c:if>
        
        <c:if test = "${actionBean.stageId eq 'semakchartingkpsn'}">
        <s:button name="lakarPelan" id="lakarPelan" value="Semak Charting" class="btn"  onclick="RunProgram1('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
        </c:if>
        
        <%--<c:if test="${actionBean.stageId eq 'g_charting_lulus'}">
        <s:button name="lakarPelan" id="lakarPelan" value="Charting" class="btn"  onclick="RunProgram('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
        </c:if>
        <c:if test="${actionBean.stageId eq 'g_charting_tolak'}">        
        <s:button name="lakarPelan" id="lakarPelan" value="Charting" class="btn"  onclick="RunProgram2('${actionBean.pguna.idPengguna}','${actionBean.pguna.nama}','${actionBean.pguna.jawatan}','${actionBean.idPermohonan}','${actionBean.stageId}');"/>&nbsp;
        </c:if>--%>
        <%--<s:button name="charting" value="Charting" class="btn"/>&nbsp;--%>
        
   </p>
</stripes:form>
  
