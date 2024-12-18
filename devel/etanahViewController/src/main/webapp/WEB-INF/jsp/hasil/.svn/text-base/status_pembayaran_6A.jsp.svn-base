<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.hasil.StatusPembayaranActionBean">


        <fieldset class="aras1">
            <legend>
                Mod Pemilihan Status
            </legend>
        
             <p>
                    <label for="batal">Pilih Surat Peringatan/Notis Tuntutan :</label>

                        <td>
                            <s:radio name="kep1" value="T"/>Surat Peringatan
                            
                            <s:radio name="kep1" value="T"/>Notis Tuntutan
                        </td>

                </p>&nbsp;

            <p>
                <label>  Pilih Status :</label>
             <%----%> <%--  <s:select name="">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="" label="nama" value="kod"  />
                </s:select>--%>
            </p>

                    <table border="0" width="100%">
                <tr>
                    <td align="right">
                        <s:submit   name="search" value="Papar" class="btn"/>
                    </td> </tr>
            </table>
        </fieldset>

       <%-- <fieldset class="aras1">
            <legend>
                Bilangan
            </legend>


            <div class="content" align="center">

                <display:table class="tablecloth" name=""
                               pagesize="4" cellpadding="0" cellspacing="0" requestURI="/hasil/pembayaran" id="line">

                    <display:column title="Perkara" sortable="true">${line_rowNum}</display:column>
                    <display:column property="" title="ID Hakmilik" class="${line_rowNum}" />
                    <display:column property="" title="No Lot" class="${line_rowNum}" />
                    <display:column property="" title="Nombor Akuan" class="${line_rowNum}"/>
                    <display:column property="" title="BandarPekanMukim" class="${line_rowNum}" />
                    <display:column property="" title="Nama Pembayar" class="${line_rowNum}"/>
                    <display:column property="" title="Jumlah Perlu Bayar (RM)" class="${line_rowNum}" format="{0,number, 0.00}" style="text-align:right" />
                  
            </div>&nbsp;&nbsp;

            <table border="0"  width="100%">
                <tr>
                    <td align="right">
                        <s:submit name="selectList" value="Simpan" class="btn" />
                       <s:reset name=" " value="Isi Semula" class="btn"/>
                    </td>
                </tr>
            </table>

        </fieldset>--%>


         <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Pemilik Yang Belum Membayar Tunggakan
                </legend>

                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.list}"
                                   pagesize="4" cellpadding="0" cellspacing="0" requestURI="/hasil/pembayaran" id="line">
                        <display:column title="Pilih" sortable="true">
                            <div align="center"><s:radio name="radioBtn" value="${line_rowNum}" /></div></display:column>
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="" title="ID Hakmilik" class="${line_rowNum}" />
                        <display:column property="" title="Nama" class="${line_rowNum}" />
                        <display:column property="" title="Nombor Akaun" class="${line_rowNum}"/>
                        <display:column property="" title="Jumlah Tunggakan(RM)" class="${line_rowNum}" />
                        <display:column property="" title="Jumlah Tunggakan Tahunan" class="${line_rowNum}"/>
                        <display:column property="" title="Cara Penghantaran" class="${line_rowNum}"  />

                    </display:table>
                </div>


                <table border="0" bgcolor="green" width="100%">
                    <tr>
                        <td align="right">
                            <s:submit name="selectList" value="Simpan" />
                            <s:submit name="selectList" value="Cetak" />

                        </td>
                    </tr>
                </table>
            </fieldset>
        </div>
        </s:form>
        </div>
