<%-- 
    Document   : penyediaan_borang_endorsan
    Created on : Dec 26, 2009, 1:05:15 AM
    Author     : nurfaizati
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<div class="subtitle">
     <s:form beanclass="etanah.view.stripes.hasil.StatusPembayaranActionBean">



        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Carian
                </legend>
                 <p>
                <label for="No Resit">Nombor Rujukan Dasar :</label>
                <s:text name="" size="21"/>
                <%--<s:text name="noResit" size="30"/>--%>
            </p>
             <p>
                <label for="No Resit">Tarikh Keputusan Dasar :</label>
                <s:text name="" size="21"/>
                <%--<s:text name="noResit" size="30"/>--%>
            </p>

             <table border="0" width="100%">
                <tr>
                    <td align="right">
                        <s:submit   name="search1" value="Cari" class="btn"/>
                        <s:reset name=" " value="Isi Semula" class="btn"/>
                    </td> </tr>
            </table>



        </fieldset>

        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Penerima
                </legend>

                <div class="content" align="center">

                    <display:table class="tablecloth" name=""
                                    pagesize="4" cellpadding="0" cellspacing="0" requestURI="/hasil/endorsan" id="line">

                        <display:column title="Bil"  value="1" sortable="true">${line_rowNum}</display:column>
                        <display:column property="" title="ID Hakmilik" class="${line_rowNum}" />
                        <display:column property="" title="Nama" class="${line_rowNum}" />
                       <display:column property="" title="Jumlah Tunggakan (RM)" class="${line_rowNum}"/>
                         <display:column property="" title="Jumlah Tahun Tunggakan" class="${line_rowNum}"/>
                          <display:column property="" title="Tarikh Surat/Notis 6A" class="${line_rowNum}"/>
                           <display:column property="" title="Tarikh Terima" class="${line_rowNum}"/>
                           <display:column property="" title="Cara Penghantaran" class="${line_rowNum}">
                                <td>
                            <s:radio name="kep1" value="T"/>Pos
                            <s:radio name="kep1" value="T"/>Penghantar Notis
                           </td></display:column>
                             <display:column property="" title="Surat Akuan Berkanun" class="${line_rowNum}">
                                <td>
                            <s:radio name="kep1" value="T"/>Ya
                            <s:radio name="kep1" value="T"/>Tidak
                           </td></display:column>
                   </display:table>
                </div>

                <p>
                    <label>
                        <div class="instr" align="center">
                        <s:errors/></div>
                    </label>
                </p>
                <table border="0" bgcolor="green" width="100%">
                    <tr>
                        <td align="right">
                            <s:submit name="" value="Simpan" class="btn" />
                            <%--<s:reset name=" " value="Isi Semula" class="btn"/>--%>

                        </td>
                    </tr>
                </table>

            </fieldset>

    </s:form>


