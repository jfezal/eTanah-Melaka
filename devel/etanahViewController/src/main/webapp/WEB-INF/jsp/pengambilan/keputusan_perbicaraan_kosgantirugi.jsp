<%-- 
    Document   : keputusan_perbicaraan_kosgantirugi
    Created on : 08-Apr-2010, 13:40:33
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
                        Keputusan Perbicaraan Kos Gantirugi
                    </legend>
                    <div class="content" align="left">

                        <table align="left" width="100%">
                             <tr>
                                <td align="left" width="30%">Tuntutan Kerosakan :</td>
                                <td align="left" width="70%">
                        <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line">
                        <display:column property="pilih" title="Pilih"/>
                        <display:column property="No" title="Jenis Kerosakan"/>
                        <display:column property="Nama" title="Amaun Kerosakan (RM)" />
                        </display:table>
                                <s:button name="savePengambilanInfo" value="Tambah" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                                <s:button name="savePengambilanInfo" value="Hapus" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/></td>

                            </tr>
                            <p> Keputusan pentadbir bagi setiap gantirugi kerosakan</p>
                            <tr>
                                <td align="left" width="30%">&nbsp;</td>
                                <td align="left" width="70%">
                        <display:table class="tablecloth" name="" pagesize="4" cellpadding="0" cellspacing="0" id="line">
                        <display:column property="pilih" title="Pilih"/>
                        <display:column property="No" title="Jenis Kerosakan"/>
                        <display:column property="Nama" title="Amaun Kerosakan (RM)" />
                        </display:table>
                                <s:button name="savePengambilanInfo" value="Tambah" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                                <s:button name="savePengambilanInfo" value="Hapus" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/></td>

                            </tr>


                        </table>
                            <br>
                            <br>
                            <p>
                            <label>&nbsp;</label>
                            <s:button name="savePengambilanInfo" value="Simpan" class="btn" onclick=""/>
                            </p>


                    </div>
                </fieldset>
            </div>

        </s:form>

