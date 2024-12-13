<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%-- carian hakmilik start--%>

<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPerizabanActionBean" name="form">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" >
        <fieldset class="aras1">         

            <table>


                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMBT' or actionBean.permohonan.kodUrusan.kod eq 'PTBTS' or actionBean.permohonan.kodUrusan.kod eq 'PTBTC'}">
                    <c:if test="${actionBean.stageId eq '01Kemasukan'}">
                        <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><label>Tujuan Kegunaan Tanah :</label></td>
                            <td>
                                <s:textarea name="tujuanRezab" id="tujuanRezab" cols="45" rows="5"></s:textarea>
                            </td>
                        </tr>

                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>
                                <s:button name="reset" value="Isi Semula" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                <s:button name="saveData" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/> 
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                    </c:if>
                    <c:if test="${actionBean.stageId ne '01Kemasukan'}">
                        <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><label>Tujuan Kegunaan Tanah : </label></td>
                            <td>
                                ${actionBean.tujuanRezab}
                            </td>
                        </tr>
                    </c:if>

                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'BMBT' and actionBean.permohonan.kodUrusan.kod ne 'PTBTS' and actionBean.permohonan.kodUrusan.kod ne 'PTBTC' and actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
                    <tr>
                        <td>&nbsp;</td>    
                        <td valign="top"><label>Tujuan Rizab : </label></td>
                        <td><c:if test="${actionBean.stageId eq '01Kemasukan'}">
                                <s:textarea name="tujuanRezab" id="tujuanRezab" cols="45" rows="5"></s:textarea>
                            </c:if>
                            <c:if test="${actionBean.stageId ne '01Kemasukan'}">
                                ${actionBean.tujuanRezab}&nbsp;
                            </c:if>
                        </td>
                    </tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'MMRE' || actionBean.permohonan.kodUrusan.kod ne 'WMRE'|| actionBean.permohonan.kodUrusan.kod ne 'BMRE'}">
                        <tr>
                            <td>&nbsp;</td>
                            <td><label>Pegawai Pengawal : </label></td>
                            <td>
                                <c:if test="${actionBean.stageId eq '01Kemasukan'}">
                                    <s:text id="pengawalRezab" name="pengawalRezab" size="45"></s:text>
                                </c:if>
                                <c:if test="${actionBean.stageId ne '01Kemasukan'}">
                                    ${actionBean.pengawalRezab}&nbsp;
                                </c:if>

                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>
                            <c:if test="${actionBean.stageId eq '01Kemasukan'}">
                                <s:button name="saveData" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/> 
                                <s:reset name="reset" value="Isi Semula" class="btn"/>
                            </c:if>

                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </c:if>
                    
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                    <tr>
                        <td>&nbsp;</td>    
                        <td valign="top"><label>Pilihan Jadual : </label></td>
                        <td><c:if test="${actionBean.stageId eq '01Kemasukan'}">
                                <s:radio name="tujuanRezab" value="Jadual Kedua (Seksyen 17)"/>Jadual Kedua<br>
                                <s:radio name="tujuanRezab" value="Jadual Ketiga (Seksyen 7)"/>Jadual Ketiga<br>
                                <s:radio name="tujuanRezab" value="Jadual Kedua (Seksyen 17) dan Jadual Ketiga (Seksyen 7)"/>Jadual Kedua dan Jadual Ketiga<br>
                            </c:if>
                            <c:if test="${actionBean.stageId ne '01Kemasukan'}">
                                ${actionBean.tujuanRezab}&nbsp;
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    
                        <td>
                            <c:if test="${actionBean.stageId eq '01Kemasukan'}">
                                <br>
                                <s:button name="saveDataJadual" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/> 
                                <s:reset name="reset" value="Isi Semula" class="btn"/>
                            </c:if>

                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </c:if>


            </table>
        </fieldset>
    </div>
</s:form>
