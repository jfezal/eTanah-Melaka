<%-- 
    Document   : penyediaan_surat_tangguh_bicara
    Created on : 18-Jan-2010, 23:14:18
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pengambilan.PengambilanSementaraActionBean">

            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Siasatan Lepas
                    </legend>
                    <div class="content" align="left">
                        <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line">
                        <display:column property="bil" title="Bil"/>
                        <display:column property="nosiasatan" title="No Siasatan" href="#"/>
                        <display:column property="tarikh" title="Tarikh"/>
                        <display:column property="sebabtangguh" title="Sebab Tangguh"/>
                        </display:table>

                    </div>
                </fieldset>
            </div>
                             <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Siasatan Lepas
                    </legend>
                    <div class="content" align="left">
                        <table align="left" border="0" width="100%">
                            <tr>
                                <td align="left" width="30%">Nombor Kes :</td>
                                <td align="left" width="70%">Tiada Data</td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Nombor Siasatan :</td>
                                <td align="left" width="70%">Tiada Data</td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Nombor Warta :</td>
                                <td align="left" width="70%">Tiada Data</td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Tarikh Warta :</td>
                                <td align="left" class="input1" width="70%">Tiada Data</td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Pegawai Bicara :</td>
                                <td align="left" width="70%">Tiada Data</td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Bilik Bicara :</td>
                                <td align="left" width="70%">Tiada Data</td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Tarikh :</td>
                                <td align="left" class="input1" width="70%">Tiada Data</td>
                            </tr>
                            <tr>
                                <td align="left" width="30%">Masa :</td>
                                <td align="left" width="70%">Tiada Data</td>
                            </tr>
                        </table>
                    </div>
                </fieldset>
            </div>
    </s:form>
