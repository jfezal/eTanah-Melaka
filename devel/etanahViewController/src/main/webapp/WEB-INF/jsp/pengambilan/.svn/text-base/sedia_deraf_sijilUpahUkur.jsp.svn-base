<%-- 
    Document   : sedia_deraf_sijilUpahUkur
    Created on : 10-May-2010, 14:46:32
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>


<s:form beanclass="etanah.view.stripes.pengambilan.UlasanMMKActionBean">
    <s:messages/>
    <s:errors/>

    <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Bayaran
                    </legend>
                    <div class="content" align="center">
                        <c:if test="${edit}">
                            <table border="0" width="80%">
                                <tr>
                                    <td><p><label><font color="black">Bayaran Ukur Yang Dikenakan (RM) :</font></label>
                                        <s:text name="bayaran" size="40"/></p></td>
                                </tr>
                            </table>
                        </c:if>
                        <c:if test="${!edit}">
                            <table border="0" width="80%">
                                <tr>
                                    <td><p><label><font color="black">Bayaran Ukur Yang Dikenakan (RM) :</font></label>
                                       &nbsp;</p></td>
                                </tr>
                            </table>
                        </c:if>
                    </div>
                </fieldset>
           </div>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Tanah
                    </legend>
                    <div class="content" align="center">
                        <c:if test="${edit}">
                            <table border="0" width="80%">
                                <tr>
                                    <td><p><label><font color="black">ID Permohonan :</font></label>
                                        <s:text name="idPermohonan" size="40"/></p></td>
                                </tr><br>
                                <tr>
                                    <td><p><label><font color="black">No Lot :</font></label>
                                        <s:text name="noLot" size="40"/></p></td>
                                </tr><br>
                                <tr>
                                    <td><p><label><font color="black">Mukim/Pekan/Bandar :</font></label>
                                        <s:text name="bandar" size="40"/></p></td>
                                </tr><br>
                                <tr>
                                    <td><p><label><font color="black">Seksyen :</font></label>
                                        <s:text name="seksyen" size="40"/></p></td>
                                </tr><br>
                                <tr>
                                    <td><p><label><font color="black">Daerah :</font></label>
                                        <s:text name="daerah" size="40"/></p></td>
                                </tr><br>
                                <tr>
                                    <td><p><label><font color="black">Negeri :</font></label>
                                    &nbsp;</td>
                                </tr>
                            </table>
                        </c:if>
                        <c:if test="${!edit}">
                            <table border="0" width="80%">
                                <tr>
                                    <td><p><label><font color="black">ID Permohonan :</font></label>
                                         &nbsp;</p></td>
                                </tr><br>
                                <tr>
                                    <td><p><label><font color="black">No Lot :</font></label>
                                        &nbsp;</p></td>
                                </tr><br>
                                <tr>
                                    <td><p><label><font color="black">Mukim/Pekan/Bandar :</font></label>
                                         &nbsp;</p></td>
                                </tr><br>
                                <tr>
                                    <td><p><label><font color="black">Seksyen :</font></label>
                                         &nbsp;</p></td>
                                </tr><br>
                                <tr>
                                    <td><p><label><font color="black">Daerah :</font></label>
                                         &nbsp;</p></td>
                                </tr><br>
                                <tr>
                                    <td><p><label><font color="black">Negeri :</font></label>
                                    &nbsp;</p></td>
                                </tr>
                            </table>
                        </c:if>
                    </div>
                </fieldset>
           </div>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Bayaran Telah Diterima Daripada
                    </legend>
                    <div class="content" align="center">
                        <c:if test="${edit}">
                            <table border="0" width="80%">
                                <tr>
                                    <td><p><label><font color="black">Terima Daripada :</font></label>
                                        <s:textarea name="pemohon" cols="50" rows="1"/></p></td>
                                </tr>
                            </table>
                        </c:if>
                        <c:if test="${!edit}">
                            <table border="0" width="80%">
                                <tr>
                                    <td><p><label><font color="black">Terima Daripada :</font></label>
                                            &nbsp;</p></td>
                                </tr>
                            </table>
                        </c:if>
                    </div>
                </fieldset>
           </div>
           <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Kerja Ukur Dijalankan Oleh
                    </legend>
                    <div class="content" align="center">
                        <c:if test="${edit}">
                            <table border="0" width="80%">
                                <tr>
                                    <td><p><label><font color="black">Dijalankan Oleh :</font></label>
                                        <s:textarea name="juruUkur" cols="50" rows="1"/></p></td>
                                </tr>
                            </table>
                        </c:if>
                        <c:if test="${!edit}">
                            <table border="0" width="80%">
                                <tr>
                                    <td><p><label><font color="black">Dijalankan Oleh :</font></label>
                                            &nbsp;</p></td>
                                </tr>
                            </table>
                        </c:if>
                    </div>
                </fieldset>
           </div>
           <%--<div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Perakuan
                    </legend>
                    <div class="content" align="center">
                        <table>
                            <tr>
                                <td> Tarikh :</td>
                                <td><s:text name="tarikh" size="30"/></td>
                            </tr>
                            <tr>
                                <td>Ditandatangani oleh : </td>
                                <td><s:select name="jawatan">
                                                <s:option>Pilih..</s:option>
                                                <s:option>Pengarah Tanah dan Galian</s:option>
                                                <s:option>Pendaftar</s:option>
                                                <s:option>Pentadbir Tanah dan Daerah</s:option>
                                                <s:options-collection collection="" value="" label=""/>
                                                </s:select></td>&nbsp;
                            </tr>
                            <tr>
                                <td>Nama : </td>
                                <td><s:text name="ttNama"/></td>&nbsp;
                            </tr>
                        </table>
                    </div>
                </fieldset>
           </div>--%>
    <c:if test="${edit}">
        <p align="center">
            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>
</s:form>
