<%-- 
    Document   : maklumat_hakmilik_tanah
    Created on : Mar 3, 2010, 11:42:35 AM
    Author     : mazurahayati
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:form action="">
<s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name=""  cellpadding="0" cellspacing="0">
                    <display:column title="No" sortable="true"></display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                    <display:column property="hakmilik.noLot" title="No Lot" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Maklumat Atas Tanah"><s:textarea name="atasTanah"/></display:column>
                </display:table>
            </div>
            <p align="center"><label></label>
                <br>
                <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>

        </fieldset>
    </div>
</s:form>
