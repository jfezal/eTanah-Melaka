<%-- 
    Document   : tab_keputusan_upload
    Created on : Dec 29, 2010, 12:41:29 PM
    Author     : mazurahayati.yusop
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>


<s:messages/>
<s:errors/>&nbsp;
<s:form beanclass="etanah.view.lelong.dokumen.FolderAction">
    <s:hidden id="idMohon" name="permohonan.idPermohonan"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan
            </legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td class="rowlabel1"><font color="red">*</font>Keputusan :</td>
                        <td class="input1">
                            <s:radio name="keputusan" id="" value="L"/> Lulus
                            <s:radio name="keputusan" id="" value="T"/> Tolak
                        </td>
                    </tr>
                    <tr>
                        <td class="rowlabel1">Ulasan :</td>
                        <td class="input1"><s:textarea name="ulasan" rows="5" cols="50" onchange="this.value=this.value.toUpperCase();"  /></td>
                    </tr>
                </table>
                <p align="right">
                    <s:submit name="save" id="" value="Simpan" class="btn"/>
                    <%--<s:reset name="" value="Isi Semula" class="btn"/>&nbsp;--%>
                </p>
            </div>
        </fieldset>
    </div>
    <br/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Syor Dan Ulasan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listFasa}" cellpadding="0" cellspacing="0" id="line">
                    <display:column title="No" sortable="true" style="vertical-align:top;">${line_rowNum}</display:column >
                    <display:column property="idPengguna" title="Nama" style="vertical-align:top;"/>
                    <display:column property="keputusan.nama" title="Keputusan" style="vertical-align:top;"/>
                    <display:column title="Ulasan" style="width:80px;vertical-align:top;">
                        <textarea name="" style="background: transparent; border: 0px" cols="80" rows="10" readonly="readonly" id="text">${line.ulasan}</textarea>
                    </display:column>
                    <display:column title="Tarikh Keputusan" style="width:90px;vertical-align:top;">
                        <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm:ss"/>
                    </display:column>
                </display:table>
            </div>

        </fieldset>
        <p align="right"><label>&nbsp;</label>
            <s:submit name="seterusnya" id="" value="Seterusnya" class="btn"/>
        </p>
    </div>
</s:form>