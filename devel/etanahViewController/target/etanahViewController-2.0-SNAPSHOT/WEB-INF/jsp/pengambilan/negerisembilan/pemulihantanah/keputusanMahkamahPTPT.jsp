<%--
    Document   : keputusanMahkkamahPTPT
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
    <c:if test="${actionBean.adaKerosakanLain eq 'Y'}">
            $("#keteranganKerosakanLain").removeAttr("disabled");
            $("#kosKerosakanLain").removeAttr("disabled");
    </c:if>
    <c:if test="${actionBean.adaKerosakanLain eq 'T'}">
            $("#keteranganKerosakanLain").attr("disabled","disabled");
            $("#kosKerosakanLain").attr("disabled","disabled");
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


        function ajaxLink(link, update) {
            $.get(link, function(data) {
                $(update).html(data);
                $(update).show();
            });
            return false;
        }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.KeputusanMahkamahPTPTActionBean" name="form1">
    <s:messages />
    <s:errors/>
    <div  id="hakmilik_details">
        <fieldset class="aras1"><br/>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="/pengambilan/keputusanMahkamahPTPT" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="No Hakmilik"/>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="Daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Tuan Tanah" >
                        <c:set value="1" var="count"/>
                        
                        <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai" varStatus="loop" >
                            <c:set value="${count}" var="count2"/>
                            <s:link beanclass="etanah.view.stripes.pengambilan.KeputusanMahkamahPTPTActionBean"
                                    event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                <s:param name="simpanLaporan" value="${actionBean.simpanLaporan}"/>
                                <br />
                                
                                ${loop.index+1}.&nbsp; <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;<br />
                            </s:link>
                        </c:forEach>
                    </display:column>
                </display:table>
            </div><br /><br />
        </fieldset>
        <c:if test="${showDetails}">
            <fieldset class="aras1"><br/>
                <legend>Keputusan Mahkamah</legend><br />
                <div class="content" align="center">
                    Nama : ${actionBean.permohonanPihak.pihak.nama}
                    <table class="tablecloth">
                        <tr>
                            <th width="20%">Keputusan</th><th width="10%">Ada</th><th width="10%">Tiada</th><th width="40%">Maklumat Keputusan</th>
                            <th width="10%">Nilai Pampasan (RM)</th>
                        </tr>
                        <tr>
                            <td>
                                <b>1. Nilai Pampasan</b>
                            </td>
                            <td>
                                <s:radio name="adaKerosakanTanah" value="Y" id="adaKerosakanTanah" onclick="kerosakanTanahAda();"/>&nbsp;Ada
                            </td>
                            <td>
                                <s:radio name="adaKerosakanTanah" value="T" id="adaKerosakanTanah" onclick="kerosakanTanahTiada();"/>&nbsp;Tiada
                            </td>
                            <td><s:textarea name="keteranganKerosakanTanah" id="keteranganKerosakanTanah" disabled="true" rows="3" cols="90" class="normal_text"/></td>
                            <td><s:text name="kosKerosakanTanah" id="kosKerosakanTanah" disabled="true" formatPattern="#,##0.00"/></td>
                        </tr>
                        <tr>
                            <td>
                                <b>2. Nilai Lain-Lain</b>
                            </td>
                            <td>
                                <s:radio name="adaKerosakanLain" value="Y" id="adaKerosakanLain" onclick="kerosakanLainLainAda();"/>&nbsp;Ada
                            </td>
                            <td>
                                <s:radio name="adaKerosakanLain" value="T" id="adaKerosakanLain" onclick="kerosakanLainLainTiada();"/>&nbsp;Tiada
                            </td>
                            <td><s:textarea name="keteranganKerosakanLain" id="keteranganKerosakanLain" disabled="true" rows="3" cols="90" class="normal_text"/></td>
                            <td><s:text name="kosKerosakanLain" id="kosKerosakanLain" disabled="true" formatPattern="#,##0.00"/></td>
                        </tr>
                        <tr>
                            <td ></td>
                            <td ></td>
                            <td ></td>
                            <td><div align="right"><b>Jumlah Keseluruhan (RM) : </b></div></td>
                            <td>
                                <s:text name="a" value="${(actionBean.kosKerosakanTanah)+(actionBean.kosKerosakanLain)}" formatPattern="#,##0.00" readonly="true"/>
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
</s:form>