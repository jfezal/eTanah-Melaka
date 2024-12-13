<%--
    Document   : laporan_operasi_polis_view
    Created on : May 17, 2010, 4:52:19 PM
    Author     : nurshahida.radzi
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatKeputusanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklum Keputusan
            </legend>
            <div class="content" >
                <c:if test="${actionBean.recordCount > 1}">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                                   id="line">
                        <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.cawangan.name" title="Cawangan" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.lot.nama" title="Unit Lot" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.noLot" title="No Lot" style="vertical-align:baseline"/>
                        <display:column property="hakmilik.kategoriTanah.nama" title="Kategori Tanah" style="vertical-align:baseline"/>
                        <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column title="Luas Baru" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                        <%--<display:column property="luasTerlibat" title="Luas Baru" ></display:column>--%>
                    </display:table>
                </c:if>

                <c:if test="${actionBean.recordCount == 1}">
                    <s:hidden name="hakmilikPermohonan.id" id="idMH"/>
                    <p>
                        <label>ID Hakmilik :</label>&nbsp;
                        ${actionBean.hakmilik.idHakmilik}&nbsp;
                        <s:hidden name="hakmilik.idHakmilik" id="idHakmilik"/>
                    </p>
                    <p>
                        <label>Cawangan :</label>&nbsp;
                        ${actionBean.hakmilik.cawangan.name}&nbsp;
                    </p>
                    <p>
                        <label>Daerah :</label>&nbsp;
                        ${actionBean.hakmilik.daerah.nama}&nbsp;
                    </p>
                    <p>
                        <label>Bandar/Pekan/Mukim :</label>&nbsp;
                        ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
                    </p>
                    <c:if test="${actionBean.stageId eq 'g_hantar_pu'}">
                        <p>
                            <label>Unit Lot :</label>&nbsp;
                            <s:select name="lot.kod" id="uom" value="${actionBean.hakmilikPermohonan.lot.kod}">
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodLot}" label="nama" value="kod" sort="nama" />
                            </s:select>
                        </p>
                        <p>
                            <label>No Lot :</label>&nbsp;
                            <s:text name="hakmilikPermohonan.noLot" id="noLotBaru" size = "10"/>&nbsp;
                        </p>
                    </c:if>
                    <c:if test="${actionBean.stageId ne 'g_hantar_pu'}">

                    </c:if>
                    <p>
                        <label>Unit Lot :</label>&nbsp;
                        ${actionBean.hakmilik.lot.nama}&nbsp;

                    </p>
                    <p>
                        <label>No Lot :</label>&nbsp;
                        ${actionBean.hakmilik.noLot}&nbsp;
                    </p>
                    <p>
                        <label>Kategori Tanah :</label>&nbsp;
                        ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
                    </p>
                    <p>
                        <label>Luas Lama :</label>&nbsp;
                        ${actionBean.hakmilik.luas}&nbsp;${actionBean.hakmilik.kodUnitLuas.nama}
                    </p>
                    <p>
                        <label>Luas Baru :</label>&nbsp;
                        ${actionBean.hakmilikPermohonan.luasTerlibat}&nbsp;

                        ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}&nbsp;
                    </p>
                    <br>
                    <p align="center">
                        <c:if test="${actionBean.stageId eq 'g_hantar_pu'}">
                            <s:button name="updateLuas" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                        </c:if>
                    </p>
                </c:if>

            </div>
        </fieldset>
    </div>
</s:form>
