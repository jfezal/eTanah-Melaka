<%-- 
    Document   : maklumat_borang7A
    Created on : Feb 24, 2010, 10:50:02 AM
    Author     : farah.shafilla
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.text.*,java.util.*" session="true"%>
<script type="text/javascript">
    
    function test() {
        document.getElementById("tempohHari").value ="";
        document.getElementById("tarikhTamat").value ="";
    }
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function changeDate()
    {

        //var tempohBayaran = document.surat.aduanTindakan.tempohHari.value;
        var tempohBayaran = document.getElementById("tempohHari").value;

        if(tempohBayaran == "" || isNaN(tempohBayaran))
        {
            alert("Sila Masukkan Tempoh Hari");
            document.surat.tempohHari.focus();
        }
        else
        {
            var nodays = parseInt(tempohBayaran);
            var todates = new Date();
            todates.setDate(todates.getDate()+nodays);
            //alert(todates);

            var _date = todates.getDate();
            var _month = todates.getMonth()+1;
            var _year = todates.getFullYear();

            if(_date < 10)
            {
                _date = '0'+_date;
            }
            if(_month < 10)
            {
                _month = '0'+_month;
            }
            //document.surat.aduanTindakan.tarikhTamat.value = _date+"/"+_month+"/"+_year;
            document.getElementById("tarikhTamat").value=_date+"/"+_month+"/"+_year;
        }
    }
    function validateForm(){
        if( $('#tempohHari').val()=="" || $('#tempohHari').val() == 0)
        {
            alert("Sila Isikan Tempoh Remedi");
            return false;
        }
        return true;
    }
    function showReport(){
        window.open("${pageContext.request.contextPath}/penguatkuasaan/maklumat_borang7A?janaBorang7A", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }

</script>
<s:form  name="form1" beanclass="etanah.view.penguatkuasaan.Borang7AactionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">

            <c:if test="${form}">
                <legend>
                    Maklumat Borang 7A
                </legend>
                <div class="content"><p>
                        <label> Tarikh Borang :</label>                                              
                        <s:text name="tarikhMula" formatType="date" formatPattern="dd/MM/yyyy" readonly="true" value="<%=new java.util.Date()%>"/>
                    </p>
                    <p>
                        <label> Tempoh Remedi (Hari):</label>
                        <s:text name="aduanTindakan.tempohHari"  id="tempohHari" onkeyup="validateNumber(this,this.value);" onchange="changeDate();"/>&nbsp;
                    </p>
                    <p>
                        <label> Tarikh Tamat:</label>
                        <s:text name="aduanTindakan.tarikhTamat" id="tarikhTamat" formatType="date" formatPattern="dd/MM/yyyy" readonly="true" />&nbsp;
                    </p>
                    <p>
                        <label> Tindakan untuk memulihkan pelanggaran syarat:</label>
                        <s:textarea name="aduanTindakan.catatan" id="catatan" value="${actionBean.aduanTindakan.catatan}"  rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                    </p>
                    <p>&nbsp;</p>
                    <p align="right">
                        <s:button class="btn" name="lsiSemula" value="lsi Semula" onclick="test();"/>
                        <s:button class="btn" onclick="if (validateForm())doSubmit(this.form, this.name, 'page_div')" name="simpan" value="Simpan"/>
                        <s:button class="btn" onclick="showReport();" name="" value="Jana Notis"/>
                    </p></div>
                </c:if>
                <c:if test="${view}">
                <legend>
                    Maklumat Borang 7A
                </legend>
                <div class="content"><p>
                        <label> Tarikh Borang:</label>
                        <fmt:formatDate value="${actionBean.aduanTindakan.tarikhMula}" pattern="dd/MM/yyyy" />&nbsp;&nbsp;
                    </p>
                    <p>
                        <label> Tempoh Remedi (Hari):</label>
                        ${actionBean.aduanTindakan.tempohHari}&nbsp;
                    </p>
                    <p>
                        <label> Tarikh Tamat:</label>
                        <fmt:formatDate value="${actionBean.aduanTindakan.tarikhTamat}" pattern="dd/MM/yyyy" />
                    </p>
                    <p>
                        <label> Tindakan untuk memulihkan pelanggaran syarat:</label>
                        ${actionBean.aduanTindakan.catatan}&nbsp;
                    </p>
                </div>
            </c:if>
        </fieldset>
    </div>

</s:form>
