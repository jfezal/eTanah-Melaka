<%-- 
    Document   : daftar_kemaskini_hakmilik
    Created on : Apr 25, 2013, 5:37:55 PM
    Author     : azri
--%>

<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<script type="text/javascript">

  function ReplaceAll(Source, stringToFind, stringToReplace) {
    var temp = Source;
    var index = temp.indexOf(stringToFind);
    while (index != -1) {
      temp = temp.replace(stringToFind, stringToReplace);
      index = temp.indexOf(stringToFind);

    }
    return temp;
  }

  function RunProgram(strUserID, strNama, strJawatan, strIDPermohonan, strIDStage) {
    
    alert(" user id:" + strUserID + " nama: " + strNama + " jawatan:" + strJawatan + " pmohonan id: " + strIDPermohonan + " stage id: " + strIDStage);
    strNama = ReplaceAll(strNama, " ", "_");
    strJawatan = ReplaceAll(strJawatan, " ", "_");
    stageId = strIDStage;
//    alert("nama:" + strNama);
//    alert("jawatan:" + strJawatan);
//    alert("stageid:" + stageId);
    var objShell = new ActiveXObject("WScript.Shell");
    var sysVar = objShell.Environment("System");
    objShell.Run(sysVar("eTanahGISLIM") + " " + strUserID + " " + strNama + " " + strJawatan + " " + strIDPermohonan + " " + stageId);
  }

</script>
<s:form beanclass="etanah.view.stripes.common.MaklumatPermohonanActionBean" name="form1">
  <div class="subtitle">
    <fieldset class="aras1">
      <legend>Maklum Aplikasi EGIS</legend>
      <p>
        Sila klik pada butang "Hantar Ke EGIS" untuk mengmaskini status hakmilik.
      </p>
      <br>
      <br>
      <p>
        <label>&nbsp;</label>
        <s:button name="kemaskiniHakmilik" id="kemaskiniHakmilik" value="Hantar Ke EGIS" class="longbtn"  onclick="RunProgram('${actionBean.pengguna.idPengguna}', '${actionBean.pengguna.nama}', '${actionBean.pengguna.jawatan}', '${actionBean.idPemohonan1}', 'g_kemaskini_hakmilik');"/>&nbsp;
      </p>
      <br>
      <br>
    </fieldset>
  </div>
</s:form>