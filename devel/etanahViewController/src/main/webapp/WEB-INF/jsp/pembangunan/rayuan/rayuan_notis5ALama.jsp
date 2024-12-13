<%--
    Document   : notis5A
    Created on : Feb 18, 2011, 6:24:10 PM
    Author     : NageswaraRao
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


     function removeCommas(val){
        return val.replace(/\,/g,'');
    }


    function calc(){
        var cukai = $("#cukai").val();
        var premium = $("#premium").val();
        var upah = $("#upah").val();
        var kos = $("#kos").val();
        var pelantetap = $("#pelantetap").val();
        var daftartetap = $("#daftartetap").val();
        var jumlah = parseFloat(Number(removeCommas(cukai))) + parseFloat(Number(removeCommas(premium))) + + parseFloat(Number(removeCommas(upah))) + parseFloat(Number(removeCommas(kos)))
                + parseFloat(Number(removeCommas(pelantetap))) + parseFloat(Number(removeCommas(daftartetap))) ;
        $("#jumlah").val(jumlah);
    }

</script>

<s:form  beanclass="etanah.view.stripes.pembangunan.RayuanAnsuranActionBean">
<s:messages/>
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Borang 5A/7G
        </legend>
        <div class="content" align="center">
            <table  border="1" class="tablecloth">
                   <tr><td>Premium </td>
                        <td>RM</td>
                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.premium}"/></td>
                    </tr>
                    <tr><td>Sumbangan Saliran </td>
                        <td>RM</td>
                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.sumbanganSaliran}"/></td>
                    </tr>
                    <c:if test="${actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'SBMS'}">
                    <tr><td>Hasil Tahun Pertama </td>
                        <td>RM</td>
                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.hasilTahunPertama}"/></td>
                    </tr>
                    <tr><td>Bayaran Wang Pelan </td>
                        <td>RM</td>
                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaranPelan}"/></td>
                    </tr>                    
                    <tr><td>Bayaran Hakmilik Semantara</td>
                        <td>RM</td>
                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaranSemantara}"/></td>
                    </tr>
                    <tr><td>Bayaran Hakmilik Kekal </td>
                        <td>RM</td>
                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaranKekal}"/></td>
                    </tr>
                    </c:if>
                    <c:if test="${actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSPSS' ||
                                  actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSPSN' ||
                                  actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSKKT' ||
                                  actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'TSKSN'}">
                        <tr><td>Pendaftaran </td>
                        <td>RM</td>
                        <td style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.pendaftaran}"/></td>
                    </tr>
                    </c:if>
                    <tr><th>Jumlah</th>
                        <th>RM</th>
                        <th style="text-align:right"><fmt:formatNumber pattern="#,##0.00" value="${actionBean.jumlah3}"/></th>
                    </tr>
                </table>

            </div>
        </fieldset>
    </div>

</s:form>

