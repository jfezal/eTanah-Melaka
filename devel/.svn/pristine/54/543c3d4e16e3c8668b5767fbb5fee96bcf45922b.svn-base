<%-- 
    Document   : nilaianTanah_dan_PremiumBaru_notis5A
    Created on : Jun 29, 2011, 11:29:16 AM
    Author     : Nageswararao
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
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
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

</script>

<stripes:form beanclass="etanah.view.stripes.pembangunan.NilaianTanahDanPremiumBaruNotis5AActionBean">
    <s:messages/>
    <div class="subtitle">

        <fieldset class="aras1">
            <c:if test ="${!(actionBean.permohonan.keputusan.kod eq 'NK') || (actionBean.permohonan.keputusan.kod eq null)}">
                <legend>
                    Nilaian dan Premium Baru
                </legend>
                <div class="content" align="center">
                    <c:if test="${edit}">
                        <table border="0" width="45%">
                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tarikh Nilaian</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</font>
                                    <s:text name="tarikhNilaian" id="datepicker" class="datepicker" size="12" style="width:130px;"/></td>
                            </tr>
                            <tr>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Rujukan Surat JPPH</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</font>
                                    <s:text name="nomborRujukan" id="nomborRujukan" size="30" style="width:130px;" maxlength="50"/></td>
                            </tr>
                            <c:if test = "${actionBean.permohonan.kodUrusan.kod ne 'SBMS'}">
                                <tr>
                                    <td width="28%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Nilaian Baru </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(RM) : </font>
                                        <s:text name="nilaianTanah" id="nilaianTanah"  value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;" size="12" /></td>
                                </tr>
                                <tr>
                                    <td width="28%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tarikh Nilaian </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(RM) : </font>
                                        <s:text name="tarikhNilaianJPPH" id="tarikhNilaianJPPH"   style="width:130px;" class="datepicker"/></td>
                                </tr>
                                <tr>
                                    <td width="28%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Kadar Premium </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</font>
                                        <s:text name="permohonan.noMahkamah" id="ulasan" formatPattern="#,##0.00" size="4" maxlength="2" onkeyup="validateNumber(this,this.value);" />%</td>
                                </tr>                  
                                <tr>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium Baru</font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">(RM) : </font>
                                        <%--<s:text name="premium" id="premiumBaru"  value="0.00" onkeyup="validateNumber(this,this.value);" formatPattern="#,##0.00" style="width:130px;" size="10"/>--%>
                                        <fmt:formatNumber pattern="#,##0.00" value="${actionBean.premium}"/></td>
                                </tr>
                            </c:if>
                        </table><br/>
                        <p>
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${!edit}">
                        <table border="0" width="45%">
                            <tr><tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tarikh Nilaian</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</font>
                                    ${actionBean.tarikhNilaian}  </td>
                            </tr>
                            <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Rujukan Surat JPPH</font></td>
                                <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</font>
                                    ${actionBean.nomborRujukan}</td>
                            </tr>
                            
                            <c:if test = "${actionBean.permohonan.kodUrusan.kod ne 'SBMS'}">
                                <tr><td width="28%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Nilaian Baru </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> (RM):</font>
                                        <fmt:formatNumber pattern="#,##0.00" value="${actionBean.nilaianTanah}"/> </td>
                                </tr>
                                <tr><td width="28%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Kadar Premium </font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</font>
                                        ${actionBean.permohonan.noMahkamah}% </td>
                                </tr>
                                <tr><td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Premium Baru</font></td>
                                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"> (RM):</font>
                                        <fmt:formatNumber pattern="#,##0.00" value="${actionBean.premium}"/> </td>
                                </tr>
                            </c:if>

                        </table><br/>                
                    </c:if>

                </div>
            </c:if>


            <c:if test ="${actionBean.permohonan.keputusan.kod eq 'NK'}">
                <legend>
                    Nilaian dan Premium Baru
                </legend>
                <div class="content" align="center">
                    Jabatan Penilaian dan Perkhidmatan Harta Negeri Melaka Mengesahkan Bahawa Nilaian Tanah Dikekalkan.         
                </div>
            </c:if>

        </fieldset>

    </div>
</stripes:form>


