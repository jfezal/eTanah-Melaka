<%-- 
    Document   : ulasan_tanah_ladang
    Created on : Jan 22, 2010, 2:35:14 PM
    Author     : muhammad.amin
--%>

  

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/timepicker.js"></script>
<script type="text/javascript">

</script>

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


<s:form beanclass="etanah.view.stripes.common.UlasanTanahLadangActionBean">
    <s:messages/>
    <s:errors/>
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
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Tujuan</td></tr>
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
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Latar Belakang</td></tr>
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
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            </table>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>ULASAN JABATAN TEKNIKAL</legend>
            <table width="100%">
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Jabatan Kebajikan Masyarakat, Negeri Sembilan</td></tr>
                <tr><td></td><td>
                        <c:if test="${jabatan}">
                            <s:textarea name="ulasanJabatanKebajikan" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewJabatan}">
                            <c:if test="${actionBean.ulasanJabatanKebajikan eq ' ' || actionBean.ulasanJabatanKebajikan eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.ulasanJabatanKebajikan ne ' '}">
                                <s:textarea name="ulasanJabatanKebajikan"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Jabatan Tenaga Kerja, Negeri Sembilan</td></tr>
                <tr><td></td><td>
                        <c:if test="${jabatan}">
                            <s:textarea name="ulasanJabatanTenagaKerja" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewJabatan}">
                            <c:if test="${actionBean.ulasanJabatanTenagaKerja eq ' ' || actionBean.ulasanJabatanTenagaKerja eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.ulasanJabatanTenagaKerja ne ' '}">
                                <s:textarea name="ulasanJabatanTenagaKerja"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Unit Perancang Ekonomi Negeri, Negeri Sembilan</td></tr>
                <tr><td></td><td>
                        <c:if test="${jabatan}">
                            <s:textarea name="ulasanUPEN" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewJabatan}">
                            <c:if test="${actionBean.ulasanUPEN eq ' ' || actionBean.ulasanUPEN eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.ulasanUPEN ne ' '}">
                                <s:textarea name="ulasanUPEN"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Jabatan Perancang Bandar Dan Desa, Negeri Sembilan</td></tr>
                <tr><td></td><td>
                        <c:if test="${jabatan}">
                            <s:textarea name="ulasanJPBD" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewJabatan}">
                            <c:if test="${actionBean.ulasanJPBD eq ' ' || actionBean.ulasanJPBD eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.ulasanJPBD ne ' '}">
                                <s:textarea name="ulasanJPBD"  readonly="true" class="normal_text"
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
        <c:if test="${ptg || viewPtg}">
            <br/>
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
                <c:if test="${ptg}">
                    <p align="center">
                        <s:button name="simpanPtPtg" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                    <br/>
                </c:if>
                <%--<p>
                    <label>&nbsp;</label>
                    <c:if test="${jabatan}">
                        <s:button name="simpanPtCon" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                    <c:if test="${ptg}">
                        <s:button name="simpanPtg" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </c:if>
                </p>--%>
            </fieldset>
            <%--<br/>--%>
        </c:if>
    </div>
</s:form>



