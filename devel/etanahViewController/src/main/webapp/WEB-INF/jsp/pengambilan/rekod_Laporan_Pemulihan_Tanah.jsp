<%--
    Document   : rekod_Laporan_Pemulihan_Tanah
    Created on : Jun 1, 2010, 12:50:07 PM
    Author     : massita
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
    <c:if test="${actionBean.adaKerosakanTanah eq 'Y'}">
            $("#keteranganKerosakanTanah").removeAttr("disabled");
            $("#kosKerosakanTanah").removeAttr("disabled");
    </c:if>
    <c:if test="${actionBean.adaKerosakanTanah eq 'T'}">
            $("#keteranganKerosakanTanah").attr("disabled","disabled");
            $("#kosKerosakanTanah").attr("disabled","disabled");
    </c:if>
    <c:if test="${actionBean.adaKerosakanBangunan eq 'Y'}">
            $("#keteranganKerosakanBangunan").removeAttr("disabled");
            $("#kosKerosakanBangunan").removeAttr("disabled");
    </c:if>
    <c:if test="${actionBean.adaKerosakanBangunan eq 'T'}">
            $("#keteranganKerosakanBangunan").attr("disabled","disabled");
            $("#kosKerosakanBangunan").attr("disabled","disabled");
    </c:if>
    <c:if test="${actionBean.adaKerosakanPokok eq 'Y'}">
            $("#keteranganKerosakanPokok").removeAttr("disabled");
            $("#kosKerosakanPokok").removeAttr("disabled");
    </c:if>
    <c:if test="${actionBean.adaKerosakanPokok eq 'T'}">
            $("#keteranganKerosakanPokok").attr("disabled","disabled");
            $("#kosKerosakanPokok").attr("disabled","disabled");
    </c:if>
    <c:if test="${actionBean.adaKerosakanInfra eq 'Y'}">
            $("#keteranganKerosakanInfra").removeAttr("disabled");
            $("#kosKerosakanInfra").removeAttr("disabled");
    </c:if>
    <c:if test="${actionBean.adaKerosakanInfra eq 'T'}">
            $("#keteranganKerosakanInfra").attr("disabled","disabled");
            $("#kosKerosakanInfra").attr("disabled","disabled");
    </c:if>
    <c:if test="${actionBean.adaKerosakanLain eq 'Y'}">
            $("#keteranganKerosakanLain").removeAttr("disabled");
            $("#kosKerosakanLain").removeAttr("disabled");
    </c:if>
    <c:if test="${actionBean.adaKerosakanLain eq 'T'}">
            $("#keteranganKerosakanLain").attr("disabled","disabled");
            $("#kosKerosakanLain").attr("disabled","disabled");
    </c:if>
    <c:if test="${actionBean.adaKecacatanTanah eq 'Y'}">
            $("#keteranganKecacatanTanah").removeAttr("disabled");
            $("#kosKecacatanTanah").removeAttr("disabled");
    </c:if>
    <c:if test="${actionBean.adaKecacatanTanah eq 'T'}">
            $("#keteranganKecacatanTanah").attr("disabled","disabled");
            $("#kosKecacatanTanah").attr("disabled","disabled");
    </c:if>
        });


        function kerosakanTanahAda(){
            $("#keteranganKerosakanTanah").attr("disabled",false);
            $("#kosKerosakanTanah").attr("disabled",false);
        }

        function kerosakanTanahTiada(){
            $("#keteranganKerosakanTanah").attr("disabled",true);
            $("#keteranganKerosakanTanah").val("");
            $("#kosKerosakanTanah").attr("disabled",true);
            $("#kosKerosakanTanah").val("");
        }

        function kerosakanBangunanAda(){
            $("#keteranganKerosakanBangunan").attr("disabled",false);
            $("#kosKerosakanBangunan").attr("disabled",false);
        }

        function kerosakanBangunanTiada(){
            $("#keteranganKerosakanBangunan").attr("disabled",true);
            $("#keteranganKerosakanBangunan").val("");
            $("#kosKerosakanBangunan").attr("disabled",true);
            $("#kosKerosakanBangunan").val("");
        }

        function kerosakanPokokAda(){
            $("#keteranganKerosakanPokok").attr("disabled",false);
            $("#kosKerosakanPokok").attr("disabled",false);
        }

        function kerosakanPokokTiada(){
            $("#keteranganKerosakanPokok").attr("disabled",true);
            $("#keteranganKerosakanPokok").val("");
            $("#kosKerosakanPokok").attr("disabled",true);
            $("#kosKerosakanPokok").val("");
        }

        function kerosakanInfrastrukturAda(){
            $("#keteranganKerosakanInfra").attr("disabled",false);
            $("#kosKerosakanInfra").attr("disabled",false);
        }

        function kerosakanInfrastrukturTiada(){
            $("#keteranganKerosakanInfra").attr("disabled",true);
            $("#keteranganKerosakanInfra").val("");
            $("#kosKerosakanInfra").attr("disabled",true);
            $("#kosKerosakanInfra").val("");
        }

        function kerosakanLainLainAda(){
            $("#keteranganKerosakanLain").attr("disabled",false);
            $("#kosKerosakanLain").attr("disabled",false);
        }

        function kerosakanLainLainTiada(){
            $("#keteranganKerosakanLain").attr("disabled",true);
            $("#keteranganKerosakanLain").val("");
            $("#kosKerosakanLain").attr("disabled",true);
            $("#kosKerosakanLain").val("");
        }

        function kecacatanTanahAda(){
            $("#keteranganKecacatanTanah").attr("disabled",false);
            $("#kosKecacatanTanah").attr("disabled",false);
        }

        function kecacatanTanahTiada(){
            $("#keteranganKecacatanTanah").attr("disabled",true);
            $("#keteranganKecacatanTanah").val("");
            $("#kosKecacatanTanah").attr("disabled",true);
            $("#kosKecacatanTanah").val("");
        }

        function ajaxLink(link, update) {
            $.get(link, function(data) {
                $(update).html(data);
                $(update).show();
            });
            return false;
        }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.LaporanPemulihanTanahActionBean" name="form1">
    <s:messages />
    <s:errors/>
    <div  id="hakmilik_details">
        <fieldset class="aras1"><br/>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/LaporanPemulihanTanah" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="No Hakmilik"/>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="Daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Tuan Tanah" >
                        <c:set value="1" var="count"/>
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                            <s:link beanclass="etanah.view.stripes.pengambilan.LaporanPemulihanTanahActionBean"
                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                <br />
                                <c:out value="${count}"/>)&nbsp;
                                <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br />
                            </s:link>
                                <c:set value="${count + 1}" var="count"/>
                        </c:forEach>
                    </display:column>
                </display:table>
            </div><br /><br />
        </fieldset>
        <c:if test="${showDetails}">
            <fieldset class="aras1"><br/>
                <legend>Tawaran Pampasan bagi tuan tanah :- <br />
                    <font style="text-transform:uppercase; color: black">
                        <c:out value="${actionBean.permohonanPihak.pihak.nama}"/><br />
                        No Hakmilik : <c:out value="${actionBean.permohonanPihak.hakmilik.noHakmilik}"/><br />
                        No Lot/Pt : <c:out value="${actionBean.permohonanPihak.hakmilik.lot.nama}"/> <c:out value="${actionBean.permohonanPihak.hakmilik.noLot}"/>
                    </font>
                </legend><br />
                <div class="content" align="center">
                    <table class="tablecloth">
                        <tr>
                            <th width="20%">Pampasan</th><th width="10%">Ada</th><th width="10%">Tiada</th><th width="40%">Maklumat Pampasan</th>
                            <th width="10%">Nilai Pampasan (RM)</th>
                        </tr>
                        <tr>
                            <td>
                                <b>1. Pembangunan Atas Tanah</b>
                            </td>
                            <td>
                                <s:radio name="adaKerosakanTanah" value="Y" id="adaKerosakanTanah" onclick="kerosakanTanahAda();"/>&nbsp;Ada
                            </td>
                            <td>
                                <s:radio name="adaKerosakanTanah" value="T" id="adaKerosakanTanah" onclick="kerosakanTanahTiada();"/>&nbsp;Tiada
                            </td>
                            <td><s:textarea name="keteranganKerosakanTanah" id="keteranganKerosakanTanah" disabled="true" rows="3" cols="90"/></td>
                            <td><s:text name="kosKerosakanTanah" id="kosKerosakanTanah" disabled="true" formatPattern="#,##0.00"/></td>
                        </tr>
                        <tr>
                            <td>
                                <b>2. Tanaman Atas Tanah</b>
                            </td>
                            <td>
                                <s:radio name="adaKerosakanBangunan" value="Y" id="adaKerosakanBangunan" onclick="kerosakanBangunanAda();"/>&nbsp;Ada
                            </td>
                            <td>
                                <s:radio name="adaKerosakanBangunan" value="T" id="adaKerosakanBangunan" onclick="kerosakanBangunanTiada();"/>&nbsp;Tiada
                            </td>
                            <td><s:textarea name="keteranganKerosakanBangunan" id="keteranganKerosakanBangunan" disabled="true" rows="3" cols="90"/></td>
                            <td><s:text name="kosKerosakanBangunan" id="kosKerosakanBangunan" disabled="true" formatPattern="#,##0.00"/></td>
                        </tr>
                        <tr>
                            <td>
                                <b>3. Kemudahan Infrastruktur</b>
                            </td>
                            <td>
                                <s:radio name="adaKerosakanPokok" value="Y" id="adaKerosakanPokok" onclick="kerosakanPokokAda();"/>&nbsp;Ada
                            </td>
                            <td>
                                <s:radio name="adaKerosakanPokok" value="T" id="adaKerosakanPokok" onclick="kerosakanPokokTiada();"/>&nbsp;Tiada
                            </td>
                            <td><s:textarea name="keteranganKerosakanPokok" id="keteranganKerosakanPokok" disabled="true" rows="3" cols="90"/></td>
                            <td><s:text name="kosKerosakanPokok" id="kosKerosakanPokok" disabled="true" formatPattern="#,##0.00"/></td>
                        </tr>
                        <tr>
                            <td>
                                <b>4. Lain-Lain</b>
                            </td>
                            <td>
                                <s:radio name="adaKerosakanLain" value="Y" id="adaKerosakanLain" onclick="kerosakanLainLainAda();"/>&nbsp;Ada
                            </td>
                            <td>
                                <s:radio name="adaKerosakanLain" value="T" id="adaKerosakanLain" onclick="kerosakanLainLainTiada();"/>&nbsp;Tiada
                            </td>
                            <td><s:textarea name="keteranganKerosakanLain" id="keteranganKerosakanLain" disabled="true" rows="3" cols="90"/></td>
                            <td><s:text name="kosKerosakanLain" id="kosKerosakanLain" disabled="true" formatPattern="#,##0.00"/></td>
                        </tr>
                        <tr>
                            <td ></td>
                            <td ></td>
                            <td ></td>
                            <td><div align="right"><b>Jumlah Keseluruhan (RM) : </b></div></td>
                            <td>
                                <s:text name="a" value="${(actionBean.kosKerosakanTanah)+(actionBean.kosKerosakanBangunan)+(actionBean.kosKerosakanPokok)+(actionBean.kosKerosakanInfra)+(actionBean.kosKerosakanLain)+(actionBean.kosKecacatanTanah)}" formatPattern="#,##0.00" readonly="true"/>
                            </td>
                        </tr>
                    </table>
                </div>

                <br />

                <div align="center">
                    <tr>
                        <td>
                            <c:if test="${actionBean.simpanLaporan eq true}">
                                <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                                <s:button class="btn" id="save" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpan" value="Simpan"/>
                            </c:if>
                        </td>
                    </tr>
                </div>
            </fieldset>
        </c:if>
    </div>

    <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A' && actionBean.kodNegeri eq '05'}">--%>
    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SEK4A'}">

        <fieldset class="aras1"><br/>
            <legend>Laporan pemulihan tanah</legend><br />
            <div class="content" align="center">
                <table class="tablecloth">
                    <tr>
                        <th width="20%">Jenis Kerosakan</th><th width="10%">Ada</th><th width="10%">Tiada</th><th width="40%">Ulasan Jika Ada</th>
                        <th width="10%">Kos Kerosakan (RM)</th>
                    </tr>
                    <tr>
                        <td>
                            <b>1. Kerosakan Tanah</b>
                        </td>
                        <td>
                            <s:radio name="adaKerosakanTanah" value="Y" id="adaKerosakanTanah" onclick="kerosakanTanahAda();"/>&nbsp;Ada
                        </td>
                        <td>
                            <s:radio name="adaKerosakanTanah" value="T" id="adaKerosakanTanah" onclick="kerosakanTanahTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKerosakanTanah" id="keteranganKerosakanTanah" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKerosakanTanah" id="kosKerosakanTanah" disabled="true" formatPattern="#,##0.00"/></td>
                    </tr>
                    <tr>
                        <td>
                            <b>2. Kerosakan Bangunan</b>
                        </td>
                        <td>
                            <s:radio name="adaKerosakanBangunan" value="Y" id="adaKerosakanBangunan" onclick="kerosakanBangunanAda();"/>&nbsp;Ada
                        </td>
                        <td>
                            <s:radio name="adaKerosakanBangunan" value="T" id="adaKerosakanBangunan" onclick="kerosakanBangunanTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKerosakanBangunan" id="keteranganKerosakanBangunan" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKerosakanBangunan" id="kosKerosakanBangunan" disabled="true" formatPattern="#,##0.00"/></td>
                    </tr>
                    <tr>
                        <td>
                            <b>3. Kerosakan Pokok</b>
                        </td>
                        <td>
                            <s:radio name="adaKerosakanPokok" value="Y" id="adaKerosakanPokok" onclick="kerosakanPokokAda();"/>&nbsp;Ada
                        </td>
                        <td>
                            <s:radio name="adaKerosakanPokok" value="T" id="adaKerosakanPokok" onclick="kerosakanPokokTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKerosakanPokok" id="keteranganKerosakanPokok" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKerosakanPokok" id="kosKerosakanPokok" disabled="true" formatPattern="#,##0.00"/></td>
                    </tr>
                    <tr>
                        <td>
                            <b>4. Kerosakan Infrastruktur</b>
                        </td>
                        <td>
                            <s:radio name="adaKerosakanInfra" value="Y" id="adaKerosakanInfra" onclick="kerosakanInfrastrukturAda();"/>&nbsp;Ada
                        </td>

                        <td>
                            <s:radio name="adaKerosakanInfra" value="T" id="adaKerosakanInfra" onclick="kerosakanInfrastrukturTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKerosakanInfra" id="keteranganKerosakanInfra" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKerosakanInfra" id="kosKerosakanInfra" disabled="true" formatPattern="#,##0.00"/></td>
                    </tr>
                    <tr>
                        <td>
                            <b>5. Kerosakan Lain-Lain</b>
                        </td>
                        <td>
                            <s:radio name="adaKerosakanLain" value="Y" id="adaKerosakanLain" onclick="kerosakanLainLainAda();"/>&nbsp;Ada
                        </td>
                        <td>
                            <s:radio name="adaKerosakanLain" value="T" id="adaKerosakanLain" onclick="kerosakanLainLainTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKerosakanLain" id="keteranganKerosakanLain" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKerosakanLain" id="kosKerosakanLain" disabled="true" formatPattern="#,##0.00"/></td>
                    </tr>
                    <tr>
                        <td>
                            <b>6. Kecacatan Tanah</b>
                        </td>
                        <td>
                            <s:radio name="adaKecacatanTanah" value="Y" id="adaKecacatanTanah" onclick="kecacatanTanahAda();"/>&nbsp;Ada
                        </td>
                        <td>
                            <s:radio name="adaKecacatanTanah" value="T" id="adaKecacatanTanah" onclick="kecacatanTanahTiada();"/>&nbsp;Tiada
                        </td>
                        <td><s:textarea name="keteranganKecacatanTanah" id="keteranganKecacatanTanah" disabled="true" rows="3" cols="90"/></td>
                        <td><s:text name="kosKecacatanTanah" id="kosKecacatanTanah" disabled="true" formatPattern="#,##0.00"/></td>
                    </tr>
                    <tr>
                        <td ></td>
                        <td ></td>
                        <td ></td>
                        <td><div align="right"><b>Jumlah Keseluruhan (RM) : </b></div></td>
                        <td>
                            <s:text name="a" value="${(actionBean.kosKerosakanTanah)+(actionBean.kosKerosakanBangunan)+(actionBean.kosKerosakanPokok)+(actionBean.kosKerosakanInfra)+(actionBean.kosKerosakanLain)+(actionBean.kosKecacatanTanah)}" formatPattern="#,##0.00" readonly="true"/>
                        </td>
                    </tr>
                </table>
            </div>

            <br />

            <div align="center">
                <tr>
                    <td>
                        <%--<s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                        <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>--%>
                        <s:button class="btn" id="save" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanAduan" value="Simpan"/>
                    </td>
                </tr>
            </div>
        </fieldset>
    </c:if>
</s:form>
