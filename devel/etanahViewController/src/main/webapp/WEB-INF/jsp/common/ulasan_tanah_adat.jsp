<%-- 
    Document   : ulasan_tanah_adat
    Created on : Jan 29, 2010, 10:27:45 AM
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

<s:form beanclass="etanah.view.stripes.common.UlasanTanahAdatActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <c:if test="${tarikh}">
            <fieldset class="aras1">
                <legend>MAKLUMAT MESYUARAT</legend>
                <p>
                    <label><font color="red">*</font>Tarikh Mesyuarat :</label>
                    <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker"/>
                </p>
                <%--                <p>
                                    <label><font color="red">*</font>Masa :</label>
                                    <s:select name="jam" style="width:50px">
                                        <s:option value="01">01</s:option>
                                        <s:option value="02">02</s:option>
                                        <s:option value="03">03</s:option>
                                        <s:option value="04">04</s:option>
                                        <s:option value="05">05</s:option>
                                        <s:option value="06">06</s:option>
                                        <s:option value="07">07</s:option>
                                        <s:option value="08">08</s:option>
                                        <s:option value="09">09</s:option>
                                        <s:option value="10">10</s:option>
                                        <s:option value="11">11</s:option>
                                        <s:option value="12">12</s:option>
                                    </s:select>:
                                    <s:select name="minit" style="width:50px">
                                        <s:option value="00">00</s:option>
                                        <s:option value="05">05</s:option>
                                        <s:option value="10">10</s:option>
                                        <s:option value="15">15</s:option>
                                        <s:option value="20">20</s:option>
                                        <s:option value="25">25</s:option>
                                        <s:option value="30">30</s:option>
                                        <s:option value="35">35</s:option>
                                        <s:option value="40">40</s:option>
                                        <s:option value="45">45</s:option>
                                        <s:option value="50">50</s:option>
                                        <s:option value="55">55</s:option>
                                    </s:select>
                                    <s:select name="ampm" style="width:100px">
                                        <s:option value="AM">Pagi</s:option>
                                        <s:option value="PM">Petang</s:option>
                                    </s:select>
                                </p>--%>
                <p>
                    <label><font color="red">*</font>Bilangan Mesyuarat :</label>
                    <s:text name="permohonanRujukanLuar.noSidang" maxlength="10" id="bilSidang"/>
                    <s:hidden name="permohonanRujukanLuar.idRujukan"/>
                </p>
                <%-- <p>
                     <label><font color="red">*</font>Tempat Mesyuarat :</label>
                     <s:text name="permohonanRujukanLuar.lokasiAgensi" maxlength="50" size="50" id="tempat"/>
                 </p>--%>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanMMK" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
            <br/>
        </c:if>

        <fieldset class="aras1">
            <legend>TAJUK</legend>
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
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            </table>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Tujuan</legend>
            <table width="100%">
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Tujuan Kertas</td></tr>
                <tr><td></td><td>
                        <c:if test="${tujuan}">
                            <s:textarea name="tujuan" rows="4" style="width:97%;" class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTujuan}">
                            <c:if test="${actionBean.tujuan eq ' '}">Tiada Data</c:if>
                            <c:if test="${actionBean.tujuan ne ' '}">
                                <s:textarea name="tujuan"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if>
                    </td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            </table>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>LATAR BELAKANG</legend>
            <table width="100%">
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Latar Belakang</td></tr>
                <tr><td></td><td>
                        <c:if test="${latar}">
                            <s:textarea name="latarBelakang" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewLatar}">
                            <c:if test="${actionBean.latarBelakang eq ' ' || actionBean.latarBelakang eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.latarBelakang ne ' '}">
                                <s:textarea name="latarBelakang"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            </table>
            <%--<p align="center">
                <c:if test="${edit}">
                    <c:if test="${latar && !showPtPtg && !ptAndPtd}">
                        <s:button name="simpanPt" id="save1" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${latar && showPtPtg && !ptAndPtd}">
                        <s:button name="simpanPtPtg" id="save2" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                </c:if>
            </p>--%>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Butir-butir Tanah</legend>
            <table width="100%">
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Butir-butir Tanah</td></tr>
                <tr><td></td><td>
                        <c:if test="${latar}">
                            <s:textarea name="butirTanah" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewLatar}">
                            <c:if test="${actionBean.butirTanah eq ' ' || actionBean.butirTanah eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.butirTanah ne ' '}">
                                <s:textarea name="butirTanah"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            </table>
            <p align="center">
                <c:if test="${edit}">
                    <c:if test="${latar && !showPtPtg && !ptAndPtd}">
                        <s:button name="simpanPt" id="save1" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${latar && showPtPtg && !ptAndPtd}">
                        <s:button name="simpanPtPtg" id="save2" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                </c:if>
            </p>
        </fieldset>
        <br/>
        <c:if test="${showPtd}">
            <fieldset class="aras1">
                <legend>PENTADBIR TANAH</legend>
                <table width="100%">
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">Huraian Pentadbir Tanah</td></tr>
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
                    <tr><td>&nbsp;</td><td id="uLabel">Syor Pentadbir Tanah</td></tr>
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
                <p align="center">
                    <c:if test="${edit}">
                        <c:if test="${ptd && !ptAndPtd}">
                            <s:button name="simpanPtd" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </c:if>
                        <c:if test="${ptAndPtd}">
                            <s:button name="simpanPtAndPtd" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </c:if>
                    </c:if>
                </p>
            </fieldset>
            <br/>
        </c:if>
        <c:if test="${showPtg}">
            <fieldset class="aras1">
                <legend>PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</legend>
                <table width="100%">
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">Huraian Pengarah Tanah Dan Galian Negeri Sembilan</td></tr>
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
                    <tr><td>&nbsp;</td><td id="uLabel">Syor Pengarah Tanah Dan Galian Negeri Sembilan</td></tr>
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
                <p align="center">
                    <c:if test="${edit}">
                        <c:if test="${ptg}">
                            <s:button name="simpanPtg" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </c:if>
                    </c:if>
                </p>
                <br/>
            </fieldset>
        </c:if>
        <br/>
    </div>
</s:form>


