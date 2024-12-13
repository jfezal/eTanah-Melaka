<%-- 
    Document   : semakDrafWarta
    Created on : May 27, 2010, 11:18:40 AM
    Author     : MASSITA
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<s:form beanclass="etanah.view.stripes.hasil.RekodWartaActionBean">
    <s:messages />
    <s:errors />&nbsp;
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Yang Dicadangkan Untuk Rekod Warta
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}"
                               pagesize="5" cellpadding="0" cellspacing="0" requestURI="/hasil/penerimaan_warta" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik"><a href="#" onclick="show('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                    <display:column title="Jenis dan Nombor Hakmilik" class="${line_rowNum}">
                        ${line.hakmilik.kodHakmilik.kod}&nbsp;&nbsp;
                        ${line.hakmilik.noHakmilik}<br>
                    </display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="${line_rowNum}"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" class="${line_rowNum}"/>
                    <display:column property="hakmilik.noLot" title="No. Lot" class="${line_rowNum}"/>
                    <display:column title="Luas"><div align="right"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>${line.hakmilik.kodUnitLuas.kod}</div></display:column>
                    <display:column title="Jumlah Tunggakan(RM)"><div align="right"><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.akaunCukai.baki}"/></div></display:column>
                </display:table>
            </div>
            <table border="0" width="96%">
                <tr>
                    <td align="right">
                        <s:button class="btn" onclick="cetakDraf();" name="" value="Cetak Draf"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Rekod Warta
            </legend>
            <p>
                <label> No Warta :</label>
                <s:text name="noWarta" id="" size="21"/>
            </p>
            <p>
                <label> Tarikh Penghantaran :</label>
                <s:text name="dateHantarWarta" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>
            <p>
                <label> Tarikh Penerimaan :</label>
                <s:text name="dateTerimaWarta" id="datepicker2" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>
            <table border="0" width="96%">
                <tr>
                    <td align="right">
                        <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="saveOrUpdate" value="Simpan"/>&nbsp;
                        <s:reset name="" value="Isi Semula" class="btn" />
                    </td>
                </tr>
            </table>
        </fieldset>
    </div>
</s:form>

