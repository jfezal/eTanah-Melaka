<%-- 
    Document   : UtilitiCarianPermohonan
    Created on : Oct 19, 2011, 5:11:32 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.lelong.UtilitiTabsTarikhdanJurulelong">
    <script type="text/javascript">
        function validate(){
             if($("#idHakmilik").val() == ""){
                 alert("Sila Masukkan IDPermohonan!");
                 return false;
             }
             return true;
        }
        
    </script>
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Carian
            </legend>
            <br/>
            <p>
                <label> ID Permohonan :</label>
                <s:text name="idPermohonan" maxlength="20" size="31" id="idPermohonan"/>
            </p>
            <br/>
            <p align="center">
                <s:submit name="find" value="Cari" onclick="return validate();" class="btn" />
            </p></br>
        </fieldset>
    </div>
</s:form>
