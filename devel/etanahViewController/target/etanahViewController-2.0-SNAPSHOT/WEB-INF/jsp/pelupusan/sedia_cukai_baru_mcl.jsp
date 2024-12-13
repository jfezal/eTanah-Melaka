<%-- 
    Document   : sedia_cukai_baru_mcl
    Created on : Oct 4, 2010, 11:38:36 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
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

<s:form beanclass="etanah.view.stripes.pelupusan.PenyediaanCukaiBaruMCL_ActionBean">
    <s:messages/>
    <s:errors/>
    <fieldset class="aras1">
      <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listHakmilikWithCukai}" cellpadding="0" cellspacing="0" pagesize="5"
                               requestURI="/pelupusan/surat_hakmilik_MCL_sementara" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column title="No. Lot/No.Hakmilik" style="vertical-align:baseline">${line.hakmilikPermohonan.hakmilik.lot.nama} ${line.hakmilikPermohonan.hakmilik.noLot}<br>${line.hakmilikPermohonan.hakmilik.kodHakmilik.kod} ${line.hakmilikPermohonan.hakmilik.noHakmilik}</display:column>
                    <display:column title="Keluasan sementara" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilikPermohonan.hakmilik.luas}"/>&nbsp;${line.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Cukai(RM) Lama" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilikPermohonan.hakmilik.cukai}"/></display:column>
                    <display:column title="Cukai(RM) Baru"><fmt:formatNumber pattern="#,##0.00" value="${line.cukaiBaru}"/></display:column>
                </display:table>
      </div>
<!--            <p align="center"><label></label>
                <br>
                <s:button name="simpanCukaiBaru" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>-->
    </fieldset>
</s:form>

