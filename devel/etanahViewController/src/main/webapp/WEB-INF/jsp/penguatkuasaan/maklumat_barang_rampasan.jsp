<%--
    Document   : maklumat_barang_rampasan
    Created on : 04-Feb-2010, 12:27:21
    Author     : nurshahida.radzi
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
          <div class="content" align="center">
                <table>
                        <tr>
                                <td class="rowlabel1"> Tarikh Rampasan :</td>
                                <td class="input1"></td>
                        </tr>
                        <tr>
                                <td class="rowlabel1"> Ulasan :</td>
                                <td class="input1"><s:text name="idInsert" /> </td>
                        </tr>
                </table>
            </div>
</s:form>
