<%-- 
    Document   : barang_rampasan
    Created on : Feb 11, 2010, 12:05:57 PM
    Author     : farah.shafilla
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:form beanclass="etanah.view.penguatkuasaan.PenguatkuasaanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Barang Rampasan
            </legend>
            <div class="content" align="center">
            </div>
            <div class="content" align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Pilih" sortable="true">{lineMP_rowNum}</display:column>
                    <display:column title="Bil"/>
                    <display:column title="Barang yang Dirampas" />
                    <display:column title="Kuantiti" />
                    <display:column title="Tempat Simpanan" />
                    <display:column title="Catatan"/>
                    <display:column title="Nilai"/>
                </display:table>
            </div>
        </fieldset>
    </div>
    <div class="content" align="right">
        <table>
            <tr>
                <td align="right"><s:button name="Tambah" value="Tambah" class="btn" /></td>
                <td align="right"><s:button name="Hapus" value="Hapus" class="btn" /></td>
            </tr>
        </table>
    </div>
                <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Barang Yang Diserah Sementara
            </legend>
            <div class="content" align="center">
            </div>
            <div class="content" align="center">
                <display:table class="tablecloth" name="" cellpadding="0" cellspacing="0" id="lineMP">
                    <display:column title="Pilih" sortable="true">{lineMP_rowNum}</display:column>
                    <display:column title="Bil"/>
                    <display:column title="Barang yang Dirampas" />
                    <display:column title="Kuantiti" />
                    <display:column title="Tempat Simpanan" />
                    <display:column title="Catatan"/>
                    <display:column title="Nilai"/>
                </display:table>
            </div>
        </fieldset>
    </div>
</s:form>
