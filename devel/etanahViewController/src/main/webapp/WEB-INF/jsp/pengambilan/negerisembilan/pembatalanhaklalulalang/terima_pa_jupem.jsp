<%-- 
    Document   : terima_pa_jupem
    Created on : nov 23, 2010, 3:40:23 PM
    Author     : massita
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pembangunan.TerimaPaJupemActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Dokumen yang diterima daripada Jupem</legend>
            <div class="content">
                <table border="0" width="40%" align="center">
                    <c:if test="${edit}">
                        <tr><td width="10%" align="left"><b>1.   Pelan Akui (PA) No. PA :</b></td>
                            <td ><font style="text-transform: uppercase"><s:text  name="noPelan" /></font></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td width="40%" align="left"><b>1.   Pelan Akui (PA) No. PA :</b></td>
                            <td><font style="text-transform:capitalize"> ${actionBean.noPelan}</font></td></tr>
                    </c:if>
                     <c:if test="${edit}">
                     <tr><td width="40%"align="left"><b>2.   Pelan B1 </b></td></tr>
                            <%--<td><font style="text-transform: uppercase"><s:text  name="b1"/></font></td></tr>--%>
                    </c:if>
                    <c:if test="${!edit}">
                    <tr><td width="40%" align="left"><b>2.   Pelan B1 :</b></td></tr>
                            <%--<td><font style="text-transform:capitalize"></font></td></tr>--%>
                    </c:if>
                    <c:if test="${edit}">
                    <tr><td width="40%" align="left"><b>3.   Surat Iringan</b></td></tr>
                            <%--<td><font style="text-transform: uppercase"><s:text  name="srtIringan"/></font></td></tr>--%>
                    </c:if>
                    <c:if test="${!edit}">
                    <tr><td width="40%" align="left"><b>3.   Surat Iringan</b></td></tr>
                            <%--<td><font style="text-transform:capitalize"></font></td></tr>--%>
                        </c:if>
               </table>
                   
                        <table align="left">
                            <div class="subtitle">
                                <legend align="left">
                                    Agihan Tugas
                                </legend>
                                <p><label>Hantar Kepada :</label>
                                    <s:select name="" style="width:300px;" id="id_pguna">
                                        <s:option value="">-- Sila Pilih --</s:option>
                                        <s:options-collection collection="${penggunaperanan.listPp}" value="idPengguna" label="nama" />
                                    </s:select>
                                </p>
                                <br/>
                            </div>
                        </table>
                 <br/><br/>
            </div>
        </fieldset>
            <p align="center">
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
            <p align="center">
                <s:button name="simpan" id="save" value="terima PA dari jupem" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
    </div>
</s:form>
