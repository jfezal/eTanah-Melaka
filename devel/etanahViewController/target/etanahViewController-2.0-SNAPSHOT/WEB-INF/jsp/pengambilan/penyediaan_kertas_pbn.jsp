<%-- 
    Document   : penyediaan_kertas_pbn
    Created on : 14-Jan-2010, 10:32:48
    Author     : nordiyana
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pengambilan.PenyediaanKertasActionBean">
        <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Draf Kertas Penuh
                    </legend>
                    <div class="content" align="left">
                        <table align="left" border="black" width="100%">
                            <tr>
                                <td align="left" width="30%">ID Permohonan :</td>
                                <td align="left" width="70%"><label for="idpermohonan">SW0000012678922</label></td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Tujuan :</td>
                                <td align="left" width="70%">
                                    <s:textarea name="tujuan1" cols="87" rows="8"/><s:textarea name="tujuan2" cols="87" rows="8"/>

                                </td>
                            </tr>
                            <tr>
                                <td align="left" width="30%" rowspan="1">Latarbelakang Permohonan :</td>
                                <td align="left" width="70%">
                                <s:textarea name="latarbelakang1" cols="87" rows="8"/>
                                <s:textarea name="latarbelakang2" cols="87" rows="8"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Perihal Tanah: </td>
                                <td align="left" width="70%" >
                                    <s:textarea name="perihaltanah1" cols="87" rows="8"/>
                                    <s:textarea name="perihaltanah2" cols="87" rows="8"/>
                                    <br>
                                    Kegunaan lot-lot yang bersempadan dengannya adalah seperti berikut :
                                    <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line" size="100">
                                    <display:column property="arah" title="Arah"/>
                                    <display:column property="statustanah" title="Status Tanah Sekeliling"/>
                                    <display:column property="lot" title="No Lot/PT"/>
                                    <display:column property="kategori" title="Kategori"/>
                                    <display:column property="gunasemasa" title="Kegunaan Semasa"/>
                                    </display:table>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" width="100%" rowspan="2"></td>
                                
                            </tr>
                           
                        </table>
                                    <table align="center"><tr><td align="center"><s:submit name="searchAllPermohonan" value="Kertas Ringkasan" class="btn"/></td></tr></table>

                    </div>
                </fieldset>
            </div>
                           </s:form>