<%--
    Document   : ulasan_mmk
    Created on : Feb 1, 2010, 12:01:43 PM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<style type="text/css">

    #uLabel {
        width: 15em;
        float: left;
        text-align: left;
        margin-right: 0px;
        display: block;
        color:#003194;
        font-weight: bold;
        font-family:Tahoma;
        font-size: 13px;
        margin-left: -3px;
    }
</style>

<s:form beanclass="etanah.view.stripes.consent.MaklumanMmkActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${!actionBean.tangguh && actionBean.permohonan.kodUrusan.kod ne 'KPTG1'  && actionBean.permohonan.kodUrusan.kod ne 'KPTG2'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend></legend>
                <br/><br/>
                <p align="center"><font size="4"> Maklumat ini hanya diperlukan untuk permohonan ditangguh untuk maklumat tambahan.</font></p>
                <br/><br/><br/><br/>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.tangguh || actionBean.permohonan.kodUrusan.kod eq 'KPTG1' || actionBean.permohonan.kodUrusan.kod eq 'KPTG2'}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>MAKLUMAT KERTAS</legend>
                <table width="100%">
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">Tajuk Kertas</td></tr>
                    <tr><td></td><td>
                            <c:if test="${edit}">
                                <s:textarea name="tajuk" rows="4" style="width:97%;"/>
                            </c:if>
                            <c:if test="${!edit}">
                                <c:if test="${actionBean.tajuk eq ' '}">Tiada Data</c:if>
                                <c:if test="${actionBean.tajuk ne ' '}">${actionBean.tajuk}</c:if>
                            </c:if>
                        </td></tr>
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">1. Tujuan</td></tr>
                    <tr><td></td><td>
                            <c:if test="${edit}">
                                <s:textarea name="tujuan" rows="4" style="width:97%;" class="normal_text"/>
                            </c:if>
                            <c:if test="${!edit}">
                                <c:if test="${actionBean.tujuan eq ' '}">Tiada Data</c:if>
                                <c:if test="${actionBean.tujuan ne ' '}">                 
                                    <s:textarea name="tujuan"  readonly="true" class="normal_text"
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                                </c:if>
                            </c:if>
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">2. Latar Belakang</td></tr>
                    <tr><td></td><td>
                            <c:if test="${edit}">
                                <s:textarea name="latarBelakang" rows="4" style="width:97%;" class="normal_text"/>
                            </c:if>
                            <c:if test="${!edit}">
                                <c:if test="${actionBean.latarBelakang eq ' '}">Tiada Data</c:if>
                                <c:if test="${actionBean.latarBelakang ne ' '}">                              
                                    <s:textarea name="latarBelakang"  readonly="true" class="normal_text"
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                                </c:if>
                            </c:if>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                </table>
            </fieldset>
            <br/>
            <fieldset class="aras1">
                <legend>PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</legend>
                <table width="100%">
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">3. Makluman Pengarah Tanah Dan Galian Negeri Sembilan</td></tr>
                    <tr><td></td><td>
                            <c:if test="${edit}">
                                <s:textarea name="maklumanPtg" rows="4" style="width:97%;" class="normal_text"/>
                            </c:if>
                            <c:if test="${!edit}">
                                <c:if test="${actionBean.maklumanPtg eq ' '}">Tiada Data</c:if>
                                <c:if test="${actionBean.maklumanPtg ne ' '}">     
                                    <s:textarea name="maklumanPtg"  readonly="true" class="normal_text"
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                                </c:if>
                            </c:if>
                        </td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">4. Syor Pengarah Tanah Dan Galian Negeri Sembilan</td></tr>
                    <tr><td></td><td>
                            <c:if test="${edit}">
                                <s:textarea name="syorPtg" rows="4" style="width:97%;" class="normal_text"/>
                            </c:if>
                            <c:if test="${!edit}">
                                <c:if test="${actionBean.syorPtg eq ' '}">Tiada Data</c:if>
                                <c:if test="${actionBean.syorPtg ne ' '}">   
                                    <s:textarea name="syorPtg"  readonly="true" class="normal_text"
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                                </c:if>
                            </c:if>
                        </td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                </table>
            </fieldset>
            <br/>
            <fieldset class="aras1">
                <legend>KEPUTUSAN</legend>
                <table width="100%">
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">5. Keputusan</td></tr>
                    <tr><td></td><td>
                            <c:if test="${edit}">
                                <s:textarea name="keputusan" rows="4" style="width:97%;" class="normal_text"/>
                            </c:if>
                            <c:if test="${!edit}">
                                <c:if test="${actionBean.keputusan eq ' '}">Tiada Data</c:if>
                                <c:if test="${actionBean.keputusan ne ' '}">
                                    <s:textarea name="keputusan"  readonly="true" class="normal_text"
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                                </c:if>
                            </c:if>
                        </td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                </table>
                <c:if test="${edit}">
                    <p align="center">
                        <s:button name="simpanPtPtg" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                    <br/>
                </c:if>
            </fieldset>
        </div>
    </c:if>
</s:form>

