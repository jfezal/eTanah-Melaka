<%-- 
    Document   : maklumat_risalat_mmkn
    Created on : Mar 25, 2011, 5:26:47 PM
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

<s:form beanclass="etanah.view.stripes.consent.MaklumatRisalatMmknActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kertas Risalat</legend>
            <table width="100%">
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Tajuk Kertas</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="tajuk" rows="4" style="width:97%;"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.tajuk eq ' '}">Tiada Data</c:if>
                            <c:if test="${actionBean.tajuk ne ' '}">${actionBean.tajuk}</c:if>
                        </c:if>
                    </td></tr>
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">1. Tujuan</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="tujuan" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.tujuan eq ' ' || actionBean.tujuan eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.tujuan ne ' '}">
                                <s:textarea name="tujuan"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if></td></tr>
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">2. Latar Belakang</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="latarBelakang" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.latarBelakang eq ' ' || actionBean.latarBelakang eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.latarBelakang ne ' '}">
                                <s:textarea name="latarBelakang"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                            </c:if>
                        </c:if></td></tr>
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">3. Asas Permohonan</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="asas" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.asas eq ' ' || actionBean.asas eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.asas ne ' '}">
                                <s:textarea name="asas"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                            </c:if>
                        </c:if></td></tr>
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">4. Ulasan Pengarah Tanah dan Galian</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="ulasanPtg" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.ulasanPtg eq ' ' || actionBean.ulasanPtg eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.ulasanPtg ne ' '}">
                                <s:textarea name="ulasanPtg"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                            </c:if>
                        </c:if></td></tr>
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">5. Syor Pengarah Tanah dan Galian</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="syorPtg" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.syorPtg eq ' ' || actionBean.syorPtg eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.syorPtg ne ' '}">
                                <s:textarea name="syorPtg"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                            </c:if>
                        </c:if></td></tr>
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">6. Keputusan</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="keputusan" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.keputusan eq ' ' || actionBean.keputusan eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.keputusan ne ' '}">
                                <s:textarea name="keputusan"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                            </c:if>
                        </c:if></td></tr>
                <%--<tr><td>&nbsp;</td><td>&nbsp;</td></tr>--%>
            </table>
            <c:if test="${tajuk}">
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>

        </fieldset>
        <br/>
      
    </div>

</s:form>
