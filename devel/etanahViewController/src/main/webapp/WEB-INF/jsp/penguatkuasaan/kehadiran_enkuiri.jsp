<%-- 
    Document   : kehadiran_enkuiri
    Created on : Jul 7, 2010, 10:24:43 AM
    Author     : farah.shafilla
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<s:form beanclass="etanah.view.penguatkuasaan.KehadiranEnkuiriActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Enkuiri
            </legend>
            <c:if test="${form}">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKehadiran}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Nama" property="orangKenaSyak.nama"></display:column>
                        <display:column title="No.Pengenalan" property="orangKenaSyak.noPengenalan"></display:column>
                        <display:column title="Kehadiran">
                            <c:if test="${line.hadir eq null}" >
                                <input type="radio" name="hadir${line_rowNum}" value="Y"/> Hadir
                                <input type="radio" name="hadir${line_rowNum}" value="T"/> Tidak Hadir &nbsp;
                            </c:if>

                            <c:if test="${line.hadir eq 'T'}" >
                                <input type="radio" name="hadir${line_rowNum}" value="Y"/> Hadir
                                <input type="radio" name="hadir${line_rowNum}" value="T" checked="checked"/> Tidak Hadir &nbsp;
                            </c:if>
                            <c:if test="${line.hadir eq 'Y'}" >
                                <input type="radio" name="hadir${line_rowNum}" value="Y" checked="checked"/> Hadir
                                <input type="radio" name="hadir${line_rowNum}" value="T"/> Tidak Hadir &nbsp;
                            </c:if>
                        </display:column>
                                <display:column title="Keputusan Denda">
                            <c:if test="${line.kenaDenda eq null}" >
                                <input type="radio" name="denda${line_rowNum}" value="Y"/> Ya
                                <input type="radio" name="denda${line_rowNum}" value="T"/> Tidak &nbsp;
                            </c:if>

                            <c:if test="${line.kenaDenda eq 'T'}" >
                                <input type="radio" name="denda${line_rowNum}" value="Y"/> Ya
                                <input type="radio" name="denda${line_rowNum}" value="T" checked="checked"/> Tidak &nbsp;
                            </c:if>
                            <c:if test="${line.kenaDenda eq 'Y'}" >
                                <input type="radio" name="denda${line_rowNum}" value="Y" checked="checked"/> Ya
                                <input type="radio" name="denda${line_rowNum}" value="T"/> Tidak &nbsp;
                            </c:if>

                        </display:column>
                        <display:column title="Catatan" >
                            <s:textarea cols="50" rows="5" name="catatan${line_rowNum}" id="catatan">${line.catatan}</s:textarea>
                        </display:column>
                        </display:table>

                    <p align="right">
                        <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="edit" value="Simpan"/>
                    </p>

                    <br>
                </div>
            </c:if>
             <c:if test="${view}">
                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.senaraiKehadiran}" cellpadding="0" cellspacing="0" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="Nama" property="orangKenaSyak.nama"></display:column>
                        <display:column title="No.Pengenalan" property="orangKenaSyak.noPengenalan"></display:column>
                        <display:column title="Kehadiran" property="hadir"></display:column>
                        <display:column title="Keputusan Denda" property="kenaDenda"></display:column>
                        <display:column title="Catatan" property="catatan"></display:column>
                        </display:table>



                    <br>
                </div>
            </c:if>
        </fieldset>
    </div>
</s:form>
