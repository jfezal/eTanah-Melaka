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

<s:form beanclass="etanah.view.stripes.common.UlasanMmkActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${actionBean.permohonan.permohonanSebelum eq null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend></legend>
                <br/><br/>
                <p align="center"><font size="4"> Maklumat ini hanya diperlukan untuk permohonan rayuan sahaja.</font></p>
                <br/><br/><br/><br/>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${actionBean.permohonan.permohonanSebelum ne null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>MAKLUMAT KERTAS</legend>
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
                    <tr><td>&nbsp;</td><td id="uLabel">3. Asas Rayuan</td></tr>
                    <tr><td></td><td>
                            <c:if test="${tajuk}">
                                <s:textarea name="asasRayuan" rows="4" style="width:97%;"  class="normal_text"/>
                            </c:if>
                            <c:if test="${viewTajuk}">
                                <c:if test="${actionBean.asasRayuan eq ' ' || actionBean.asasRayuan eq null}">Tiada Data</c:if>
                                <c:if test="${actionBean.asasRayuan ne ' '}">
                                    <s:textarea name="asasRayuan"  readonly="true" class="normal_text"
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                                </c:if>
                            </c:if></td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                </table>
            </fieldset>
            <br/>
            <fieldset class="aras1">
                <legend>PENTADBIR TANAH</legend>
                <table width="100%">
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">4. Huraian Pentadbir Tanah</td></tr>
                    <tr><td></td><td>
                            <c:if test="${ptd}">
                                <s:textarea name="huraianPentadbir" rows="4" style="width:97%;"  class="normal_text"/>
                            </c:if>
                            <c:if test="${viewPtd}">
                                <c:if test="${actionBean.huraianPentadbir eq ' ' || actionBean.huraianPentadbir eq null}">Tiada Data</c:if>
                                <c:if test="${actionBean.huraianPentadbir ne ' '}">
                                    <s:textarea name="huraianPentadbir"  readonly="true" class="normal_text" 
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                                </c:if>
                            </c:if></td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">5. Syor Pentadbir Tanah</td></tr>
                    <tr><td></td><td>
                            <c:if test="${ptd}">
                                <s:textarea name="syorPentadbir" rows="4" style="width:97%;"  class="normal_text"/>
                            </c:if>
                            <c:if test="${viewPtd}">
                                <c:if test="${actionBean.syorPentadbir eq ' ' || actionBean.syorPentadbir eq null}">Tiada Data</c:if>
                                <c:if test="${actionBean.syorPentadbir ne ' '}">
                                    <s:textarea name="syorPentadbir"  readonly="true" class="normal_text" 
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                                </c:if>
                            </c:if></td></tr>
                    <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                </table>
                <c:if test="${tajuk && !ptg}">
                    <p align="center">
                        <s:button name="simpanPtCon" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                    <br/>
                </c:if>
            </fieldset>
            <br/>
            <c:if test="${ptg || viewPtg}">
                <fieldset class="aras1">
                    <legend>PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</legend>
                    <table width="100%">
                        <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;</td><td id="uLabel">6. Huraian Pengarah Tanah Dan Galian Negeri Sembilan</td></tr>
                        <tr><td></td><td>
                                <c:if test="${ptg}">
                                    <s:textarea name="huraianPtg" rows="4" style="width:97%;"  class="normal_text"/>
                                </c:if>
                                <c:if test="${viewPtg}">
                                    <c:if test="${actionBean.huraianPtg eq ' ' || actionBean.huraianPtg eq null}">Tiada Data</c:if>
                                    <c:if test="${actionBean.huraianPtg ne ' '}">
                                        <s:textarea name="huraianPtg"  readonly="true" class="normal_text"
                                                    style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                                    </c:if>
                                </c:if></td></tr>
                        <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;</td><td id="uLabel">7. Syor Pengarah Tanah Dan Galian Negeri Sembilan</td></tr>
                        <tr><td></td><td>
                                <c:if test="${ptg}">
                                    <s:textarea name="syorPtg" rows="4" style="width:97%;"  class="normal_text"/>
                                </c:if>
                                <c:if test="${viewPtg}">

                                    <c:if test="${actionBean.syorPtg eq ' ' || actionBean.syorPtg eq null}">Tiada Data</c:if>
                                    <c:if test="${actionBean.syorPtg ne ' '}"> 
                                        <s:textarea name="syorPtg"  readonly="true" class="normal_text" 
                                                    style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                                    </c:if>
                                </c:if></td></tr>
                        <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                    </table>
                    <c:if test="${ptg}">
                        <p align="center">
                            <s:button name="simpanPtPtg" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </c:if>
                </fieldset>
            </c:if>
        </div>
    </c:if>
</s:form>

