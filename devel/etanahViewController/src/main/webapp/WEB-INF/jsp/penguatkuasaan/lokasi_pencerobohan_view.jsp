<%-- 
    Document   : lokasi_pencerobohan
    Created on : Jan 18, 2010, 2:28:16 PM
    Author     : farah.shafilla
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatLokasiActionBean">
  <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Lokasi Pencerobohan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiAduanLokasi}" pagesize="10" cellpadding="0" cellspacing="0" id="line" >
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column property="idAduanLokasi" title="Id Aduan"></display:column>
                    <display:column property="cawangan.name" title="Cawangan"></display:column>
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"></display:column>
                    <display:column property="pemilikan.nama" title="Jenis Tanah"></display:column>
                    <display:column property="noLot" title="No.Lot"></display:column>
                    <display:column property="lokasi" title="Lokasi"></display:column>
                </display:table>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>