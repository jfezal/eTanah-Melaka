<%-- 
    Document   : surat_hakmilik_sementara_MCL
    Created on : Oct 4, 2010, 10:44:45 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pelupusan.PenyediaanSuratHakMilikSementaraMCL_ActionBean">
    <s:messages/>
    <s:errors/>
    <fieldset class="aras1">
      <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonan}" cellpadding="0" cellspacing="0"
                               requestURI="/pelupusan/surat_hakmilik_MCL_sementara" id="line">
                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                    <display:column property="hakmilik.noLot" title="No.PT" style="vertical-align:baseline"/>
                    <display:column title="Keluasan sementara" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                    <display:column title="Cukai(RM)" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##00.00" value="${line.hakmilik.cukai}"/></display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                    <display:column title="Catatan"><s:textarea name="hakmilikPermohonan.catatan"/></display:column>
                    <%--<display:column title="HMM(Untuk unit pendaftaran)"><s:textarea name="test"/></display:column><display:column title="Tarikh Pendaftaran(Untuk Unit Pendaftaran)"><s:textarea name="test"/></display:column>--%>


                </display:table>
            </div>
            <p align="center"><label></label>
                <br>
                <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
    </fieldset>
</s:form>

